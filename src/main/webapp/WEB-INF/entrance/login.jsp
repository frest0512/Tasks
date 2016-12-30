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
    <title>Classic Style a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Login ::
        w3layouts</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Classic Style Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
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
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Login</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s">
            <a href="<c:url value="/"/>">Home</a><label>/</label>Login</h3>
        <div class="clearfix"></div>
    </div>
</div>
<!-- contact -->
<div class="login">
    <div class="container">
        <form action="<c:url value="/login"/>" method="post">
            <input type="hidden" value="${com_epam_pre_prod_url_to_redirect_after_login}" name="urlToRedirect">
            <div class="col-md-6 login-do1 animated wow fadeInLeft" data-wow-delay=".5s">
                <div style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['login_username_error']?com_epam_pre_prod_validation_error_messages['login_username_error']:''}</div>
                <div class="login-mail">
                    <input name="username" type="text" placeholder="<fmt:message key="login.username"/>" required="">
                    <i class="glyphicon glyphicon-envelope"></i>
                </div>
                <div style="color: #F93333">
                    ${not empty com_epam_pre_prod_user_wrong_password?com_epam_pre_prod_user_wrong_password:''}
                    ${not empty com_epam_pre_prod_validation_error_messages['login_password_error']?com_epam_pre_prod_validation_error_messages['login_password_error']:''}
                </div>

                <div class="login-mail">
                    <input name="password" type="password" placeholder="<fmt:message key="login.password"/>" required="">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div style="color: #F93333">
                    ${not empty com_epam_pre_prod_user_not_exist?com_epam_pre_prod_user_not_exist:''}
                </div>
                <div style="color: #F93333">
                    ${not empty com_epam_pre_prod_user_ban?com_epam_pre_prod_user_ban:''}

                    <c:if test="${not empty com_epam_pre_prod_user_unban_time}">
                        <br>
                        Un ban time: ${com_epam_pre_prod_user_unban_time}
                    </c:if>
                    <c:if test="${not empty com_epam_pre_prod_no_rights_to_view_page}">
                        <br>
                        ${com_epam_pre_prod_no_rights_to_view_page}
                    </c:if>
                </div>
            </div>
            <div class="col-md-6 login-do animated wow fadeInRight" data-wow-delay=".5s">
                <label class="hvr-sweep-to-top login-sub">
                    <input type="submit" value="<fmt:message key="login.log.in"/>">
                </label>
                <p></p>
                <a href="<c:url value="/register"/> " class="hvr-sweep-to-top"><fmt:message key="button.register"/></a>
            </div>
            <div class="clearfix"></div>
        </form>

    </div>
</div>

<!-- footer -->
<%@ include file="/WEB-INF/includes/subfooter.jsp" %>
<%@ include file="/WEB-INF/includes/footer.jsp" %>
<!-- //footer -->
</body>
</html>