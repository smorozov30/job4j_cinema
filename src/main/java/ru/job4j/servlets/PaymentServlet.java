package ru.job4j.servlets;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;
import ru.job4j.services.PsqlService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int row = Integer.parseInt(req.getParameter("row"));
        int place = Integer.parseInt(req.getParameter("place"));
        Place occupiedPlace = new Place(row, place);
        String name = req.getParameter("username");
        String phone = req.getParameter("phone");
        Visitor visitor = new Visitor(name, phone);
        PsqlService.instOf().takePlace(occupiedPlace, visitor);
    }
}
