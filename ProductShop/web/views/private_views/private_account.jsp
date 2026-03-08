<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/account_list.css">
</head>

<body>

    <%@ include file="private_header.jspf" %>

    <div class="page-wrapper">

        <div class="page-title">
            <h2>Account Management</h2>
            <p>List of accounts in system</p>
        </div>

        <div class="card">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Account</th>
                        <th>Full Name</th>
                        <th>Birth Day</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th class="action-col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="acc" items="${account_list}">
                        <tr>
                            <td>${acc.account}</td>

                            <td>
                                ${acc.lastName} ${acc.firstName}
                            </td>

                            <td>
                                <fmt:formatDate 
                                    value="${acc.birthday}" 
                                    pattern="dd/MM/yyyy" />
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${acc.gender == true}">
                                        Male
                                    </c:when>
                                    <c:otherwise>
                                        Female
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>${acc.phone}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${acc.roleInSystem == 1}">
                                        <span class="badge badge-admin">
                                            Administrator
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-staff">
                                            Staff
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${acc.used}">
                                        <span class="badge badge-active">
                                            Active
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-inactive">
                                            Inactive
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td class="actions">

                                <!-- Update -->
                                <a href="main_controller?action=private_updateAccountView&account=${acc.account}"
                                   class="btn btn-primary">
                                    Update
                                </a>

                                <!-- Toggle Status -->
                                <c:choose>
                                    <c:when test="${acc.used}">
                                        <a href="main_controller?action=private_toggleAccountStatus&account=${acc.account}"
                                           class="btn btn-warning">
                                            Deactivate
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="main_controller?action=private_toggleAccountStatus&account=${acc.account}"
                                           class="btn btn-success">
                                            Activate
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Delete -->
                                <a href="main_controller?action=private_deleteAccount&account=${acc.account}"
                                   class="btn btn-danger"
                                   onclick="return confirm('Are you sure to delete this account?')">
                                    Delete
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>

        <div class="card-footer">
            <a href="main_controller?action=private_addAccountView" class="btn btn-add">
                + Add New Account
            </a>
        </div>

    </div>

</body>
</html>