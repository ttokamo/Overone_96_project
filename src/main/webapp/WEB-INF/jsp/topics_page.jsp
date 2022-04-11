<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<a href="/create-topic">
    Создать обсуждение
</a>
<br><br>

<c:if test="${thisIsCreationTopicStage == false}">
    <c:forEach items="${topicsList}" var="topic">
        <a href="/user/${topic.authorId}">
                ${topic.author}<br>
        </a>

        <a href="/topic/${topic.id}">
                ${topic.topicName}<br>
                ${topic.message}<br>
                ${topic.createDate}
        </a><br><br>
    </c:forEach>
</c:if>

<c:if test="${thisIsCreationTopicStage == true}">
    <form method="post" action="/create-topic" enctype="multipart/form-data">
        <input type="text" name="topicName" placeholder="Topic name" autofocus><br>
        <input type="text" name="topicMessage" placeholder="Topic message"><br>
        <input type="file" name="topicImage"><br>
        <button type="submit">Создать обсуждение</button>
        <br>
    </form>
</c:if>


</body>
</html>
