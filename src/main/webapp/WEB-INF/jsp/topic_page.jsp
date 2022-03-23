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

</body>
</html>
