<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/register.css">
    </head>

    <body>

        <div class="login-wrapper">

            <div class="login-box">

                <h2>Create Account</h2>

                <c:if test="${not empty error}">
                    <div class="error-box">
                        ${error}
                    </div>
                </c:if>

                <form action="main_controller?action=registerAdd" method="post">

                    <label>Account</label>
                    <input type="text" name="account"  value="${param.account}" placeholder="Username" required>
                    <c:if test="${not empty account_error.accountError}">
                        <span class="error">${account_error.accountError}</span>
                    </c:if>

                    <label>Password</label>
                    <input type="password" name="pass" placeholder="Password" required>
                    <c:if test="${not empty account_error.passError}">
                        <span class="error">${account_error.passError}</span>
                    </c:if>

                    <label>First Name</label>
                    <input type="text" name="firstName"  value="${param.firstName}" placeholder="First Name" required>
                    <c:if test="${not empty account_error.firstNameError}">
                        <span class="error">${account_error.firstNameError}</span>
                    </c:if>

                    <label>Last Name</label>
                    <input type="text" name="lastName"  value="${param.lastName}" placeholder="Last Name" required>
                    <c:if test="${not empty account_error.lastNameError}">
                        <span class="error">${account_error.lastNameError}</span>
                    </c:if>

                    <label>Birthday</label>
                    <input type="date" name="birthday"   value="${param.birthday}" required>

                    <label>Gender</label>
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="gender" value="1"
                                   <c:if test="${param.gender == '1' || empty param.gender}">checked</c:if>>
                                   Male
                            </label>
                            <label>
                                <input type="radio" name="gender" value="0"
                                <c:if test="${param.gender == '0'}">checked</c:if>>
                                Female
                            </label>
                        </div>

                        <label>Phone</label>
                        <input type="text" name="phone"  value="${param.phone}" placeholder="Phone Number" required>
                    <c:if test="${not empty account_error.phoneError}">
                        <span class="error">${account_error.phoneError}</span>
                    </c:if>

                    <input type="hidden" name="action" value="register">

                    <button type="submit">REGISTER</button>

                </form>

                <div class="login-link">
                    Already have an account?
                    <a href="main_controller?action=login">Login</a>
                </div>

            </div>

        </div>

    </body>
</html>