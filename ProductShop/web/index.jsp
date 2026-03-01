<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
<div class="containner">
    <%@include file="views/header.jspf" %>

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
<div/>
<%@include file="views/footer.jspf" %>