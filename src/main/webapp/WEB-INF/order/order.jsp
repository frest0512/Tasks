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
    <title>Classic Style a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Checkout ::
        w3layouts</title>
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
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"></script>
    <!-- cart -->
    <!-- for bootstrap working -->
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <!-- //for bootstrap working -->
    <!-- animation-effect -->
    <link href="css/animate.min.css" rel="stylesheet">
    <script src="js/wow.min.js"></script>
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
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Order</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s"><a href="<c:url value="/"/>">Home</a><label>/</label>Order
        </h3>
        <div class="clearfix"></div>
    </div>
</div>
<!-- contact -->
<div class="check-out">
    <div class="container">
        <table class="table animated wow fadeInLeft" data-wow-delay=".5s">
            <tr>
                <th class="t-head head-it "><fmt:message key="order.product"/></th>
                <th class="t-head"><fmt:message key="order.price"/></th>
                <th class="t-head"><fmt:message key="order.quantity"/></th>
                <th class="t-head"><fmt:message key="order.price.total"/></th>
            </tr>

            <c:forEach items="${com_epam_pre_prod_order.products}" var="order">
            <tr>
                <td class="t-data ring-in">
                    <a href="single.html" class="at-in">
                        <img src="<c:url value="/avatar?imageName=${order.product.imageFirst}&type=product"/>"
                             class="img-responsive"
                             height="164"
                             width="100"
                             alt="">
                    </a>
                    <div class="sed">
                        <h5>${order.product.name}</h5>
                    </div>
    </div>
    </td>
    <td class="t-data">${order.product.price}$</td>
    <td class="t-data">
        <div class="quantity">
            <div class="quantity-select">

                <div class="entry value"><span class="span-1">${order.amount}</span></div>

            </div>
        </div>
        <!--quantity-->

    </td>
    <td class="t-data">${order.amount*order.product.price}$</td>

    </tr>
    </c:forEach>
    </table>

    <!--Forms-->
    <div class="col-md-6 col-m1 wow fadeInLeft animated" style="width:70%;position: relative;" data-wow-delay=".5s"
         data-example-id="simple-horizontal-form">
        <form class="form-horizontal" action="<c:url value="/order"/>" method="post">
            <div class="form-group">
                <label class="col-sm-2 control-label">Address</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="city" name="city"
                           placeholder="<fmt:message key="order.address.city"/>"
                           style="width:30%;display:inline-block;">
                    <input type="text" class="form-control" id="street" name="street"
                           placeholder="<fmt:message key="order.address.street"/>"
                           style="width:53%;display:inline-block;">
                    <input type="text" class="form-control" id="building" name="building"
                           placeholder="<fmt:message key="order.address.building"/>"
                           style="width:15%;display:inline-block;">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Delivery</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="cartNumber" name="cartNumber"
                           placeholder="<fmt:message key="order.delivery.card.number"/>"
                           style="width:70%;display:inline-block;">
                    <input type="text" class="form-control" id="cartDateExpire" name="cartDateExpire"
                           placeholder="<fmt:message key="order.delivery.card.date.expire"/>" style="width:29%;display:inline-block;">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><fmt:message key="button.make.order"/></button>
                </div>
            </div>
        </form>

    </div>
    <div class="col-md-6 col-m1 wow fadeInRight animated" style="width:30%;position: relative;">
        <div class="cart-total">
            <div class="price-details">
                <h3><fmt:message key="order.total.form.price.detail"/></h3>
                <span><fmt:message key="order.total.form.total"/></span>
                <span id="total_price_without_discount_and_delivery"
                      class="total1">${not empty com_epam_pre_prod_order?com_epam_pre_prod_order.total:0}</span>
                <span><fmt:message key="order.total.form.discount"/></span>
                <span class="total1">---</span>
                <span><fmt:message key="order.total.form.delivery"/></span>
                <span class="total1">---</span>
                <div class="clearfix"></div>
            </div>
            <ul class="total_price">
                <li class="last_price"><h4><fmt:message key="order.total.form.total"/></h4></li>
                <li id="total_price_with_discount_and_delivery" class="last_price">
                    <span>${not empty com_epam_pre_prod_order?com_epam_pre_prod_order.total:0}</span></li>
                <div class="clearfix"></div>
            </ul>

        </div>


    </div>
</div>
<!--//forms-->
<%@ include file="/WEB-INF/includes/subfooter.jsp" %>
<%@ include file="/WEB-INF/includes/footer.jsp" %>
<!-- //footer -->
</body>
</html>