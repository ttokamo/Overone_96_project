<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="topic" value="${topic}"/>
${topic.get().author}<br>
${topic.get().topicName}<br>
${topic.get().message}<br>
${topic.get().createDate}<br><br>

<form method="post" action="/add-comment/${topic.get().id}">
    <input type="text" name="comment" placeholder="Input comment here..." autofocus>
    <button type="submit">Отправить</button>
</form>
<br><br>

<c:forEach items="${comments}" var="comment">
    <a href="/user/${comment.authorId}">
            ${comment.authorUsername}
    </a><br>
    ${comment.comment}<br>
    ${comment.createdDate}


    <c:if test="${userId == comment.authorId || role == 'ADMIN'}">
        <form method="post" action="/topic/${comment.topicId}">
            <button type="submit" name="deleteComment" value="${comment.id}">Удалить</button>
        </form>
    </c:if>

    <br>
</c:forEach><br>

</body>
</html>
