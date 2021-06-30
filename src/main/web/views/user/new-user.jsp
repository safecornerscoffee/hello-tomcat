<%--
  Created by IntelliJ IDEA.
  User: safecorners
  Date: 6/16/21
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
                    <button type="submit" class="button">Create account</button>
                    <a href="${pageContext.request.contextPath}/users" class="button">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</section><!--section-->
</body>
</html>
