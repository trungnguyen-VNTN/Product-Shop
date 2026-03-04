<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Product</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/update_product.css">
    </head>

    <body>

        <%@include file="private_header.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Update Product</h2>
                <p>Edit product information</p>
            </div>

            <div class="card">

                <form action="main_controller?action=updateProduct"
                      method="post"
                      enctype="multipart/form-data"
                      class="form-layout">

                    <!-- Product ID (readonly) -->
                    <div class="form-group">
                        <label>Product ID</label>
                        <input type="text" name="productId"
                               value="${param.productId != null ? param.productId : product.productId}"
                               readonly>
                    </div>

                    <!-- Product Name -->
                    <div class="form-group">
                        <label>Product Name</label>
                        <input type="text" name="productName"
                               value="${param.productName != null ? param.productName : product.productName}">
                        <span class="error">${product_error.productNameError}</span>
                    </div>

                    <!-- Image Upload -->
                    <div class="form-group">
                        <label>Image</label>
                        <input type="file" name="fileImage">
                        <input type="hidden" name="oldImage"
                               value="${product.productImage}">
                    </div>

                    <div class="form-group">
                        <label>Image Name (without extension)</label>
                        <input type="text" name="imageName"
                               value="${param.imageName}"
                               placeholder="example_image">
                    </div>

                    <!-- Brief -->
                    <div class="form-group">
                        <label>Brief</label>
                        <textarea name="brief">${param.brief != null ? param.brief : product.brief}</textarea>
                    </div>

                    <!-- Posted Date -->
                    <div class="form-group">
                        <label>Posted Date</label>
                        <input type="date" name="postedDate"
                               value="${param.postedDate != null ? param.postedDate : product.postedDate}">
                    </div>

                    <!-- Category -->
                    <div class="form-group">
                        <label>Category</label>
                        <select name="typeId">
                            <c:forEach var="c" items="${category_list}">
                                <option value="${c.typeId}"
                                        <c:if test="${(param.typeId != null && param.typeId == c.typeId) 
                                                      || (param.typeId == null && product.type.typeId == c.typeId)}">
                                              selected
                                        </c:if>>
                                    ${c.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Account -->
                    <div class="form-group">
                        <label>Account</label>
                        <select name="account">
                            <c:forEach var="a" items="${account_list}">
                                <option value="${a.account}"
                                        <c:if test="${(param.account != null && param.account == a.account) 
                                                      || (param.account == null && product.account.account == a.account)}">
                                              selected
                                        </c:if>>
                                    ${a.account}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Unit -->
                    <div class="form-group">
                        <label>Unit</label>
                        <input type="text" name="unit"
                               value="${param.unit != null ? param.unit : product.unit}">
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" name="price"
                               value="${param.price != null ? param.price : product.price}">
                        <span class="error">${product_error.priceError}</span>
                    </div>

                    <!-- Discount -->
                    <div class="form-group">
                        <label>Discount (%)</label>
                        <input type="number" name="discount"
                               value="${param.discount != null ? param.discount : product.discount}">
                        <span class="error">${product_error.discountError}</span>
                    </div>

                    <!-- Buttons -->
                    <div class="form-footer">
                        <button type="submit" class="btn btn-success">Update Product</button>
                        <a href="main_controller?action=products" class="btn btn-secondary">Cancel</a>
                    </div>

                </form>

            </div>
        </div>

    </body>
</html>