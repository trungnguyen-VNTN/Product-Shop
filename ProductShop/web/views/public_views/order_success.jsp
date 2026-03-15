<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/order_success.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">

<c:choose>
    <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
        <%@ include file="header.jspf" %>
        <%@ include file="navbar.jspf" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/views/private_views/private_header.jspf" %>
    </c:otherwise>
</c:choose>


<div class="success-container">

    <div class="success-box">

        <div class="success-icon">
            <i class="fa-solid fa-check"></i>
        </div>

        <h2>Order Placed Successfully!</h2>

        <p>
            Thank you for your purchase.
        </p>

        <p class="order-id">
            Order ID: <b>#${param.orderId}</b>
        </p>

        <div class="success-buttons">

            <a href="${pageContext.request.contextPath}/main_controller?action=home"
               class="btn-continue">
                Continue Shopping
            </a>

            <a href="${pageContext.request.contextPath}/main_controller?action=viewOrder"
               class="btn-orders">
                View My Orders
            </a>

        </div>

    </div>

</div>