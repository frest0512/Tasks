<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://epam.com/tags/ctags" prefix="ct" %>
<%@ taglib uri="http://epam.com/tags/funcs" prefix="fn" %>
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
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%@ include file="/WEB-INF/includes/subheader.jsp" %>
<!-- //header -->
<!--banner-->
<div class="banner-top">
    <div class="container">
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Products</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s"><a
                href="<c:url value="/"/>">Home</a><label>/</label>Order<label>/</label>Fail</h3>
        <div class="clearfix"></div>
    </div>
</div>
<!--content-->
<div class="product">
    <div class="container" align="center">
            <h1><fmt:message key="status.make.order.fail"/></h1>
            <h2><fmt:message key="status.make.order.fail.description"/></h2>
        <div class="clearfix"/>
    </div>
</div>
</div>
<%@ include file="/WEB-INF/includes/subfooter.jsp" %>
<%@ include file="/WEB-INF/includes/footer.jsp" %>
<!-- //footer -->
</body>
</html>