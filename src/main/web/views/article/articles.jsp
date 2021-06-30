<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Articles | Tomcat@9</title>
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
            <h1 class="title">Articles</h1>

            <nav class="level">
                <div class="level-left">
                    <p class="level-item">
                        <a class="button is-success" href="${pageContext.request.contextPath}/articles/new">New</a>
                    </p>

                    <div class="level-item is-hidden-tablet-only">
                        <div class="field has-addons">
                            <p class="control">
                                <input class="input" placeholder="Title, tag..." type="text">
                            </p>
                            <p class="control">
                                <button class="button">Search</button>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="level-right">
                    <div class="level-item">
                        Order by
                    </div>
                    <div class="level-item">
                        <div class="select">
                            <select>
                                <option>Date</option>
                                <option>Page count</option>
                            </select>
                        </div>
                    </div>
                </div>
            </nav><!-- toolbar -->

            <table class="table is-hoverable is-fullwidth">
                <thead>
                <tr>
                    <th class="is-narrow">Article #</th>
                    <th>Title</th>
                    <th class="is-narrow">Author</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        787352
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/articles/article-id">Hello, World!</a>
                    </td>
                    <td>Cappuccino</td>
                </tbody>
            </table>

            <nav class="pagination">
                <a class="pagination-previous" href="#">Previous</a>
                <a class="pagination-next" href="#">Next page</a>
                <ul class="pagination-list">
                    <li>
                        <a class="pagination-link" href="#">1</a>
                    </li>
                    <li>
                        <span class="pagination-ellipsis">&hellip;</span>
                    </li>
                    <li>
                        <a class="pagination-link" href="#">45</a>
                    </li>
                    <li>
                        <a class="pagination-link is-current" href="#">46</a>
                    </li>
                    <li>
                        <a class="pagination-link" href="#">47</a>
                    </li>
                    <li>
                        <span class="pagination-ellipsis">&hellip;</span>
                    </li>
                    <li>
                        <a class="pagination-link" href="#">86</a>
                    </li>
                </ul>
            </nav><!--pagination-->
        </div><!--column-->
    </div><!--columns-->
</section><!--section-->
</body>
</html>