<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

    <div class="page-wrapper">

        <div class="page-title">
            <h2>Admin Dashboard</h2>
            <p>System Overview</p>
        </div>

        <div class="dashboard-container">

            <!-- Account Card -->
            <div class="dashboard-card">
                <h3>Total Accounts</h3>
                <p class="dashboard-number">
                    ${totalAccounts}
                </p>
            </div>

            <!-- Category Card -->
            <div class="dashboard-card">
                <h3>Total Categories</h3>
                <p class="dashboard-number">
                    ${totalCategories}
                </p>
            </div>

            <!-- Product Card -->
            <div class="dashboard-card">
                <h3>Total Products</h3>
                <p class="dashboard-number">
                    ${totalProducts}
                </p>
            </div>

        </div>

    </div>

</body>
</html>