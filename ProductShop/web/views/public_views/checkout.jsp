<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/checkout.css">
</head>

<body>

<c:choose>
    <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
        <%@ include file="header.jspf" %>
        <%@ include file="navbar.jspf" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/views/private_views/private_header.jspf" %>
    </c:otherwise>
</c:choose>

<div class="checkout-container">


    <h2 class="checkout-title">Checkout</h2>

    <div class="checkout-layout">

        <!-- LEFT : CART SUMMARY -->
        <div class="checkout-products">

            <h3>Order Summary</h3>

            <c:set var="total" value="0"/>

            <c:forEach var="c" items="${cartList}">

                <c:set var="total"
                       value="${total + (c.quantity * (c.productId.price - (c.productId.price * c.productId.discount / 100)))}"/>

                <div class="checkout-item">

                    <img src="${pageContext.request.contextPath}/${c.productId.productImage}"
                         class="checkout-img">

                    <div class="checkout-info">

                        <div class="checkout-name">
                            ${c.productId.productName}
                        </div>

                        <div class="checkout-qty">
                            Quantity: ${c.quantity}
                        </div>

                    </div>

                    <div class="checkout-price">
                        <fmt:formatNumber
                                value="${c.quantity * (c.productId.price - (c.productId.price * c.productId.discount / 100))}"
                                type="number"/> đ
                    </div>

                </div>

            </c:forEach>

        </div>


        <!-- RIGHT : BILLING DETAILS -->

        <div class="billing-box">

            <h3 class="billing-title">Billing Details</h3>

            <div class="checkout-user">
                Ordering as: <b>${sessionScope.user.account}</b>
            </div>

            <form action="main_controller?action=placeOrder"
                  method="post"
                  accept-charset="UTF-8">


                <!-- FULL NAME -->
                <div class="form-group">

                    <label>Full Name</label>

                    <input type="text"
                           value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"
                           readonly
                           class="readonly-input">

                    <small class="form-note">
                        To change your name, please update it in your profile.
                    </small>

                </div>


                <!-- PHONE -->
                <div class="form-group">

                    <label>Phone</label>

                    <input type="text"
                           value="${sessionScope.user.phone}"
                           readonly
                           class="readonly-input">

                    <small class="form-note">
                        To change your phone, please update it in your profile.
                    </small>

                </div>


                <!-- ADDRESS -->
                <div class="form-group">

                    <label>Shipping Address</label>

                    <textarea
                            name="address"
                            required
                            placeholder="Enter your delivery address"></textarea>

                </div>


                <!-- TOTAL -->
                <div class="checkout-total">

                    Total

                    <span>
                        <fmt:formatNumber value="${total}" type="number"/> đ
                    </span>

                </div>


                <button type="submit" class="place-order-btn">
                    Place Order
                </button>

            </form>

        </div>

    </div>

</div>

</body>
</html>
