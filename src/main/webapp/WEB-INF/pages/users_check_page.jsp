<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <spring:form modelAttribute="userFromServer" method="post" action="/users/check">
        <spring:input path="name"/>
        <spring:input path="password"/>
        <spring:button name="Validate user">Check user</spring:button>
    </spring:form>
</body>
</html>