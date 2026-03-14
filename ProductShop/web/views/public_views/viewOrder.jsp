<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/viewOrder.css">

<%@ include file="header.jspf" %>
<%@ include file="navbar.jspf" %>

<div class="orders-container">

    <h2>My Orders</h2>

    <c:choose>

        <c:when test="${empty orderList}">

            <div class="empty-orders">
                You have not placed any orders yet.
            </div>

        </c:when>

        <c:otherwise>

            <table class="orders-table">

                <tr>
                    <th>Order ID</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>Status</th>
                </tr>

                <c:forEach var="o" items="${orderList}">

                    <tr>

                        <td>
                            <a href="${pageContext.request.contextPath}/main_controller?action=viewOrderDetail&id=${o.orderId}">
                                #${o.orderId}
                            </a>
                        </td>


                        <td>
                            <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                        </td>

                        <td>
                            <fmt:formatNumber value="${o.totalAmount}" type="number"/> đ
                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${o.status == 0}">
                                    <span class="status pending">Pending</span>
                                </c:when>

                                <c:when test="${o.status == 1}">
                                    <span class="status processing">Processing</span>
                                </c:when>

                                <c:when test="${o.status == 2}">
                                    <span class="status shipping">Shipping</span>
                                </c:when>

                                <c:when test="${o.status == 3}">
                                    <span class="status completed">Completed</span>
                                </c:when>

                                <c:otherwise>
                                    <span class="status cancelled">Cancelled</span>
                                </c:otherwise>

                            </c:choose>

                        </td>

                    </tr>

                </c:forEach>

            </table>

        </c:otherwise>

    </c:choose>

</div>