<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<br>
<h2>Add client</h2>
<form action="${pageContext.request.contextPath}/clients" method="post">
    Name: <label>
    <input type="text" name="name">
</label><br>
    <br>
    <input type="submit" value="create"><br>
</form>
</body>
</html>
