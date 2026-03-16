<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>My Profile</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/public_css/profile.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/all.min.css">

    </head>

    <body>

        <div class="container">

            <%@include file="header.jspf" %>
            <%@include file="navbar.jspf" %>


            <div class="profile-wrapper">

                <div class="profile-card">

                    <h2 class="profile-title">
                        <i class="fa-solid fa-user"></i>
                        My Profile
                    </h2>


                    <div class="profile-info">

                        <div class="profile-row">
                            <span class="label">Account</span>
                            <span class="value">${sessionScope.user.account}</span>
                        </div>


                        <div class="profile-row">
                            <span class="label">First Name</span>
                            <span class="value">${sessionScope.user.firstName}</span>
                        </div>


                        <div class="profile-row">
                            <span class="label">Last Name</span>
                            <span class="value">${sessionScope.user.lastName}</span>
                        </div>


                        <div class="profile-row">
                            <span class="label">Birthday</span>

                            <span class="value">

                                <fmt:formatDate value="${sessionScope.user.birthday}"
                                                pattern="dd/MM/yyyy"/>

                            </span>
                        </div>


                        <div class="profile-row">
                            <span class="label">Gender</span>

                            <span class="value">

                                <c:choose>

                                    <c:when test="${sessionScope.user.gender == true}">
                                        Male
                                    </c:when>

                                    <c:otherwise>
                                        Female
                                    </c:otherwise>

                                </c:choose>

                            </span>

                        </div>


                        <div class="profile-row">
                            <span class="label">Phone</span>
                            <span class="value">${sessionScope.user.phone}</span>
                        </div>

                    </div>


                    <!-- EDIT BUTTON -->

                    <div class="profile-actions">

                        <a href="main_controller?action=editProfileView&account=${sessionScope.user.account}"
                           class="btn-edit">

                            <i class="fa-solid fa-pen"></i>
                            Edit Profile

                        </a>

                    </div>

                </div>

            </div>


            <%@include file="footer.jspf" %>

        </div>

    </body>

</html>