<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/product_list.css">
</head>

<body>

<%@include file="private_header.jspf" %>
<%@ include file="sidebar.jspf" %>

<div class="page-wrapper">

    <div class="page-title">
        <h2>Product Management</h2>
        <p>List of products in system</p>
    </div>

    <div class="card">

        <table class="styled-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Posted Date</th>
                    <th>Unit</th>
                    <th>Price</th>
                    <th>Discount</th>
                    <th class="action-col">Action</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="pro" items="${product_list}">
                    <tr>

                        <td>${pro.productId}</td>

                        <!-- Image -->
                        <td>
                            <img src="${pageContext.request.contextPath}/${pro.productImage}" 
                                 width="60" height="60"
                                 style="object-fit: cover; border-radius: 6px;">
                        </td>

                        <td>${pro.productName}</td>

                        <!-- Category -->
                        <td>${pro.type.categoryName}</td>

                        <!-- Posted Date -->
                        <td>
                            <fmt:formatDate value="${pro.postedDate}" pattern="dd/MM/yyyy"/>
                        </td>

                        <td>${pro.unit}</td>

                        <td>
                            <fmt:formatNumber value="${pro.price}" type="number"/> VND
                        </td>

                        <td>${pro.discount}%</td>

                        <td class="actions">

                            <!-- Update -->
                            <a href="main_controller?action=private_updateProductView&productId=${pro.productId}" 
                               class="btn btn-primary">Update</a>

                            <!-- Delete -->
                            <a href="main_controller?action=private_deleteProduct&productId=${pro.productId}" 
                               class="btn btn-danger"
                               onclick="return confirm('Are you sure to delete this product?')">
                                Delete
                            </a>

                        </td>

                    </tr>
                </c:forEach>
            </tbody>

        </table>

    </div>

    <div class="card-footer">
        <a href="main_controller?action=private_addProductView" class="btn btn-add">
            + Add New Product
        </a>
    </div>

</div>

</body>
</html>