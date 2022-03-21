<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${role != 'ADMIN'}">
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

<a href="/admin/add-user">
    Добавить пользователя
</a>

<br><br>

<c:forEach items="${userList}" var="user">
    <a href="/user/${user.id}">
            ${user.username}
    </a>


    <form action="/admin-action" method="post">
        <button type="submit" name="adminActionButton" value="DELETE ${user.id}">Delete</button>


        <c:if test="${user.status == 'ACTIVE'}">
            <button type="submit" name="adminActionButton" value="BLOCK ${user.id}">Block</button>
        </c:if>

        <c:if test="${user.status == 'BLOCK'}">
            <button type="submit" name="adminActionButton" value="UNBLOCK ${user.id}">Unblock</button>
            <br>
        </c:if>
    </form>

</c:forEach>

</body>
</html>
