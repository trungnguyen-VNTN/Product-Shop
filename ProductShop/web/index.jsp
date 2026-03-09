<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Shop</title>
    </head>
    <body>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/index.css">
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <%@ include file="views/public_views/header.jspf" %>
                    <%@ include file="views/public_views/navbar.jspf" %>
                </c:when>
                <c:otherwise>
                    <%@ include file="views/private_views/private_header.jspf" %>
                </c:otherwise>
            </c:choose>

            <!-- BANNER -->
            <div class="banner-container">

                <!-- SLIDER LEFT -->
                <div class="slider">

                    <div class="slides">
                        <img src="${pageContext.request.contextPath}/images/web_images/banner1.png">
                        <img src="${pageContext.request.contextPath}/images/web_images/banner2.png">
                        <img src="${pageContext.request.contextPath}/images/web_images/banner4.png">
                    </div>

                    <div class="dots">
                        <span class="dot active"></span>
                        <span class="dot"></span>
                        <span class="dot"></span>
                    </div>

                </div>

                <!-- RIGHT BANNERS -->
                <div class="side-banners">

                    <img src="${pageContext.request.contextPath}/images/web_images/banner3.png">

                    <img src="${pageContext.request.contextPath}/images/web_images/banner5.png">

                </div>

            </div>


            <!--CONTENT-->
            <div class="product-container">

                <c:forEach var="p" items="${productList}">

                    <a href="${pageContext.request.contextPath}/main_controller?action=detail&id=${p.productId}" class="product-link">

                        <div class="product-card">

                            <c:if test="${p.discount > 0}">
                                <div class="discount">-${p.discount}%</div>
                            </c:if>

                            <div class="img-box">
                                <img src="${pageContext.request.contextPath}/${p.productImage}" class="product-img"/>
                            </div>

                            <div class="product-info">
                                <div class="product-name">
                                    ${p.productName}
                                </div>

                                <div class="price-box">

                                    <c:if test="${p.discount > 0}">
                                        <span class="old-price">
                                            <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> đ
                                        </span>
                                    </c:if>

                                    <span class="new-price">
                                        <fmt:formatNumber 
                                            value="${p.price - (p.price * p.discount / 100)}" 
                                            type="number" 
                                            groupingUsed="true"/> đ
                                    </span>

                                </div>
                            </div>

                        </div>

                    </a>

                </c:forEach>

            </div>
            <%@include file="views/public_views/footer.jspf" %>
        </div>
        <script src="${pageContext.request.contextPath}/js/slider.js"></script>
    </body>
</html>