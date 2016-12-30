<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stuff</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //for-mobile-apps -->
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet" type="text/css" media="all"/>
    <!-- js -->
    <script src="<c:url value="/js/jquery.min.js" />"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="<c:url value="/js/simpleCart.min.js" />"></script>
    <!-- cart -->
    <!-- for bootstrap working -->
    <script type="text/javascript" src="<c:url value="/js/bootstrap-3.1.1.min.js" />"></script>
    <!-- //for bootstrap working -->
    <!-- animation-effect -->
    <link href="<c:url value="/css/animate.min.css" />" rel="stylesheet">
    <script src="<c:url value="/js/wow.min.js" />"></script>
    <script>
        new WOW().init();
    </script>
    <!-- //animation-effect -->
    <link href='//fonts.googleapis.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
</head>

<body>
<!-- header -->

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/subheader.jsp" %>
<!-- //header -->


<!-- banner -->
<div class="banner">


    <div class="banner-right">
        <ul id="flexiselDemo2">
            <li>
                <div class="banner-grid">
                    <h2>Featured Products</h2>
                    <div class="wome">
                        <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bi1.jpg" />"
                                                      alt=""/>
                        </a>
                        <div class="women simpleCart_shelfItem">
                            <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                            <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                            <p class="ba-price">
                                <del>$100.00</del>
                                <em class="item_price">$70.00</em></p>
                            <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="banner-grid">
                    <h2>Featured Products</h2>
                    <div class="wome">
                        <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bi.jpg" />"
                                                      alt=""/>
                        </a>
                        <div class="women simpleCart_shelfItem">
                            <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                            <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                            <p class="ba-price">
                                <del>$100.00</del>
                                <em class="item_price">$70.00</em></p>
                            <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="banner-grid">
                    <h2>Featured Products</h2>
                    <div class="wome">
                        <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bi2.jpg" />"
                                                      alt=""/>
                        </a>
                        <div class="women simpleCart_shelfItem">
                            <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                            <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                            <p class="ba-price">
                                <del>$100.00</del>
                                <em class="item_price">$70.00</em></p>
                            <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                        </div>
                    </div>
                </div>
            </li>


        </ul>
        <script type="text/javascript">
            $(window).load(function () {
                $("#flexiselDemo2").flexisel({
                    visibleItems: 1,
                    animationSpeed: 1000,
                    autoPlay: true,
                    autoPlaySpeed: 5000,
                    pauseOnHover: true,
                    enableResponsiveBreakpoints: true,
                    responsiveBreakpoints: {
                        portrait: {
                            changePoint: 480,
                            visibleItems: 1
                        },
                        landscape: {
                            changePoint: 640,
                            visibleItems: 1
                        },
                        tablet: {
                            changePoint: 768,
                            visibleItems: 1
                        }
                    }
                });

            });
        </script>
        <script type="text/javascript" src="<c:url value="/js/jquery.flexisel.js" />"></script>

    </div>


</div>

</div>
<!-- //banner -->
<!--content-->
<div class="content">
    <div class="content-head">

        <div class="col-md-6 col-m1 animated wow fadeInLeft" data-wow-delay=".1s">
            <div class="col-1">
                <div class="col-md-6 col-2">
                    <a href="../single.html"><img src="<c:url value="/images/pi3.jpg" />" class="img-responsive" alt="">
                    </a></div>
                <div class="col-md-6 col-p">
                    <h5>For All Collections</h5>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis
                        praesentium voluptatum deleniti atque</p>
                    <a href="../single.html" class="shop" data-hover="Shop Now">Shop Now</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="col-1">
                <div class="col-md-6 col-p">
                    <h5>For All Collections</h5>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis
                        praesentium voluptatum deleniti atque</p>
                    <a href="../single.html" class="shop" data-hover="Shop Now">Shop Now</a>
                </div>
                <div class="col-md-6 col-2">
                    <a href="../single.html"><img src="<c:url value="/images/pi.jpg" />" class="img-responsive" alt="">
                    </a></div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="col-md-6 col-m2 animated wow fadeInRight" data-wow-delay=".1s">

            <!---->
            <!-- requried-jsfiles-for owl -->
            <link href="<c:url value="/css/owl.carousel.css" />" rel="stylesheet">
            <script src="<c:url value="/js/owl.carousel.js" />"></script>
            <script>
                $(document).ready(function () {
                    $("#owl-demo").owlCarousel({
                        items: 2,
                        lazyLoad: false,
                        autoPlay: true,
                        navigation: true,
                        navigationText: true,
                        pagination: false,
                    });
                });
            </script>
            <!-- //requried-jsfiles-for owl -->
            <!-- start content_slider -->
            <div id="owl-demo" class="owl-carousel">
                <div class="item">

                    <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bb.png" />" alt=""/></a>
                    <a href="../single.html" class="shop-2">Shop Now</a>

                </div>
                <div class="item">

                    <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bb1.png" />"
                                                  alt=""/></a>
                    <a href="../single.html" class="shop-2">Shop Now</a>

                </div>

                <div class="item">

                    <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bb.png" />" alt=""/>
                    </a>
                    <a href="../single.html" class="shop-2">Shop Now</a>

                </div>
                <div class="item">

                    <a href="../single.html"><img class="img-responsive" src="<c:url value="/images/bb1.png" />"
                                                  alt=""/></a>
                    <a href="../single.html" class="shop-2">Shop Now</a>

                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!---->

<div class="content-top">
    <div class="col-md-5 col-md1 animated wow fadeInLeft" data-wow-delay=".1s">
        <div class="col-3">
            <a href="../single.html"><img src="<c:url value="/images/pi1.jpg" />" class="img-responsive " alt="">
                <div class="col-pic">
                    <h5> Women's Wear</h5>
                    <p>At vero eos et accusamus et</p>
                </div>
            </a>
        </div>

    </div>
    <div class="col-md-7 col-md2 animated wow fadeInRight" data-wow-delay=".1s">
        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr1.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>
        </div>

        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr3.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr2.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr4.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr4.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>
</div>
<!----->
<!---->
<div class="content-top">

    <div class="col-md-7 col-md2 animated wow fadeInLeft" data-wow-delay=".1s">
        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr6.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr7.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>

        </div>

        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr9.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr8.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4 item-grid simpleCart_shelfItem">
            <div class="grid-pro">
                <div class=" grid-product ">
                    <figure>
                        <a href="../single.html">
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr10.jpg" />" class="img-responsive" alt="">
                            </div>
                            <div class="grid-img">
                                <img src="<c:url value="/images/pr11.jpg" />" class="img-responsive" alt="">
                            </div>
                        </a>
                    </figure>
                </div>
                <div class="women">
                    <a href="#"><img src="<c:url value="/images/ll.png" />" alt=""></a>
                    <h6><a href="../single.html">Sed ut perspiciatis unde</a></h6>
                    <p>
                        <del>$100.00</del>
                        <em class="item_price">$70.00</em></p>
                    <a href="#" data-text="Add To Cart" class="but-hover1 item_add">Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="col-md-5 col-md1 animated wow fadeInRight" data-wow-delay=".1s">
        <div class="col-3">
            <a href="../single.html"><img src="<c:url value="/images/pi2.jpg" />" class="img-responsive " alt="">
                <div class="col-pic">
                    <h5> Men's Wear</h5>
                    <p>At vero eos et accusamus et</p>
                </div>
            </a>
        </div>

    </div>
    <div class="clearfix"></div>
</div>

<!-- footer -->
<%@ include file="includes/subfooter.jsp" %>
<%@ include file="includes/footer.jsp" %>
<!-- //footer -->
</body>
</html>