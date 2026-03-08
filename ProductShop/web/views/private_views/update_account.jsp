<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/add_account.css">
</head>

<body>

<%@include file="private_header.jspf" %>

<div class="page-wrapper">

    <div class="page-title">
        <h2>Update Account</h2>
        <p>Edit account information</p>
    </div>

    <div class="card">

        <form action="main_controller?action=private_updateAccount" method="post" class="form-layout">

            <!-- Account (readonly) -->
            <div class="form-group">
                <label>Account</label>
                <input type="text" name="account"
                       value="${param.account != null ? param.account : acc.account}"
                       readonly>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="pass"
                       placeholder="Enter new password (leave blank to keep old)">
                <span class="error">${account_error.passError}</span>
            </div>

            <!-- Last Name -->
            <div class="form-group">
                <label>Last Name</label>
                <input type="text" name="lastName"
                       value="${param.lastName != null ? param.lastName : acc.lastName}">
                <span class="error">${account_error.lastNameError}</span>
            </div>

            <!-- First Name -->
            <div class="form-group">
                <label>First Name</label>
                <input type="text" name="firstName"
                       value="${param.firstName != null ? param.firstName : acc.firstName}">
                <span class="error">${account_error.firstNameError}</span>
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label>Phone Number</label>
                <input type="text" name="phone"
                       value="${param.phone != null ? param.phone : acc.phone}">
                <span class="error">${account_error.phoneError}</span>
            </div>

            <!-- Birthday -->
            <div class="form-group">
                <label>Birth Day</label>
                <input type="date" name="birthday"
                       value="${param.birthday != null ? param.birthday : acc.birthday}">
                <span class="error">${account_error.birthdayError}</span>
            </div>

            <!-- Gender -->
            <div class="form-group">
                <label>Gender</label>
                <div class="radio-group">
                    <label>
                        <input type="radio" name="gender" value="1"
                            <c:if test="${(param.gender != null && param.gender == true) || (param.gender == null && acc.gender == true)}">checked</c:if>>
                        Male
                    </label>
                    <label>
                        <input type="radio" name="gender" value="0"
                            <c:if test="${(param.gender != null && param.gender == false) || (param.gender == null && acc.gender == false)}">checked</c:if>>
                        Female
                    </label>
                </div>
            </div>

            <!-- Role -->
            <div class="form-group">
                <label>Role in System</label>
                <select name="roleInSystem">
                    <option value="1"
                        <c:if test="${(param.roleInSystem != null && param.roleInSystem == '1') || (param.roleInSystem == null && acc.roleInSystem == 1)}">selected</c:if>>
                        Administrator
                    </option>
                    <option value="0"
                        <c:if test="${(param.roleInSystem != null && param.roleInSystem == '0') || (param.roleInSystem == null && acc.roleInSystem == 0)}">selected</c:if>>
                        Staff
                    </option>
                </select>
            </div>

            <!-- Active -->
            <div class="form-group checkbox-group">
                <label>
                    <input type="hidden" name="used" value="false">
                    <input type="checkbox" name="used" value="true"
                        <c:if test="${(param.used != null && param.used == 'true') || (param.used == null && acc.used)}">checked</c:if>>
                    Is Active
                </label>
            </div>

            <!-- Buttons -->
            <div class="form-footer">
                <button type="submit" class="btn btn-success">Update Account</button>
                <a href="main_controller?action=private_accounts" class="btn btn-secondary">Cancel</a>
            </div>

        </form>

    </div>
</div>

</body>
</html>