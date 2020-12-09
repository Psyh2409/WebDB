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

@WebServlet (name = "StoryServlet", urlPatterns = "/story")
public class StoryServlet extends HttpServlet {
    Client client;
    Rate rate;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        Long clientId = Long.valueOf(req.getParameter("clientId"));
        ClientDAO cdao = new ClientDAO();
        client = cdao.getById(emf, Client.class, clientId);
        Long rateId = Long.valueOf(req.getParameter("rateId"));
        RateDAO rdao = new RateDAO();
        rate = rdao.getById(emf, Rate.class, rateId);
        resp.sendRedirect("/story");
//        resp.getWriter().println(
//                "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>\n" +
//                "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "    <title>Account</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h3>Dear ${client.name}!</h3>\n" +
//                "<h2>This is a growing story of your rich transactions:</h2>\n" +
//                "<h3>\n" +
//                "    <c:forEach items=\"${client.transactions}\" var=\"transaction\" varStatus=\"status\">\n" +
//                "        <tr>\n" +
//                "            <td> Your account (<c:out value=\"${transaction.accountNumber}\"/>)<br></td>\n" +
//                "            <td> <c:out value=\"${transaction.dateTime}\"/> was\n" +
//                "                <c:out value=\"${transaction.action}\"/>ED with<br></td>\n" +
//                "            <td> <c:out value=\"${transaction.amount}\"/> <c:out value=\"${transaction.accCurrency}\"/><br></td>\n" +
//                "                by <c:out value=\"${transaction.target}\"/>. So, now its amount is:<br></td>\n" +
//                "            <td> <c:out value=\"${transaction.rest}\"/> <c:out value=\"${transaction.accCurrency}\"/><br></td>\n" +
//                "        </tr>\n" +
//                "        <br>\n" +
//                "    </c:forEach>\n" +
//                "</h3>\n" +
//                "<h2>\n" +
//                "    <form action=\"/home\" method=\"post\">\n" +
//                "        <input type=\"hidden\" name=\"clientId\" value=\"${client.id}\">\n" +
//                "        <input type=\"hidden\" name=\"rateId\" value=\"${rate.id}\">\n" +
//                "        <input type=\"submit\" value=\"Go home!\"><br>\n" +
//                "    </form>\n" +
//                "</h2>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", client);
        req.setAttribute("rate", rate);
        getServletContext().getRequestDispatcher("/story.jsp").forward(req, resp);
    }
}
