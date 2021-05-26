<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <title>Hello, Tomcat@9</title>
</head>
<body>
<div class="container">

    <div class="field">
        <label class="label" class="label" for="userId">Id</label>
        <input class="input" class="input" type="text" id="userId" value="${user.id}" readonly>
    </div>
    <div class="field">
        <label class="label" for="userEmail">Email</label>
        <input class="input" type="text" id="userEmail" value="${user.email}" readonly>
    </div>
    <div class="field">
        <label class="label" for="userPassword">Password</label>
        <input class="input" type="text" id="userPassword" value="${user.password}" readonly>
    </div>
    <div class="field">
        <label class="label" for="userName">Name</label>
        <input class="input" type="text" id="userName" value="${user.name}" readonly>
    </div>
    <div class="field">
        <label class="label" for="userToken">JWT</label>
        <input class="input" type="text" id="userToken" value="${user.token}" readonly>
    </div>

</div>
</body>
</html>


