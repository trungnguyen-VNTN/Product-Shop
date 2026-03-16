<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public_css/contact.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">

    </head>

    <body>
        <c:choose>

            <c:when test="${sessionScope.user == null || sessionScope.user.roleInSystem == 0}">
                <%@ include file="header.jspf" %>
                <%@ include file="navbar.jspf" %>
            </c:when>

            <c:otherwise>
                <%@ include file="/views/private_views/private_header.jspf" %>
            </c:otherwise>

        </c:choose>

        <div class="contact-container">

            <div class="contact-header">
                Contact Us
            </div>

            <div class="contact-box">

                <!-- STORE INFO -->
                <div class="contact-info">

                    <h3>
                        Our Store
                    </h3>

                    <p>
                        <i class="fa-solid fa-phone"></i>
                        Phone: +84 377 094 328
                    </p>

                    <p>
                        <i class="fa-solid fa-envelope"></i>
                        Email: peansproductshop@gmail.com
                    </p>

                    <p>
                        <i class="fa-solid fa-clock"></i>
                        Working Hours: 8:00 - 22:00
                    </p>


                    <p>
                        <i class="fa-solid fa-location-dot"></i>
                        Address: Lô E2a-7, Đường D1, Phường Tăng Nhơn Phú, Ho Chi Minh City, Vietnam
                    </p>
                    <div class="store-map">
                        <img src="${pageContext.request.contextPath}/images/web_images/address.png" alt="Store location">
                        
                    </div>
                </div>

                <!-- CONTACT FORM -->
                <div class="contact-form">
                    <h3>
                        Tell us about your thinking
                    </h3>
                    
                    <form action="main_controller?action=home" method="post">

                        <input type="hidden" name="action" value="sendContact">

                        <input type="text"
                               name="name"
                               placeholder="Your Name"
                               required>

                        <input type="email"
                               name="email"
                               placeholder="Your Email"
                               required>

                        <input type="text"
                               name="subject"
                               placeholder="Subject">

                        <textarea name="message"
                                  placeholder="Your Message"
                                  rows="6"
                                  required></textarea>

                        <button type="submit">
                            <i class="fa-solid fa-paper-plane"></i>
                            Send Message
                        </button>

                    </form>

                </div>

            </div>

        </div>
        <%@include file="footer.jspf" %>
    </body>
</html>