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
<a href="/users">
    Список пользователей
</a>
<c:if test="${role == 'ADMIN'}">
    <a href="/admin">
        Админ панель
    </a>
</c:if>
<a href="#">
    Выход
</a>
<br> ${username} <br>


<c:if test="${thisIsMainUser == true}">
    This is main user page
</c:if>

<c:if test="${thisIsMainUser == false}">
    This is not main user page
</c:if>

</body>
</html>
