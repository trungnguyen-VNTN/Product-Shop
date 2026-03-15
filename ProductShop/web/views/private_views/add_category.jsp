<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Category</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/add_category.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>
        <%@ include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Add New Category</h2>
                <p>Create a new category in system</p>
            </div>

            <div class="card">

                <form action="main_controller?action=private_addCategory"
                      method="post"
                      accept-charset="UTF-8">

                    <div class="form-group">
                        <label>Category Name</label>
                        <input type="text" name="categoryName"
                               value="${param.categoryName}"
                               placeholder="Enter category name">
                        <span class="error">${category_error.categoryNameError}</span>
                    </div>

                    <div class="form-group">
                        <label>Memo</label>
                        <input type="text" name="memo"
                               value="${param.memo}"
                               placeholder="Enter memo">
                        <span class="error">${category_error.memoError}</span>
                    </div>

                    <div class="form-footer">
                        <button type="submit" class="btn btn-success">Create Category</button>
                        <a href="main_controller?action=categories" class="btn btn-secondary">Cancel</a>
                    </div>

                </form>

            </div>
        </div>

    </body>
</html>