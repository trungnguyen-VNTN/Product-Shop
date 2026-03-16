<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Invoice</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/private_css/invoice.css">

    </head>

    <body>

        <%@include file="private_header.jspf" %>
        <%@include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Order Management</h2>
                <p>List of orders in system</p>
            </div>


            <div class="card">

                <c:if test="${not empty sessionScope.message}">
                    <div class="success-message">
                        ${sessionScope.message}
                    </div>

                    <c:remove var="message" scope="session"/>
                </c:if>

                <table class="styled-table">

                    <thead>

                        <tr>

                            <th>ID</th>
                            <th>Account</th>
                            <th>Order Date</th>
                            <th>Shipping Address</th>
                            <th>Phone</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th class="action-col">Action</th>

                        </tr>

                    </thead>


                    <tbody>

                        <c:forEach var="o" items="${orderList}">

                            <tr>

                                <td>${o.orderId}</td>

                                <td>${o.account.getAccount()}</td>

                                <td>
                                    <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy"/>
                                </td>

                                <td>${o.shippingAddress}</td>

                                <td>${o.phone}</td>

                                <td class="price">
                                    <fmt:formatNumber value="${o.totalAmount}" type="number"/> VND
                                </td>


                                <!-- STATUS UPDATE -->

                                <td>

                                    <form action="main_controller" method="post">

                                        <input type="hidden" name="action" value="private_updateOrderStatus">
                                        <input type="hidden" name="orderId" value="${o.orderId}">

                                        <select name="status"
                                                onchange="this.form.submit()"
                                                style="padding:5px;border-radius:4px">

                                            <option value="0" ${o.status==0?'selected':''}>
                                                Pending
                                            </option>

                                            <option value="1" ${o.status==1?'selected':''}>
                                                Processing
                                            </option>

                                            <option value="2" ${o.status==2?'selected':''}>
                                                Shipping
                                            </option>

                                            <option value="3" ${o.status==3?'selected':''}>
                                                Completed
                                            </option>

                                        </select>

                                    </form>

                                </td>


                                <!-- ACTION -->

                                <td class="actions">

                                    <a href="main_controller?action=private_viewOrderDetail&id=${o.orderId}"
                                       class="btn btn-primary">
                                        View
                                    </a>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>

    </body>
</html>