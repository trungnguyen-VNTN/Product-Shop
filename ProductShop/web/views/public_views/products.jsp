<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/products.css">

    </head>

    <body>

        <div class="container">

            <!-- HEADER -->
            <c:choose>

                <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
                    <%@ include file="header.jspf" %>
                    <%@ include file="navbar.jspf" %>
                </c:when>

                <c:otherwise>
                    <%@ include file="../private_views/private_header.jspf" %>
                </c:otherwise>

            </c:choose>



            <!<!--------------------------------FILTER-------------------------------------->

            <form method="get" action="${pageContext.request.contextPath}/main_controller" class="filter-bar">

                <input type="hidden" name="action" value="productFilter">

                <!-- CATEGORY -->
                <div class="filter-item">

                    <label class="filter-label">
                        <i class="fa-solid fa-layer-group"></i>
                        Category
                    </label>

                    <select name="category" class="filter-select">

                        <option value="">All Categories</option>

                        <c:forEach var="c" items="${categoryList}">
                            <option value="${c.typeId}">
                                ${c.categoryName}
                            </option>
                        </c:forEach>

                    </select>

                </div>


                <!-- PRICE RANGE -->

                <div class="filter-item">

                    <label class="filter-label">
                        <i class="fa-solid fa-dollar-sign"></i>
                        Price
                    </label>

                    <select name="price" class="filter-select">

                        <option value="">All Prices</option>
                        <option value="0-5000000">Under 5M</option>
                        <option value="5000000-15000000">5M - 15M</option>
                        <option value="15000000-999999999">Above 15M</option>

                    </select>

                </div>


                <!-- DISCOUNT -->

                <div class="filter-item">

                    <label class="filter-label">
                        <i class="fa-solid fa-tag"></i>
                        Discount
                    </label>

                    <select name="discount" class="filter-select">

                        <option value="">All</option>
                        <option value="true">On Sale</option>
                        <option value="false">No Discount</option>

                    </select>

                </div>


                <!-- SORT -->

                <div class="filter-item">

                    <label class="filter-label">
                        <i class="fa-solid fa-arrow-down-wide-short"></i>
                        Sort By
                    </label>

                    <select name="sort" class="filter-select">

                        <option value="">Default</option>
                        <option value="priceAsc">Price Low → High</option>
                        <option value="priceDesc">Price High → Low</option>
                        <option value="newest">Newest</option>

                    </select>

                </div>


                <!-- BUTTONS -->

                <div class="filter-actions">

                    <button type="submit" class="filter-btn">

                        <i class="fa-solid fa-filter"></i>
                        Apply

                    </button>

                    <a href="${pageContext.request.contextPath}/main_controller?action=products"
                       class="reset-btn">

                        <i class="fa-solid fa-rotate-right"></i>
                        Reset

                    </a>    

                </div>

            </form>

            <!-------------------------------------------CONTENT------------------------------------------->
            <div class="product-container">

                <c:forEach var="p" items="${productList}">

                    <a href="${pageContext.request.contextPath}/main_controller?action=detail&id=${p.productId}" 
                       class="product-link">

                        <div class="product-card">

                            <c:if test="${p.discount > 0}">
                                <div class="discount">-${p.discount}%</div>
                            </c:if>

                            <div class="img-box">
                                <img src="${pageContext.request.contextPath}/${p.productImage}" class="product-img"/>
                            </div>

                            <div class="product-info">

                                <div class="product-name">
                                    ${p.productName}
                                </div>

                                <div class="price-box">

                                    <c:if test="${p.discount > 0}">
                                        <span class="old-price">
                                            <fmt:formatNumber value="${p.price}" type="number"/> đ
                                        </span>
                                    </c:if>

                                    <span class="new-price">

                                        <fmt:formatNumber 
                                            value="${p.price - (p.price * p.discount / 100)}" 
                                            type="number"/> đ

                                    </span>

                                </div>

                            </div>

                        </div>

                    </a>

                </c:forEach>

            </div>

            <%@include file="footer.jspf" %>

        </div>

        <script src="${pageContext.request.contextPath}/js/slider.js"></script>

    </body>
</html>
