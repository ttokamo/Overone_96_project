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

<c:forEach items="${userList}" var="user">
    <a href="/user/${user.id}">
            ${user.username}
    </a>

    <a href="/delete/${user.id}">
        Delete
    </a>


    <c:if test="${user.status == 'ACTIVE'}">
        <a href="/block/${user.id}">
            Block
        </a> <br>
    </c:if>

    <c:if test="${user.status == 'BLOCKED'}">
        <a href="/unblock/${user.id}">
            Unblock
        </a> <br>
    </c:if>

</c:forEach>

</body>
</html>
