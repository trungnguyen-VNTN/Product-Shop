<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Category Management</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/category_list.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>
        <%@ include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Category Management</h2>
                <p>List of categories in system</p>
            </div>

            <div class="card">

                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Type ID</th>
                            <th>Category Name</th>
                            <th>Memo</th>
                            <th class="action-col">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="cate" items="${category_list}">
                            <tr>
                                <td>${cate.typeId}</td>
                                <td>${cate.categoryName}</td>
                                <td>${cate.memo}</td>

                                <td class="actions">

                                    <!-- Update -->
                                    <a href="main_controller?action=private_updateCategoryView&typeId=${cate.typeId}" 
                                       class="btn btn-primary">Update</a>

                                    <!-- Delete -->
                                    <a href="main_controller?action=private_deleteCategory&typeId=${cate.typeId}" 
                                       class="btn btn-danger"
                                       onclick="return confirm('Are you sure to delete this category?')">
                                        Delete
                                    </a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </div>

            <div class="card-footer">
                <a href="main_controller?action=private_addCategoryView" class="btn btn-add">
                    + Add New Category
                </a>
            </div>

        </div>

    </body>
</html>