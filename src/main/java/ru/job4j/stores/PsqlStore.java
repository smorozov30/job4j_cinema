package ru.job4j.stores;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.models.Place;
import ru.job4j.models.Visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    public static final Logger LOG = LogManager.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(new FileReader("cinema_db.properties"))) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();

    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public List<Place> getOccupiedPlaces() {
        List<Place> places = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT DISTINCT row, place FROM hall JOIN visitor ON hall.id = visitor.place_id;")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    places.add(new Place(it.getInt("row"),
                                        it.getInt("place")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return places;
    }

    @Override
    public Visitor takePlace(Place place, Visitor visitor) {
        int placeId = getPlaceId(place);
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "INSERT INTO visitor(name, phone, place_id) VALUES (?, ?, ?)")
        ) {
            ps.setString(1, visitor.getName());
            ps.setString(2, visitor.getPhone());
            ps.setInt(3, placeId);
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return visitor;
    }

    private int getPlaceId(Place place) {
        int result = 0;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT id FROM hall WHERE row = (?) AND place = (?)")
        ) {
            ps.setInt(1, place.getRow());
            ps.setInt(2, place.getPlace());
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    result = it.getInt("id");
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
