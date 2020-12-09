package com.gmail.psyh2409.servlets;

import com.gmail.psyh2409.dao.AccountDAO;
import com.gmail.psyh2409.dao.ClientDAO;
import com.gmail.psyh2409.entities.Client;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/accounts")
public class AccountServlet extends HttpServlet {
    Client client;
    List<BigDecimal> bds = new ArrayList<>();
    List<Long> numbers = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        Long clientId = Long.valueOf(req.getParameter("clientId"));
        ClientDAO cdao = new ClientDAO();
        client = cdao.getById(emf, Client.class, clientId);
        AccountDAO adao = new AccountDAO();
        for (int i = 0; i < client.getAccounts().size(); i++) {
            Long num = Long.valueOf(req.getParameter("number"+i));
            BigDecimal bd = new BigDecimal(req.getParameter("money"+i));
            numbers.add(i, num);
            bds.add(i, bd);
            adao.depositAccount(emf, cdao.getById(emf, Client.class, clientId), num, bd, null);
        }
        client = cdao.getById(emf, Client.class, clientId);
        resp.sendRedirect("/accounts");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", client);
        for (int i = 0, n = i; i < client.getAccounts().size(); i++) {
            req.setAttribute("account".concat(String.valueOf(i)),
                    client
                    .getAccounts()
                    .stream()
                    .filter(account -> Objects.equals(numbers.get(n), account.getNumber()))
                    .findAny()
                    .orElse(null)
            );
        }
        getServletContext().getRequestDispatcher("/account.jsp").forward(req, resp);
    }
}
//                        |"""|
//                        (^l^)
//                     p  _ Y _  q
//                     \ /|U U|\ /
//                      V ( * ) V
//                        | М |
//                        d/ \b