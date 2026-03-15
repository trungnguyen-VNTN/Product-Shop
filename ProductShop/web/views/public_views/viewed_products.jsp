<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Viewed Products</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/public_css/viewed_products.css">

    </head>

    <body>

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

        <div class="container">

            <div class="content">

                <div class="viewed-title">
                    Products You Viewed
                </div>

                <c:choose>

                    <c:when test="${not empty viewedProducts}">

                        <div class="product-container">

                            <c:forEach var="p" items="${viewedProducts}">

                                <a class="product-link"
                                   href="${pageContext.request.contextPath}/main_controller?action=detail&id=${p.productId}">

                                    <div class="product-card">

                                        <div class="img-box">

                                            <img class="product-img"
                                                 src="${pageContext.request.contextPath}/${p.productImage}"
                                                 alt="${p.productName}">

                                        </div>

                                        <div class="product-info">

                                            <div class="product-name">
                                                ${p.productName}
                                            </div>

                                            <div class="price-box">

                                                <span class="new-price">

                                                    <fmt:formatNumber value="${p.price}"
                                                                      type="number"
                                                                      groupingUsed="true"/>
                                                    đ

                                                </span>

                                            </div>

                                        </div>

                                    </div>

                                </a>

                            </c:forEach>

                        </div>

                    </c:when>

                    <c:otherwise>

                        <div class="no-product">
                            You have not viewed any products yet.
                        </div>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>
        <%@include file="footer.jspf" %>

    </body>
</html>