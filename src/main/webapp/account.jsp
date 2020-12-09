<%--
  Created by IntelliJ IDEA.
  User: Psyh
  Date: 07-Dec-20
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:useBean id="currencies" class="com.gmail.psyh2409.utils.Currency.values()"></jsp:useBean>--%>
<html>
<head>
    <title>Account</title>
</head>
<body>
<h3>Dear ${client.name}!</h3>
<h2>Your accounts was deposited:</h2>
<h3>
    <c:forEach items="${client.transactions}" var="transaction" varStatus="status">
        <tr>
            <td> Your account (<c:out value="${transaction.accountNumber}"/>)<br></td>
            <td> <c:out value="${transaction.dateTime}"/> was
                <c:out value="${transaction.action}"/>ED with<br></td>
            <td> <c:out value="${transaction.amount}"/> <c:out value="${transaction.accCurrency}"/> and now its amount is:<br></td>
            <td> <c:out value="${transaction.rest}"/> <c:out value="${transaction.accCurrency}"/><br></td>
        </tr>
        <br>
    </c:forEach>
</h3>
<h2>
    <form action="/home" method="post">
        <input type="hidden" name="clientId" value="${client.id}">
        <input type="submit" value="Go home!"><br>
    </form>
</h2>

</body>
</html>
