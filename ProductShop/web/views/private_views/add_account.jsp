<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Account</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/add_account.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Add New Account</h2>
                <p>Create a new account in system</p>
            </div>

            <div class="card">

                <form action="main_controller?action=private_addAccount" method="post" class="form-layout">
                    <input type="hidden" name="action" value="addAccount">

                    <!-- Account -->
                    <div class="form-group">
                        <label>Account</label>
                        <input type="text" name="account"
                               value="${param.account}"
                               placeholder="Enter account">
                        <span class="error">${account_error.accountError}</span>
                    </div>

                    <!-- Password -->
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="pass"
                               placeholder="Enter password">
                        <span class="error">${account_error.passError}</span>
                    </div>

                    <!-- Last Name -->
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastName"
                               value="${param.lastName}"
                               placeholder="Enter last name">
                        <span class="error">${account_error.lastNameError}</span>
                    </div>

                    <!-- First Name -->
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstName"
                               value="${param.firstName}"
                               placeholder="Enter first name">
                        <span class="error">${account_error.firstNameError}</span>
                    </div>

                    <!-- Phone -->
                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" name="phone"
                               value="${param.phone}"
                               placeholder="Enter phone number">
                        <span class="error">${account_error.phoneError}</span>
                    </div>

                    <!-- Birthday -->
                    <div class="form-group">
                        <label>Birth Day</label>
                        <input type="date" name="birthday"
                               value="${param.birthday}">
                        <span class="error">${account_error.birthdayError}</span>
                    </div>

                    <!-- Gender -->
                    <div class="form-group">
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
                        </div>

                        <!-- Role -->
                        <div class="form-group">
                            <label>Role in System</label>

                            <select name="roleInSystem">

                                <option value="1"
                                <c:if test="${param.roleInSystem == '1'}">selected</c:if>>
                                    Administrator
                                </option>

                                <option value="2"
                                <c:if test="${param.roleInSystem == '2'}">selected</c:if>>
                                    Staff
                                </option>

                                <option value="0"
                                <c:if test="${param.roleInSystem == '0' || empty param.roleInSystem}">selected</c:if>>
                                    Customer
                                </option>

                            </select>

                        </div>

                        <!-- Active -->
                        <div class="form-group checkbox-group">
                            <label>
                                <input type="hidden" name="used" value="false">
                                <input type="checkbox" name="used" value="true"
                                <c:if test="${param.used == 'true' || empty param.used}">checked</c:if>>
                            Is Active
                        </label>
                    </div>

                    <!-- Buttons -->
                    <div class="form-footer">
                        <button type="submit" class="btn btn-success">Create Account</button>
                        <a href="main_controller?action=accounts" class="btn btn-secondary">Cancel</a>
                    </div>

                </form>

            </div>
        </div>

    </body>
</html>