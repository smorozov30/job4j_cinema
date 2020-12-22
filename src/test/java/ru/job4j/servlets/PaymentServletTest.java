package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.models.Place;
import ru.job4j.services.MemService;
import ru.job4j.services.PsqlService;
import ru.job4j.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlService.class)
public class PaymentServletTest {

    @Test
    public void whenTakePlaceThenOccupiedPlacesReturnedSizeTwo() throws ServletException, IOException {
        Service stubService = MemService.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.mockStatic(PsqlService.class);
        when(PsqlService.instOf()).thenReturn(stubService);
        when(req.getParameter("row")).thenReturn("1");
        when(req.getParameter("place")).thenReturn("1");
        when(req.getParameter("username")).thenReturn("Sergey");
        when(req.getParameter("phone")).thenReturn("+7");

        new PaymentServlet().doPost(req, resp);

        List<Place> result = stubService.getOccupiedPlaces();
        assertThat(result.size(), is(2));
    }
}