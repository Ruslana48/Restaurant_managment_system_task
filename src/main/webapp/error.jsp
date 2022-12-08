<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<h1 class="display-3">Sorry, something went wrong!</h1>
    <p><%= exception%></p>
    <a href="index.jsp" class="btn-outline-primary">Home page</a>
</body>
</html>
