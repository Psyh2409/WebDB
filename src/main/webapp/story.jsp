<%--
  Created by IntelliJ IDEA.
  User: Psyh
  Date: 09-Dec-20
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<h3>Dear ${client.name}!</h3>
<h2>This is a growing story of your rich transactions:</h2>
<h3>
    <c:forEach items="${client.transactions}" var="transaction" varStatus="status">
        <tr>
            <td> Your account (<c:out value="${transaction.accountNumber}"/>)<br></td>
            <td> <c:out value="${transaction.dateTime}"/> was
                <c:out value="${transaction.action}"/>ED with<br></td>
            <td> <c:out value="${transaction.amount}"/> <c:out value="${transaction.accCurrency}"/><br></td>
                by <c:out value="${transaction.target}"/>. So, now its amount is:<br></td>
            <td> <c:out value="${transaction.rest}"/> <c:out value="${transaction.accCurrency}"/><br></td>
        </tr>
        <br>
    </c:forEach>
</h3>
<h2>
    <form action="/home" method="post">
        <input type="hidden" name="clientId" value="${client.id}">
        <input type="hidden" name="rateId" value="${rate.id}">
        <input type="submit" value="Go home!"><br>
    </form>
</h2>

</body>
</html>
