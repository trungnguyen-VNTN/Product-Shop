<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Product</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/private_css/add_product.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>
        <%@ include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Add New Product</h2>
                <p>Create a new product in system</p>
            </div>

            <div class="card">

                <form action="main_controller?action=private_addProduct"
                      method="post"
                      enctype="multipart/form-data"
                      class="form-layout">

                    <!-- Product ID -->
                    <div class="form-group">
                        <label>Product ID</label>
                        <input type="text" name="productId"
                               value="${param.productId}"
                               placeholder="Enter product ID" required>
                        <span class="error">${product_error.productIdError}</span>
                    </div>

                    <!-- Product Name -->
                    <div class="form-group">
                        <label>Product Name</label>
                        <input type="text" name="productName"
                               value="${param.productName}"
                               placeholder="Enter product name" required>
                        <span class="error">${product_error.productNameError}</span>
                    </div>

                    <!-- Select Image File -->
                    <div class="form-group">
                        <label>Select Image</label>
                        <input type="file" name="fileImage" accept="image/*" required>
                        <span class="error">${product_error.productImageError}</span>
                    </div>

                    <!-- Brief -->
                    <div class="form-group">
                        <label>Brief</label>
                        <textarea name="brief" class="brief-box" placeholder="Short description" required>${param.brief}</textarea>
                        <span class="error">${product_error.briefError}</span>
                    </div>

                    <!-- Category -->
                    <div class="form-group">
                        <label>Category</label>
                        <select name="typeId">
                            <c:forEach var="cate" items="${category_list}">
                                <option value="${cate.typeId}"
                                        <c:if test="${param.typeId == cate.typeId}">selected</c:if>>
                                    ${cate.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Account -->
                    <div class="form-group">
                        <label>Created By</label>
                        <select name="account">
                            <c:forEach var="acc" items="${account_list}">
                                <option value="${acc.account}"
                                        <c:if test="${param.account == acc.account}">selected</c:if>>
                                    ${acc.account}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Unit -->
                    <div class="form-group">
                        <label>Unit</label>
                        <input type="text" name="unit"
                               value="${param.unit}"
                               placeholder="pcs, box, kg..." required>
                        <span class="error">${product_error.unitError}</span>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" name="price"
                               value="${param.price}"
                               placeholder="Enter price" required>
                        <span class="error">${product_error.priceError}</span>
                    </div>

                    <!-- Discount -->
                    <div class="form-group">
                        <label>Discount (%)</label>
                        <input type="number" name="discount"
                               value="${param.discount}"
                               placeholder="0 - 100" required>
                        <span class="error">${product_error.discountError}</span>
                    </div>

                    <!-- Buttons -->
                    <div class="form-footer">
                        <button type="submit" class="btn btn-success">Create Product</button>
                        <a href="main_controller?action=products" class="btn btn-secondary">Cancel</a>
                    </div>

                </form>

            </div>
        </div>

    </body>
</html>