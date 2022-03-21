<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/registration"/>
</c:if>

<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/admin">
    Админ панель
</a> <br>

<form method="post" action="/admin/add-user">
    <input type="text" name="username" placeholder="Username" autofocus/><br>
    <button type="submit">Сохранить</button>
</form>

</body>
</html>
