
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h4>Exchange rates to HRN on ${rate.date}:   USD - ${rate.usd},   EUR - ${rate.eur}</h4>
<h2>
    <form action="/story" method="post">
        <input type="hidden" name="clientId" value="${client.id}">
        <input type="hidden" name="rateId" value="${rate.id}">
        <input type="submit" value="STORY OF YOUR TRANSACTIONS"><br>
    </form>
</h2>
<h2>So, ${client.name},</h2>
<h3>You have next accounts:</h3>
<h4>
    <c:forEach items="${client.accounts}" var="account">
        <tr>
            <td> Account number: <c:out value="${account.number}"/><br></td>
            <td> Amount: <c:out value="${account.amount}"/> <c:out value="${account.currency}"/><br></td>
        </tr>
        <br>
    </c:forEach>
</h4>
<h2>Money transfer</h2>
<form action="/exchange" method="post">
    From account (number):<br>
    <input type="text" name="from"><br>
    To account (number):<br>
    <input type="text" name="to"><br>
    Amount of money:<br>
    <input type="number" name="money"><br>
    <input type="hidden" name="clientId" value="${client.id}">
    <input type="hidden" name="rateId" value="${rate.id}">
    <input type="submit" value="SEND"><br>
</form>
<h2>Amount of client's money in:</h2>
<form action="/heap" method="post">
    <input type="hidden" name="clientId" value="${client.id}">
    <input type="hidden" name="rateId" value="${rate.id}">
    Currency type:<br>
    <input type="radio" name="currency" value="UAH"> UAH<br>
    <input type="radio" name="currency" value="USD"> USD<br>
    <input type="radio" name="currency" value="EUR"> EUR<br>
    <input type="submit" value="GET SUM"><br>
</form>
<h2>
    ${text} ${heap} ${currency}
</h2>
</body>
</html>
