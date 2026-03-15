<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>About Us</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/public_css/about.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/all.min.css">

    </head>

    <body>

        <div class="container">

            <!-- HEADER -->

            <%@ include file="header.jspf" %>
            <%@ include file="navbar.jspf" %>


            <div class="content">

                <!-- HERO -->

                <div class="about-hero">

                    <div class="about-header">
                        About Our Store
                    </div>

                    <p>
                        We provide high quality products with the best prices and excellent
                        customer service. Our mission is to make online shopping easy,
                        reliable and enjoyable for everyone.
                    </p>

                </div>


                <!-- FEATURES -->

                <div class="about-section">

                    <h2 class="section-title">

                        <i class="fa-solid fa-star"></i>
                        Why Choose Us

                    </h2>

                    <div class="feature-grid">

                        <div class="feature-card">

                            <i class="fa-solid fa-truck-fast feature-icon"></i>

                            <h3>Fast Delivery</h3>

                            <p>
                                We deliver products quickly and safely to your doorstep.
                            </p>

                        </div>


                        <div class="feature-card">

                            <i class="fa-solid fa-shield-halved feature-icon"></i>

                            <h3>Secure Payment</h3>

                            <p>
                                Your transactions are protected with secure payment systems.
                            </p>

                        </div>


                        <div class="feature-card">

                            <i class="fa-solid fa-headset feature-icon"></i>

                            <h3>24/7 Support</h3>

                            <p>
                                Our support team is always ready to assist you anytime.
                            </p>

                        </div>


                        <div class="feature-card">

                            <i class="fa-solid fa-tag feature-icon"></i>

                            <h3>Best Price</h3>

                            <p>
                                We offer competitive prices and frequent promotions.
                            </p>

                        </div>

                    </div>

                </div>


                <!-- OUR MISSION -->

                <div class="about-section">

                    <h2 class="section-title">

                        <i class="fa-solid fa-bullseye"></i>
                        Our Mission

                    </h2>

                    <p class="mission-text">

                        Our goal is to build a trusted online marketplace where customers
                        can discover quality products at the best prices. We continuously
                        improve our services to deliver the best shopping experience.

                    </p>

                </div>


                <!-- TEAM -->

                <div class="about-section">

                    <h2 class="section-title">

                        <i class="fa-solid fa-users"></i>
                        Our Team

                    </h2>

                    <div class="team-grid">

                        <div class="team-card">

                            <i class="fa-solid fa-user-tie team-icon"></i>

                            <h4>Store Manager</h4>

                            <p>Responsible for store management and strategy.</p>

                        </div>


                        <div class="team-card">

                            <i class="fa-solid fa-laptop-code team-icon"></i>

                            <h4>Developer</h4>

                            <p>Builds and maintains the website system.</p>

                        </div>


                        <div class="team-card">

                            <i class="fa-solid fa-box-open team-icon"></i>

                            <h4>Product Manager</h4>

                            <p>Manages product listings and quality control.</p>

                        </div>

                    </div>

                </div>


            </div>


            <%@ include file="footer.jspf" %>

        </div>

    </body>

</html>