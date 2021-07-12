<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
      <a class="navbar-item" onclick="document.querySelector('#logoutForm').submit();">Sign out</a>
      <form action="/logout" method="post" id="logoutForm" style="visibility: hidden">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </div>
  </div><!--navbar-menu-->
</div><!--navbar-->

<section class="section">
  <div class="columns">
    <div class="column is-centered">
      <h1 class="title">Index Page</h1>
      <sec:authorize access="hasRole('ROLE_USER')">
        <p>Only Visible to ROLE_USER</p>
      </sec:authorize>

      <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p>Only Visible to ROLE_ADMIN</p>
      </sec:authorize>

      <sec:authorize access="isAuthenticated()">
        <p>Visible to Authenticated User</p>
      </sec:authorize>

      <sec:authorize access="isAnonymous()">
        <p>Visible to Anonymous</p>
      </sec:authorize>
    </div><!--column-->
  </div><!--columns-->
</section><!--section-->
</body>
</html>
