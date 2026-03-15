<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Category</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/update_account.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>
        <%@ include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Update Category</h2>
                <p>Edit category information</p>
            </div>

            <div class="card">

                <form action="main_controller?action=private_updateCategory" method="post" class="form-layout">

                    <!-- Type ID (readonly) -->
                    <div class="form-group">
                        <label>Type ID</label>
                        <input type="text" name="typeId"
                               value="${param.typeId != null ? param.typeId : cate.typeId}"
                               readonly>
                    </div>

                    <!-- Category Name -->
                    <div class="form-group">
                        <label>Category Name</label>
                        <input type="text" name="categoryName"
                               value="${param.categoryName != null ? param.categoryName : cate.categoryName}">
                        <span class="error">${category_error.categoryNameError}</span>
                    </div>

                    <div class="form-group">
                        <label>Memo</label>
                        <input type="text" name="memo"
                               value="${param.memo != null ? param.memo : cate.memo}">
                        <span class="error">${category_error.memoError}</span>
                    </div>

                    <!-- Buttons -->
                    <div class="form-footer">
                        <button type="submit" class="btn btn-success">Update Category</button>
                        <a href="main_controller?action=private_categories" class="btn btn-secondary">Cancel</a>
                    </div>

                </form>

            </div>
        </div>

    </body>
</html>