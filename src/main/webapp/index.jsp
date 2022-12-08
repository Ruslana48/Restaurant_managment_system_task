<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang=${language}>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Restaurantly</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/vendor/aos/aos.css">
    <link rel="stylesheet" href="assets/vendor/glightbox/css/glightbox.min.css">

    <link rel="stylesheet" href="assets/vendor/swiper/swiper-bundle.min.css">

    <!-- Template Main CSS File -->
    <link href="assets/css/main.css" rel="stylesheet">
</head>

<body>
<%session.setAttribute("pageName","index.jsp");%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n" var="lang"/>
<fmt:bundle basename="i18n">
    <fmt:message key="index.home.href" bundle="${lang}" var="home"/>
    <fmt:message key="index.menu.href" bundle="${lang}" var="menu"/>
    <fmt:message key="index.gallery.href" bundle="${lang}" var="galllery"/>
    <fmt:message key="index.dropDown.href" bundle="${lang}" var="dropDown"/>
    <fmt:message key="index.deep.href" bundle="${lang}" var="deep"/>
    <fmt:message key="index.logIn.href" bundle="${lang}" var="logIn"/>
    <fmt:message key="index.welcomeTo.header" bundle="${lang}" var="welcomeTo"/>
    <fmt:message key="index.delivering.header" bundle="${lang}" var="delivering"/>
    <fmt:message key="index.ourMenu.menu" bundle="${lang}" var="ourmenu"/>
    <fmt:message key="index.gallery.gallerySection" bundle="${lang}" var="gallery"/>
</fmt:bundle>
<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

        <a href="index.jsp" class="logo d-flex align-items-center me-auto me-lg-0">
            <h1>Restaurantly<span>.</span></h1>
        </a>

        <nav id="navbar" class="navbar">
            <ul>
                <a href="localization?language=en_US">English</a>|
                <a href="localization?language=ua_UA">Українська</a>
                <li><a href="index.jsp">${home}</a></li>
                <li><a href="menu.jsp">${menu}</a></li>
                <li><a href="#gallery">${galllery}</a></li>
                <li class="dropdown"><a href="#"><span>${dropDown}</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                    <ul>
                        <li><a href="#">${dropDown} 1</a></li>
                        <li class="dropdown"><a href="#"><span>${deep} ${dropDown}</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                    </ul>
                </li>
                <li><a href="login.jsp">${logIn}</a></li>
            </ul>
        </nav>

    </div>

</header><!-- End Header -->

<!-- ======= Hero Section ======= -->
<section id="hero" class="hero d-flex align-items-center section-bg">
    <div class="container">
        <div class="row justify-content-between gy-5">
            <div class="col-lg-5 order-2 order-lg-1 d-flex flex-column justify-content-center align-items-center align-items-lg-start text-center text-lg-start">
                <h2 data-aos="fade-up">${welcomeTo} Restaurantly<br>${delivering}</h2>
                <p data-aos="fade-up" data-aos-delay="100">.</p>
            </div>
            <div class="col-lg-5 order-1 order-lg-2 text-center text-lg-start">
                <img src="assets/img/hero-img.png" class="img-fluid" alt="" data-aos="zoom-out" data-aos-delay="300">
            </div>
        </div>
    </div>
</section><!-- End Hero Section -->

