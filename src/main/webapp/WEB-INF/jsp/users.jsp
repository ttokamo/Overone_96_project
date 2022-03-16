<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userId == null}">
    <c:redirect url="/registration"/>
</c:if>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/user/${userId}">
    Профиль
</a>

<form method="post" action="/users">
    <input type="text" name="userSearch"/>
    <button type="submit">Найти</button>
</form>
<br><br>

<c:forEach items="${usersList}" var="user">

    <c:if test="${userId != user.id}">
        <a href="/user/${user.id}">
                ${user.username} <br>
        </a>
    </c:if>
</c:forEach>

</body>
</html>
