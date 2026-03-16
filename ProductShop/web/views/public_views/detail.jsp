<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/detail.css">
<title>Detail</title>
<c:choose>

    <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
        <%@ include file="header.jspf" %>
        <%@ include file="navbar.jspf" %>
    </c:when>

    <c:otherwise>
        <%@ include file="/views/private_views/private_header.jspf" %>
    </c:otherwise>

</c:choose>

<div>

    <div class="detail-wrapper">

        <!-- PRODUCT IMAGE -->
        <div class="detail-img">
            <img src="${pageContext.request.contextPath}/${product.productImage}">
        </div>

        <!-- PRODUCT INFO -->
        <div class="detail-info">

            <h1 class="detail-name">
                ${product.productName}
            </h1>

            <!-- PRICE -->
            <div class="price-area">

                <c:if test="${product.discount > 0}">
                    <span class="old-price">
                        <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/> đ
                    </span>
                </c:if>

                <span class="new-price">
                    <fmt:formatNumber 
                        value="${product.price - (product.price * product.discount / 100)}"
                        type="number"
                        groupingUsed="true"/> đ
                </span>

            </div>

            <!-- DESCRIPTION -->
            <div class="detail-desc">
                ${product.brief}
            </div>

            <!-- ADD CART -->
            <div class="add-cart-area">

                <form action="main_controller?action=addToCart" method="post">

                    <input type="hidden" name="action" value="addToCart">
                    <input type="hidden" name="productId" value="${product.productId}">

                    <div class="quantity-box">
                        <span>Quantity</span>

                        <div class="quantity-control">
                            <button type="button" onclick="decrease()">−</button>

                            <input id="qty" type="text" name="quantity" value="1" min="1">

                            <button type="button" onclick="increase()">+</button>
                        </div>
                    </div>

                    <c:choose>

                        <c:when test="${not empty sessionScope.user}">
                            <button class="add-cart-btn">
                                Add to Cart
                            </button>
                        </c:when>

                        <c:otherwise>
                            <a href="main_controller?action=login" class="add-cart-btn">
                                Add to Cart
                            </a>
                        </c:otherwise>

                    </c:choose>

                </form>
                <c:if test="${not empty sessionScope.message}">
                    <div class="success-message">
                        ${sessionScope.message}
                    </div>

                    <c:remove var="message" scope="session"/>
                </c:if>

            </div>

        </div>

    </div>
    <%@include file="footer.jspf" %>
</div>
<script src="${pageContext.request.contextPath}/js/quantity.js"></script>
