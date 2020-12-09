package com.gmail.psyh2409.servlets;

import com.gmail.psyh2409.dao.ClientDAO;
import com.gmail.psyh2409.dao.RateDAO;
import com.gmail.psyh2409.entities.Client;
import com.gmail.psyh2409.entities.Rate;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    Client client;
    Rate rate;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("clientId"));
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ClientDAO cdao = new ClientDAO();
        client = cdao.getById(emf, Client.class, id);
        rate = new Rate();
        rate.setEur(34.26);
        rate.setUsd(28.24);
        RateDAO rdao = new RateDAO();
        rdao.add(emf, rate);
        rate = rdao.getById(emf, Rate.class, rate.getId());
        resp.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", client);
        req.setAttribute("rate", rate);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
