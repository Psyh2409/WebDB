package com.gmail.psyh2409.servlets;

import com.gmail.psyh2409.dao.ClientDAO;
import com.gmail.psyh2409.dao.RateDAO;
import com.gmail.psyh2409.dao.TransactionDAO;
import com.gmail.psyh2409.entities.Account;
import com.gmail.psyh2409.entities.Client;
import com.gmail.psyh2409.entities.Rate;
import com.gmail.psyh2409.utils.Currency;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name="HeapServlet", urlPatterns="/heap")
public class HeapServlet extends HttpServlet {
    Client client;
    Rate rate;
    BigDecimal heap;
    Currency currency;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        Long clientId = Long.valueOf(req.getParameter("clientId"));
        ClientDAO cdao = new ClientDAO();
        client = cdao.getById(emf, Client.class, clientId);
        Long rateId = Long.valueOf(req.getParameter("rateId"));
        RateDAO rdao = new RateDAO();
        rate = rdao.getById(emf, Rate.class, rateId);
        currency = Currency.valueOf(req.getParameter("currency"));
        TransactionDAO tdao = new TransactionDAO();
        heap = new BigDecimal(0);
        for (Account a: client.getAccounts()) {
            BigDecimal bigDecimal = new BigDecimal(0);
            if (a.getCurrency().equals(currency)) {
                bigDecimal = bigDecimal.add(
                        a.getAmount());
            } else {
                bigDecimal = bigDecimal.add(
                        tdao.exchange(currency, a.getCurrency(), a.getAmount(), rate));
            }
            heap = heap.add(bigDecimal);
        }
        resp.sendRedirect("/heap");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", client);
        req.setAttribute("rate", rate);
        req.setAttribute("text", "Heap of your money has:");
        req.setAttribute("heap", heap.setScale(2, BigDecimal.ROUND_HALF_UP));
        req.setAttribute("currency", currency);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
