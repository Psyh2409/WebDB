package com.gmail.psyh2409.servlets;

import com.gmail.psyh2409.dao.ClientDAO;
import com.gmail.psyh2409.entities.Account;
import com.gmail.psyh2409.entities.Client;
import com.gmail.psyh2409.utils.Currency;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ClientServlet", urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {
    Client resultClient;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Client client = new Client(name);
        for (Currency c: Currency.values()) {
            client.getAccounts().add(new Account(c, new BigDecimal(0), client));
        }
        ClientDAO cdao = new ClientDAO();
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        cdao.add(emf, client);
        resultClient  = cdao.getById(emf, Client.class, client.getId());
        resp.sendRedirect("/clients");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", resultClient);
        getServletContext().getRequestDispatcher("/client.jsp").forward(req, resp);
    }
}
