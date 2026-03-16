<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Edit Profile</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/public_css/update_profile.css">

</head>

<body>

<%@include file="header.jspf" %>

<%@include file="navbar.jspf" %>


<div class="profile-wrapper">

    <div class="profile-card">

        <h2 class="profile-title">
            <i class="fa-solid fa-user"></i>
            Edit Profile
        </h2>


        <form action="main_controller?action=updateProfile"
              method="post">

            <div class="profile-info">


                <!-- ACCOUNT -->

                <div class="profile-row">

                    <label>Account</label>

                    <input type="text"
                           name="account"
                           value="${param.account != null ? param.account : sessionScope.user.account}"
                           readonly>

                </div>


                <!-- PASSWORD -->

                <div class="profile-row">

                    <label>Password</label>

                    <input type="password"
                           name="pass"
                           placeholder="Enter new password (leave blank to keep old)">

                    <c:if test="${not empty account_error.passError}">
                        <span class="error">${account_error.passError}</span>
                    </c:if>

                </div>


                <!-- FIRST NAME -->

                <div class="profile-row">

                    <label>First Name</label>

                    <input type="text"
                           name="firstName"
                           value="${param.firstName != null ? param.firstName : sessionScope.user.firstName}">

                    <c:if test="${not empty account_error.firstNameError}">
                        <span class="error">${account_error.firstNameError}</span>
                    </c:if>

                </div>


                <!-- LAST NAME -->

                <div class="profile-row">

                    <label>Last Name</label>

                    <input type="text"
                           name="lastName"
                           value="${param.lastName != null ? param.lastName : sessionScope.user.lastName}">

                    <c:if test="${not empty account_error.lastNameError}">
                        <span class="error">${account_error.lastNameError}</span>
                    </c:if>

                </div>


                <!-- PHONE -->

                <div class="profile-row">

                    <label>Phone</label>

                    <input type="text"
                           name="phone"
                           value="${param.phone != null ? param.phone : sessionScope.user.phone}">

                    <c:if test="${not empty account_error.phoneError}">
                        <span class="error">${account_error.phoneError}</span>
                    </c:if>

                </div>


                <!-- BIRTHDAY -->

                <div class="profile-row">

                    <label>Birthday</label>

                    <input type="date"
                           name="birthday"
                           value="${param.birthday != null ? param.birthday : sessionScope.user.birthday}">

                    <c:if test="${not empty account_error.birthdayError}">
                        <span class="error">${account_error.birthdayError}</span>
                    </c:if>

                </div>


                <!-- GENDER -->

                <div class="profile-row">

                    <label>Gender</label>

                    <div class="radio-group">

                        <label>
                            <input type="radio"
                                   name="gender"
                                   value="1"

                            <c:if test="${(param.gender != null && param.gender == true)
                                          || (param.gender == null && sessionScope.user.gender == true)}">
                                checked
                            </c:if>>
                            Male
                        </label>

                        <label>
                            <input type="radio"
                                   name="gender"
                                   value="0"

                            <c:if test="${(param.gender != null && param.gender == false)
                                          || (param.gender == null && sessionScope.user.gender == false)}">
                                checked
                            </c:if>>
                            Female
                        </label>

                    </div>

                </div>

            </div>


            <div class="profile-actions">

                <button type="submit" class="btn-edit">

                    <i class="fa-solid fa-floppy-disk"></i>
                    Update Profile

                </button>
                <a href="main_controller?action=profile" class="btn btn-secondary">Cancel</a>

            </div>

        </form>

    </div>

</div>


<%@include file="footer.jspf" %>

</body>

</html>