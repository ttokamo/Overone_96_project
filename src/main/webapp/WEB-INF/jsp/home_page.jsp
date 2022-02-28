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
<a href="#">
    Выход
</a>
<br> ${username}
</body>
</html>
