<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Edit user | Tomcat@9</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body>
<div class="navbar has-shadow">
    <div class="navbar-brand">
        <a class="navbar-item" href="${pageContext.request.contextPath}/">Home</a>
    </div><!--navbar-brand-->
    <div class="navbar-menu is-active">
        <div class="navbar-start">
            <a class="navbar-item" href="${pageContext.request.contextPath}/users">
                Users
            </a>
            <a class="navbar-item" href="${pageContext.request.contextPath}/articles">
                Articles
            </a>
        </div><!--navbar-start-->

        <div class="navbar-end">
            <a class="navbar-item" href="${pageContext.request.contextPath}/login">Sign in</a>
        </div>
    </div><!--navbar-menu-->
</div><!--navbar-->

<section class="section">
    <div class="columns">
        <div class="column">
            <nav class="breadcrumb">
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/users">Users</a>
                    </li>
                    <li class="is-active">
                        <a href="${pageContext.request.contextPath}/users/${user.id}">Edit user</a>
                    </li>
                </ul>
            </nav> <!-- breadcrumb -->
            <h1 class="title is-1">
                <span class="subtitle is-3 has-text-grey-light">User#</span>
                <strong>${user.id}</strong>
            </h1>

            <form action="/users/${user.id}" method="POST">
                <div class="field">
                    <label class="label" for="username">Username</label>
                    <div class="control">
                        <input class="input" id="username" name="username" type="text" value="${user.username}">
                    </div>
                </div>
                <div class="field">
                    <label for="email" class="label">Email</label>
                    <div class="control">
                        <input class="input" type="email" id="email" value="${user.email}">
                    </div>
                </div>
                <div class="field">
                    <label class="label" for="password">Password</label>
                    <div class="control">
                        <input  class="input" type="text" id="password" value="${user.password}" readonly>
                    </div>
                </div>
                <div class="field is-grouped is-grouped-centered">
                    <div class="control">
                        <button class="button is-success">Save changes</button>
                    </div>
                    <div class="control">
                        <a class="button is-light" href="${pageContext.request.contextPath}/users">Cancel</a>
                    </div>
                </div>
            </form>
        </div><!--column-->
    </div><!--columns-->
</section><!--section-->
</body>
</html>