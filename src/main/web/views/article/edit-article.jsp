<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Tomcat@9</title>
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
                        <a href="${pageContext.request.contextPath}/articles">Articles</a>
                    </li>
                    <li class="is-active">
                        <a href="${pageContext.request.contextPath}/articles/new">Edit article</a>
                    </li>
                </ul>
            </nav> <!-- breadcrumb -->

            <form action="" method="POST">
                <div class="field">
                    <label class="label" for="title">Title</label>
                    <div class="control">
                        <input class="input" id="title" name="title" type="text">
                    </div>
                </div>
                <div class="field">
                    <label class="label" for="body">Body</label>
                    <div class="control">
                        <textarea class="textarea" id="body"></textarea>
                    </div>
                </div>
                <div class="field is-grouped is-grouped-centered">
                    <div class="control">
                        <button class="button is-success">Save changes</button>
                    </div>
                    <div class="control">
                        <button class="button is-light">Cancel</button>
                    </div>
                </div>
            </form>
        </div><!--column-->
    </div><!--columns-->
</section><!--section-->
</body>
</html>