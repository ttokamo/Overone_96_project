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

<form method="post" action="/add-photo" enctype="multipart/form-data">
    Выберите фото<br>
    <input type="file" name="image"><br>
    <button type="submit">Сохранить</button>
</form>

</body>
</html>
