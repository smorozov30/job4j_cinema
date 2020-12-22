package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.services.MemService;
import ru.job4j.services.PsqlService;
import ru.job4j.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlService.class)
public class HallServletTest {

    @Test
    public void whenDoGetOccupiedPlacesItReturnsOneAsJson() throws ServletException, IOException {
        Service stubService = MemService.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();

        PowerMockito.mockStatic(PsqlService.class);
        Mockito.when(PsqlService.instOf()).thenReturn(stubService);
        Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(writer));

        new HallServlet().doGet(req, resp);

        Mockito.verify(resp).getWriter();
        assertThat(writer.toString(), is("[{\"row\":1,\"place\":3}]"));
    }
}