<main id="main">


    </div>

    </div>
    </section><!-- End Stats Counter Section -->

    <!-- ======= Menu Section ======= -->
    <section id="menu" class="menu">
        <div class="container" data-aos="fade-up">

            <div class="section-header">
                <h2>${ourmenu}</h2>
                <p>Check Our <span>Yummy Menu</span></p>
            </div>

            <ul class="nav nav-tabs d-flex justify-content-center" data-aos="fade-up" data-aos-delay="200">

                <li class="nav-item">
                    <a class="nav-link active show" data-bs-toggle="tab" data-bs-target="#menu-starters">
                        <h4>Starters</h4>
                    </a>
                </li><!-- End tab nav item -->

                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" data-bs-target="#menu-breakfast">
                        <h4>Breakfast</h4>
                    </a><!-- End tab nav item -->

                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" data-bs-target="#menu-lunch">
                        <h4>Lunch</h4>
                    </a>
                </li><!-- End tab nav item -->

                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" data-bs-target="#menu-dinner">
                        <h4>Dinner</h4>
                    </a>
                </li><!-- End tab nav item -->

            </ul>

            <div class="tab-content" data-aos="fade-up" data-aos-delay="300">

                <div class="tab-pane fade active show" id="menu-starters">

                    <div class="tab-header text-center">
                        <p>Menu</p>
                        <h3>Starters</h3>
                    </div>

                    <div class="row gy-5">

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-1.png" class="glightbox"><img src="assets/img/menu/menu-item-1.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Magnam Tiste</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $5.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-2.png" class="glightbox"><img src="assets/img/menu/menu-item-2.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Aut Luia</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $14.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-3.png" class="glightbox"><img src="assets/img/menu/menu-item-3.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Est Eligendi</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $8.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-4.png" class="glightbox"><img src="assets/img/menu/menu-item-4.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-5.png" class="glightbox"><img src="assets/img/menu/menu-item-5.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-6.png" class="glightbox"><img src="assets/img/menu/menu-item-6.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Laboriosam Direva</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $9.95
                            </p>
                        </div><!-- Menu Item -->

                    </div>
                </div><!-- End Starter Menu Content -->

                <div class="tab-pane fade" id="menu-breakfast">

                    <div class="tab-header text-center">
                        <p>Menu</p>
                        <h3>Breakfast</h3>
                    </div>

                    <div class="row gy-5">

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-1.png" class="glightbox"><img src="assets/img/menu/menu-item-1.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Magnam Tiste</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $5.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-2.png" class="glightbox"><img src="assets/img/menu/menu-item-2.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Aut Luia</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $14.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-3.png" class="glightbox"><img src="assets/img/menu/menu-item-3.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Est Eligendi</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $8.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-4.png" class="glightbox"><img src="assets/img/menu/menu-item-4.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-5.png" class="glightbox"><img src="assets/img/menu/menu-item-5.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-6.png" class="glightbox"><img src="assets/img/menu/menu-item-6.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Laboriosam Direva</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $9.95
                            </p>
                        </div><!-- Menu Item -->

                    </div>
                </div><!-- End Breakfast Menu Content -->

                <div class="tab-pane fade" id="menu-lunch">

                    <div class="tab-header text-center">
                        <p>Menu</p>
                        <h3>Lunch</h3>
                    </div>

                    <div class="row gy-5">

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-1.png" class="glightbox"><img src="assets/img/menu/menu-item-1.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Magnam Tiste</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $5.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-2.png" class="glightbox"><img src="assets/img/menu/menu-item-2.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Aut Luia</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $14.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-3.png" class="glightbox"><img src="assets/img/menu/menu-item-3.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Est Eligendi</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $8.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-4.png" class="glightbox"><img src="assets/img/menu/menu-item-4.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-5.png" class="glightbox"><img src="assets/img/menu/menu-item-5.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-6.png" class="glightbox"><img src="assets/img/menu/menu-item-6.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Laboriosam Direva</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $9.95
                            </p>
                        </div><!-- Menu Item -->

                    </div>
                </div><!-- End Lunch Menu Content -->

                <div class="tab-pane fade" id="menu-dinner">

                    <div class="tab-header text-center">
                        <p>Menu</p>
                        <h3>Dinner</h3>
                    </div>

                    <div class="row gy-5">

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-1.png" class="glightbox"><img src="assets/img/menu/menu-item-1.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Magnam Tiste</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $5.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-2.png" class="glightbox"><img src="assets/img/menu/menu-item-2.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Aut Luia</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $14.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-3.png" class="glightbox"><img src="assets/img/menu/menu-item-3.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Est Eligendi</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $8.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-4.png" class="glightbox"><img src="assets/img/menu/menu-item-4.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-5.png" class="glightbox"><img src="assets/img/menu/menu-item-5.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Eos Luibusdam</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $12.95
                            </p>
                        </div><!-- Menu Item -->

                        <div class="col-lg-4 menu-item">
                            <a href="assets/img/menu/menu-item-6.png" class="glightbox"><img src="assets/img/menu/menu-item-6.png" class="menu-img img-fluid" alt=""></a>
                            <h4>Laboriosam Direva</h4>
                            <p class="ingredients">
                                Lorem, deren, trataro, filede, nerada
                            </p>
                            <p class="price">
                                $9.95
                            </p>
                        </div><!-- Menu Item -->

                    </div>
                </div><!-- End Dinner Menu Content -->

            </div>

        </div>
    </section><!-- End Menu Section -->


    <!-- ======= Gallery Section ======= -->
    <section id="gallery" class="gallery section-bg">
        <div class="container" data-aos="fade-up">

            <div class="section-header">
                <h2>gallery</h2>
                <p>Check <span>${galery}</span></p>
            </div>

            <div class="gallery-slider swiper">
                <div class="swiper-wrapper align-items-center">
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-1.jpg"><img src="assets/img/gallery/gallery-1.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-2.jpg"><img src="assets/img/gallery/gallery-2.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-3.jpg"><img src="assets/img/gallery/gallery-3.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-4.jpg"><img src="assets/img/gallery/gallery-4.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-5.jpg"><img src="assets/img/gallery/gallery-5.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-6.jpg"><img src="assets/img/gallery/gallery-6.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-7.jpg"><img src="assets/img/gallery/gallery-7.jpg" class="img-fluid" alt=""></a></div>
                    <div class="swiper-slide"><a class="glightbox" data-gallery="images-gallery" href="assets/img/gallery/gallery-8.jpg"><img src="assets/img/gallery/gallery-8.jpg" class="img-fluid" alt=""></a></div>
                </div>
                <div class="swiper-pagination"></div>
            </div>

        </div>
    </section><!-- End Gallery Section -->

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer" class="footer">

    <div class="container">
        <div class="row gy-3">
            <div class="col-lg-3 col-md-6 d-flex">
                <i class="bi bi-geo-alt icon"></i>
                <div>
                    <h4>Address</h4>
                    <p>
                        A108 Adam Street <br>
                        New York, NY 535022 - US<br>
                    </p>
                </div>

            </div>

            <div class="col-lg-3 col-md-6 footer-links d-flex">
                <i class="bi bi-telephone icon"></i>
                <div>
                    <h4>Reservations</h4>
                    <p>
                        <strong>Phone:</strong> +1 5589 55488 55<br>
                        <strong>Email:</strong> info@example.com<br>
                    </p>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 footer-links d-flex">
                <i class="bi bi-clock icon"></i>
                <div>
                    <h4>Opening Hours</h4>
                    <p>
                        <strong>Mon-Sat: 11AM</strong> - 23PM<br>
                        Sunday: Closed
                    </p>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 footer-links">
                <h4>Follow Us</h4>
                <div class="social-links d-flex">
                    <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                    <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                </div>
            </div>

        </div>
    </div>

    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>Yummy</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </div>

</footer><!-- End Footer -->
<!-- End Footer -->

<a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<div id="preloader"></div>

<!-- Vendor JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/aos/aos.js"></script>
<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>

</body>

</html>

