<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">
<title>Cart</title>
<c:choose>
    <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
        <%@ include file="header.jspf" %>
        <%@ include file="navbar.jspf" %>
    </c:when>

    <c:otherwise>
        <%@ include file="/views/private_views/private_header.jspf" %>
    </c:otherwise>
</c:choose>
<!<!------------------- content ----------------------------->

<div class="cart-container">
    <c:choose>
        <c:when test="${empty cartList}">

            <div class="empty-cart">

                <h2>Your cart is empty</h2>

                <a href="${pageContext.request.contextPath}/main_controller?action=home" class="continue-shopping">
                    Continue Shopping
                </a>

            </div>

        </c:when>

        <c:otherwise>

            <div class="cart-title">
                <h2>Your Shopping Cart</h2>
                <span>${cartList.size()} items</span>
            </div>

            <div class="cart-header">
                <div class="col-product">Product</div>
                <div class="col-price">Price</div>
                <div class="col-quantity">Quantity</div>
                <div class="col-total">Total</div>
                <div class="col-remove"></div>
            </div>


            <c:set var="total" value="0"/>
            <c:forEach var="c" items="${cartList}">
                <c:set var="total" value="${total + (c.quantity * (c.productId.price - (c.productId.price * (c.productId.discount) / 100)))}"/>
                <div class="cart-item">

                    <!-- PRODUCT -->
                    <div class="col-product product-box">

                        <div class="cart-img">
                            <img src="${pageContext.request.contextPath}/${c.productId.productImage}">
                        </div>

                        <div class="cart-name">
                            ${c.productId.productName}
                        </div>

                    </div>

                    <!-- PRICE -->
                    <div class="col-price price">

                        <c:choose>

                            <c:when test="${c.productId.discount > 0}">

                                <div class="price-box">

                                    <span class="old-price">
                                        <fmt:formatNumber value="${c.productId.price}" type="number"/> đ
                                    </span>

                                    <span class="new-price">
                                        <fmt:formatNumber 
                                            value="${c.productId.price - (c.productId.price * c.productId.discount / 100)}"
                                            type="number"/> đ
                                    </span>

                                    <span class="discount-badge">
                                        -${c.productId.discount}%
                                    </span>

                                </div>

                            </c:when>

                            <c:otherwise>

                                <fmt:formatNumber value="${c.productId.price}" type="number"/> đ

                            </c:otherwise>

                        </c:choose>

                    </div>


                    <!-- QUANTITY -->
                    <div class="col-quantity">
                        <div class="cart-quantity">

                            <!-- MINUS -->
                            <form class="qty-form"
                                  action="${pageContext.request.contextPath}/main_controller?action=updateCartQuantity&productId=${c.productId.productId}&cartId=${c.cartId}&type=minus"
                                  method="post">
                                <button class="qty-btn">−</button>
                            </form>

                            <input class="qty-input" type="text" value="${c.quantity}" readonly>

                            <!-- PLUS -->
                            <form class="qty-form"
                                  action="${pageContext.request.contextPath}/main_controller?action=updateCartQuantity&productId=${c.productId.productId}&cartId=${c.cartId}&type=plus"
                                  method="post">
                                <button class="qty-btn">+</button>
                            </form>

                        </div>

                    </div>

                    <!-- TOTAL -->
                    <div class="col-total total">

                        <fmt:formatNumber 
                            value="${c.quantity *  (c.productId.price - (c.productId.price * (c.productId.discount) / 100))}" 
                            type="number"/> đ   

                    </div>

                    <!-- DELETE -->
                    <div class="col-remove">

                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/main_controller?action=deleteCart&productId=${c.productId.productId}&cartId=${c.cartId}">
                            <i class="fa-solid fa-trash-can"></i>
                        </a>

                    </div>

                </div>

            </c:forEach>


            <!-- CART SUMMARY -->

            <div class="cart-summary">

                <div class="summary-total">

                    Total: 
                    <span>
                        <fmt:formatNumber value="${total}" type="number"/> đ
                    </span>

                </div>

                <a href="${pageContext.request.contextPath}/main_controller?action=checkout" class="checkout-btn">
                    Proceed to Checkout
                </a>

            </div>

        </c:otherwise>

    </c:choose>
</div>
<%@include file="footer.jspf" %>


