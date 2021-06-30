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
    <div class="columns is-centered">
        <div class="column is-12-tablet is-8-desktop is-6-widescreen">

            <form action="${pageContext.request.contextPath}/join" method="POST">
                <div class="field">
                    <label class="label" for="username">Username</label>
                    <div class="control">
                        <input class="input" id="username" name="username" type="text">
                    </div>
                </div><!--field#username-->
                <div class="field">
                    <label class="label" for="email">Email</label>
                    <div class="control">
                        <input class="input" id="email" name="email" type="email">
                    </div>
                </div><!--field#email-->
                <div class="field">
                    <label class="label" for="password">Password</label>
                    <div class="control">
                        <input class="input" id="password" name="password" type="password">
                    </div>
                </div><!--field#password!-->
                <div class="field">
                    <label class="label" for="profileName">Profile Name</label>
                    <div class="control">
                        <input class="input" id="profileName" name="profileName" type="text">
                    </div>
                </div><!--field#profileName-->

                <div class="field">
                    <label class="label">Profile image</label>
                    <div class="control">
                        <div class="file has-name">
                            <label class="file-label">
                                <input class="file-input" id="profileImage" name="profileImage" type="file">
                                <span class="file-cta">
                                    <span class="file-icon">
                                      <i class="fa fa-upload"></i>
                                    </span>
                                    <span class="file-label">
                                      Choose a file...
                                    </span>
                                </span>
                                <span class="file-name">
                                    No file chosen
                                </span>
                            </label>
                        </div>
                    </div>
                </div><!--field#profileImage-->

                <div class="field is-grouped is-grouped-centered">
                    <button type="submit" class="button is-fullwidth is-success">Create account</button>
                </div>
            </form>
        </div>
    </div>
</section><!--section-->
</body>
</html>
