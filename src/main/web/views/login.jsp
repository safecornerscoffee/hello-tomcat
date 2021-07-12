<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Login | Tomcat@9</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body>
<section class="hero is-primary is-fullheight">
    <div class="hero-body">
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                    <form action="${pageContext.request.contextPath}/login" class="box" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="field">
                            <label class="label" for="username">Username</label>
                            <div class="control has-icons-left">
                                <input class="input" id="username" name="username" placeholder="e.g. cappuccino"
                                       type="text">
                                <span class="icon is-small is-left">
                                    <i class="fa fa-user"></i>
                                </span>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label" for="password">Password</label>
                            <div class="control has-icons-left">
                                <input class="input" placeholder="********" type="password" name="password" id="password">
                                <span class="icon is-small is-left">
                                    <i class="fa fa-lock"></i>
                                  </span>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label">
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                        <div class="field is-grouped is-grouped-centered">
                            <div class="control">
                                <button class="button is-success" type="submit">Login</button>
                            </div>
                            <div class="control">
                                <button class="button is-light">
                                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                                </button>
                            </div>
                        </div>
                    </form>

                    <div class="box has-text-centered">
                        New to Tomcat@9? <a class="has-text-info" href="${pageContext.request.contextPath}/join">Create an account.</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
