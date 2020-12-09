package com.gmail.psyh2409.servlets;

import com.gmail.psyh2409.dao.AccountDAO;
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
import java.math.RoundingMode;
import java.time.LocalDate;

@WebServlet(name = "Exchange", urlPatterns = "/exchange")
public class ExchangeServlet extends HttpServlet {
    Client client;
    Rate rate;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        Long to = Long.valueOf(req.getParameter("to"));
        Long from = Long.valueOf(req.getParameter("from"));
        BigDecimal amountFrom = new BigDecimal(req.getParameter("money"));
        ClientDAO cdao = new ClientDAO();
        client = cdao.getById(emf, Client.class, Long.valueOf(req.getParameter("clientId")));
        AccountDAO adao = new AccountDAO();
        Account acTo = adao.getByNumber(emf, to);
        Account acFrom = adao.getByNumber(emf, from);
        Currency curTo = acTo.getCurrency();
        Currency curFrom = acFrom.getCurrency();
        RateDAO rdao = new RateDAO();
        rate = rdao.getForDate(emf, LocalDate.now());
        TransactionDAO tdao = new TransactionDAO();
        BigDecimal amountTo = tdao.exchange(curTo, curFrom, amountFrom, rate);
        adao.transferMoney(emf, acTo, acFrom, amountTo, amountFrom);
        client = cdao.getById(emf, Client.class, client.getId());
        resp.sendRedirect("/exchange");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", client);
        req.setAttribute("rate", rate);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
