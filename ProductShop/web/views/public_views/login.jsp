<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/login.css">
    </head>

    <body>

        <div class="login-wrapper">

            <div class="login-box">
                <h2>Login</h2>

                <c:if test="${not empty error}">
                    <div class="error-box">
                        ${error}
                    </div>
                </c:if>

                <form action="login" method="post">
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="hidden" name="action" value="login">
                    <button type="submit">LOGIN</button>
                </form>

                <div class="register-link">
                    Don't have an account?
                    <a href="main_controller?action=register">Register</a>
                </div>
            </div>

        </div>

    </body>
</html>