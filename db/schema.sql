CREATE TABLE hall (
      id SERIAL PRIMARY KEY NOT NULL,
      row INTEGER,
      place INTEGER,
      UNIQUE (row, place)
);

INSERT INTO hall(row, place) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3);

CREATE TABLE visitor (
     id SERIAL PRIMARY KEY,
     name TEXT,
     phone TEXT,
     place_id INTEGER REFERENCES hall(id),
     UNIQUE (place_id)
);