<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://epam.com/tags/ctags" prefix="ct" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
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
<!-- //header --><!--banner-->
<div class="banner-top">
    <div class="container">
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Register</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s"><a
                href="WEB-INF/includes/index.jsp">Home</a><label>/</label>Register</h3>
        <div class="clearfix"></div>
    </div>
</div>


<!-- contact -->
<div class="login">
    <div class="container">
        <form id="reg_form" action="${pageContext.servletContext.contextPath}/register" method="post"
              enctype="multipart/form-data">
            <div class="col-md-6 login-do1 animated wow fadeInLeft" data-wow-delay=".5s">
                <div id="error_first_name"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['firstName']?com_epam_pre_prod_validation_error_messages['firstName']:''}</div>
                <div class="login-mail">
                    <input id="first_name" name="first_name" type="text" placeholder="<fmt:message key="register.first.name"/>" required=""
                           value="${not empty com_epam_pre_prod_user_fail? com_epam_pre_prod_user_fail.firstName:''}">
                    <i class="glyphicon "></i>
                </div>
                <div id="error_last_name"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['lastName']?com_epam_pre_prod_validation_error_messages['lastName']:''}</div>
                <div class="login-mail">
                    <input id="last_name" name="last_name" type="text" placeholder="<fmt:message key="register.last.name"/>" required=""
                           value="${not empty com_epam_pre_prod_user_fail? com_epam_pre_prod_user_fail.lastName:''}">
                    <i class="glyphicon "></i>
                </div>
                <div id="error_username"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['userName']?com_epam_pre_prod_validation_error_messages['userName']:''}</div>
                <div class="login-mail">
                    <input id="username" name="username" type="text" placeholder="<fmt:message key="register.username"/>" required=""
                           value="${not empty com_epam_pre_prod_user_fail? com_epam_pre_prod_user_fail.userName:''}">
                    <i class="glyphicon "></i>
                </div>
                <div id="error_email"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['email']?com_epam_pre_prod_validation_error_messages['email']:''}</div>
                <div class="login-mail">
                    <input id="email" name="email" type="text" placeholder="<fmt:message key="register.email"/>" required=""
                           value="${not empty com_epam_pre_prod_user_fail? com_epam_pre_prod_user_fail.email:''}">
                    <i class="glyphicon glyphicon-envelope"></i>
                </div>

                <div id="error_password"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['password']?com_epam_pre_prod_validation_error_messages['password']:''}</div>
                <div class="login-mail">
                    <input id="password" name="password" type="password" placeholder="<fmt:message key="register.password"/>" required="">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div id="error_conf_password"
                     style="color: #F93333">${not empty com_epam_pre_prod_validation_error_messages['confirmPassword']?com_epam_pre_prod_validation_error_messages['confirmPassword']:''}</div>
                <div class="login-mail">
                    <input id="password_confirmation" name="password_confirmation" type="password"
                           placeholder="<fmt:message key="register.password.confirm"/>" required="">
                    <i class="glyphicon glyphicon-lock"></i>
                </div>
                <div style="color: #F93333">${not empty com_epam_pre_prod_user_exist?com_epam_pre_prod_user_exist:''}</div>
                <a class="news-letter" href="#">
                    <label class="checkbox1">
                        <input id="isSubscribe" name="isSubscribe" type="checkbox" name="checkbox">
                        <i></i><fmt:message key="register.get.notifications"/></label>
                </a>

            </div>
            <div class="col-md-6 login-do animated wow fadeInRight" data-wow-delay=".5s">
                <input id="avatar_name" name="avatar_name" type="file">
                <p style="color: #F93333">${com_epam_pre_prod_wrong_captcha}</p>
                <ct:captchaMaker imageServletUrl="${pageContext.servletContext.contextPath}/captcha"
                                 key="${requestScope.captchaCodeToPutIn.code}"/>
                <input id="captchaConfirm" name="captchaConfirm" placeholder="<fmt:message key="register.enter.captcha.text"/>">
                <br/>
                <br/>
                <br/>
                <br/>
                <label class="hvr-sweep-to-top login-sub">
                    <input id="register_button" type="submit" value="<fmt:message key="button.register"/>">
                </label>
                <p></p>
                <a href="<c:url value="/login"/>" class="hvr-sweep-to-top"><fmt:message key="button.log.in"/></a>
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

<!--<script src="js/validation/JSRegisterValidation.js"></script>-->
<script src="<c:url value="/js/validation/JQueryRegisterValidation.js" />"></script>

</html>