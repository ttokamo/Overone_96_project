<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/user/${userId}">
    Профиль
</a>

<a href="/logout">
    Выход
</a>

<c:forEach items="${messagesList}" var="message">
    ${message.userNickname} <br>
    ${message.text} <br>
    ${message.postTime} <br><br>
</c:forEach>

<form method="post" action="/news">
    <input type="text" name="userMessage"/>
    <button type="submit">Отправить</button>
</form>

</body>
</html>
