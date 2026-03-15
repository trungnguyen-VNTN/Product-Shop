<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/view_order_detail.css">

<c:choose>
    <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
        <%@ include file="header.jspf" %>
        <%@ include file="navbar.jspf" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/views/private_views/private_header.jspf" %>
    </c:otherwise>
</c:choose>


<div class="order-detail-container">

    <h2>Order #${orderId}</h2>

    <table class="order-detail-table">

        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>

        <c:forEach var="d" items="${detailList}">

            <tr>

                <!-- PRODUCT -->
                <td class="product-info">

                    <img src="${pageContext.request.contextPath}/${d.productId.productImage}">

                    <div class="product-text">

                        <span class="product-name">
                            ${d.productId.productName}
                        </span>

                        <c:if test="${d.productId.discount > 0}">
                            <span class="discount-badge">
                                -${d.productId.discount}%
                            </span>
                        </c:if>

                    </div>

                </td>

                <!-- PRICE -->
                <td>

                    <c:choose>

                        <c:when test="${d.productId.discount > 0}">

                            <div class="price-box">

                                <span class="old-price">
                                    <fmt:formatNumber value="${d.productId.price}" type="number"/> đ
                                </span>

                                <span class="new-price">
                                    <fmt:formatNumber 
                                        value="${d.productId.price - (d.productId.price * d.productId.discount / 100)}"
                                        type="number"/> đ
                                </span>

                            </div>

                        </c:when>

                        <c:otherwise>

                            <fmt:formatNumber value="${d.productId.price}" type="number"/> đ

                        </c:otherwise>

                    </c:choose>

                </td>

                <!-- QUANTITY -->
                <td>${d.quantity}</td>

                <!-- TOTAL -->
                <td>

                    <fmt:formatNumber 
                        value="${d.quantity * (d.productId.price - (d.productId.price * d.productId.discount / 100))}" 
                        type="number"/> đ

                </td>

            </tr>

        </c:forEach>

    </table>

</div>
