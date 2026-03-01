<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">

<div>
    <%@include file="header.jspf" %>
    <div class="detail-wrapper">

        <div class="detail-img">
            <img src="${pageContext.request.contextPath}/${product.productImage}">
        </div>

        <div class="detail-info">

            <div class="detail-name">
                ${product.productName}
            </div>

            <div class="price-area">
                <c:if test="${product.discount > 0}">
                    <span class="old-price">
                        <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/> đ
                    </span>
                </c:if>

                <span class="new-price">
                    <fmt:formatNumber 
                        value="${product.price - (product.price * product.discount / 100)}" 
                        type="number" 
                        groupingUsed="true"/> đ
                </span>
            </div>

            <div class="detail-desc">
                ${product.brief}
            </div>

        </div>

    </div>
    <%@include file="footer.jspf" %>
</div>
 