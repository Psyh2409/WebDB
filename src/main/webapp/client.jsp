<%--
  Created by IntelliJ IDEA.
  User: Psyh
  Date: 06-Dec-20
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Client</title>
</head>
<body>
<h3>Watch me, ${client.name}!</h3>
<h2>Your accounts was registered:</h2>
<h3>
    <tr>
        <form action="/accounts" method="post">
            <c:forEach items="${client.accounts}" var="account" varStatus="vs">
                <td> Account number: <c:out value="${account.number}"/><br></td>
                <td> Currency type: <c:out value="${account.currency}"/><br></td>
                <td> Amount: <c:out value="${account.amount}"/><br></td>
                    <input type="hidden" name="number${vs.index}" value="${account.number}">
                    <input type="hidden" name="clientId" value="${client.id}">
                    Enter sum for depose: <input type="number" name="money${vs.index}"><br>
                <br>
            </c:forEach>
            <br><input type="submit" value="depose"><br>
        </form>
    </tr>
    <br>

<%--    <c:forEach items="${client.accounts}" var="account">--%>
<%--        <tr>--%>
<%--            <td> Account number: <c:out value="${account.number}"/><br></td>--%>
<%--            <td> Currency type: <c:out value="${account.currency}"/><br></td>--%>
<%--            <td> Amount: <c:out value="${account.amount}"/><br></td>--%>
<%--            <form action="/accounts" method="post">--%>
<%--                <input type="hidden" name="number" value="${account.number}">--%>
<%--                <input type="hidden" name="clientId" value="${client.id}">--%>
<%--                Enter sum for depose: <input type="number" name="money"><br>--%>
<%--                <input type="submit" value="depose"><br>--%>
<%--            </form>--%>
<%--        </tr>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>
</h3>
</body>
</html>
