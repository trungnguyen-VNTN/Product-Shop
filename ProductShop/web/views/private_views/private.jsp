<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/private_css/dashboard.css">
    </head>

    <body>

        <%@ include file="private_header.jspf" %>
        <%@ include file="sidebar.jspf" %>

        <div class="page-wrapper">

            <div class="page-title">
                <h2>Admin Dashboard</h2>
                <p>System Overview</p>
            </div>

            <div class="dashboard-content">

                <div class="dashboard-grid">

                    <!-- TOTAL PRODUCTS -->
                    <div class="dashboard-card">

                        <div class="card-icon green">
                            <i class="fa-solid fa-cubes"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Total Products</div>
                            <div class="card-number">${totalProducts}</div>
                        </div>

                    </div>


                    <!-- PRODUCTS SOLD -->

                    <div class="dashboard-card">

                        <div class="card-icon blue">
                            <i class="fa-solid fa-truck-fast"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Products Sold</div>
                            <div class="card-number">${productsSold}</div>
                        </div>

                    </div>


                    <!-- TOTAL SALES -->

                    <div class="dashboard-card">

                        <div class="card-icon red">
                            <i class="fa-solid fa-dollar-sign"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Total Sales</div>
                            <div class="card-number"><fmt:formatNumber value="${totalSales}" type="number"/> đ</div>
                        </div>

                    </div>


                    <!-- TOTAL USERS -->

                    <div class="dashboard-card">

                        <div class="card-icon blue">
                            <i class="fa-solid fa-users"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Total Users</div>
                            <div class="card-number">${totalAccounts}</div>
                        </div>

                    </div>


                    <!-- PRODUCT TYPES -->

                    <div class="dashboard-card">

                        <div class="card-icon black">
                            <i class="fa-solid fa-box"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Total Product Types</div>
                            <div class="card-number">${totalCategories}</div>
                        </div>

                    </div>


                    <!-- TOTAL ORDERS -->

                    <div class="dashboard-card">

                        <div class="card-icon green">
                            <i class="fa-solid fa-cart-shopping"></i>
                        </div>

                        <div class="card-info">
                            <div class="card-title">Total Orders</div>
                            <div class="card-number">${totalOrders}</div>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </body>
</html>