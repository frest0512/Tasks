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
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Cart</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s"><a href="<c:url value="/"/>">Home</a><label>/</label>Cart
        </h3>
        <div class="clearfix"></div>
    </div>
</div>
<!-- contact -->
<div class="check-out">
    <div class="container">
        <table class="table animated wow fadeInLeft" data-wow-delay=".5s">
            <tr>
                <th class="t-head head-it "><fmt:message key="cart.product"/></th>
                <th class="t-head"><fmt:message key="cart.price"/></th>
                <th class="t-head"><fmt:message key="cart.quantity"/></th>
                <th class="t-head"><fmt:message key="cart.price.total"/></th>
            </tr>

            <c:forEach items="${com_epam_pre_prod_cart.items}" var="cart">
                <tr id="cross${cart.key.id}" class="removable_line">
                    <td class="t-data ring-in">
                        <a href="single.html" class="at-in">
                            <img src="<c:url value="/avatar?imageName=${cart.key.imageFirst}&type=product"/>"
                                 class="img-responsive"
                                 height="164"
                                 width="100"
                                 alt="">
                        </a>
                        <div class="sed">
                            <h5>${cart.key.name}</h5>
                            <div class="close2" onclick="removeItem('${cart.key.id}')"></div>
                        </div>
                    </td>
                    <td class="t-data">${cart.key.price}$</td>
                    <td class="t-data">
                        <div class="quantity">
                            <div class="quantity-select">
                                <div class="entry value-minus" onclick="decreaseNumberOfItems('${cart.key.id}')">
                                    &nbsp;</div>
                                <div class="entry value"><span id="numberItems${cart.key.id}"
                                                               class="span-1">${cart.value}</span></div>
                                <div class="entry value-plus" onclick="increaseNumberOfItems('${cart.key.id}')">
                                    &nbsp;</div>
                            </div>
                        </div>
                        <!--quantity-->

                    </td>
                    <td class="t-data"><span id="totalItemPrice${cart.key.id}"
                                             class="span-1">${cart.value*cart.key.price}</span>$</td>

                </tr>
            </c:forEach>
        </table>
        <div class=" cart-total">

            <h5 class="continue"><fmt:message key="cart.total.form.cart.total"/></h5>
            <div class="price-details">
                <h3><fmt:message key="cart.total.form.price.detail"/></h3>
                <span><fmt:message key="cart.total.form.total"/></span>
                <span id="total_price_without_discount_and_delivery"
                      class="total1">${not empty com_epam_pre_prod_cart?com_epam_pre_prod_cart.totalPrice():0}</span>
                <span><fmt:message key="cart.total.form.discount"/></span>
                <span class="total1">---</span>
                <span><fmt:message key="cart.total.form.delivery"/></span>
                <span class="total1">---</span>
                <div class="clearfix"></div>
            </div>
            <ul class="total_price">
                <li class="last_price"><h4><fmt:message key="cart.total.form.cart.total"/></h4></li>
                <li id="total_price_with_discount_and_delivery" class="last_price">
                    <span>${not empty com_epam_pre_prod_cart?com_epam_pre_prod_cart.totalPrice():0}</span></li>
                <div class="clearfix"></div>
            </ul>

            <a href="<c:url value="/order"/>"><fmt:message key="button.prepare.order"/></a>

        </div>
        <button onclick="clearCart()" class="btn btn-lg btn-primary"><fmt:message key="button.clear.cart"/></button>

    </div>
</div>

<%@ include file="/WEB-INF/includes/subfooter.jsp" %>
<%@ include file="/WEB-INF/includes/footer.jsp" %>
<!-- //footer -->
</body>
<script>
    var removeLine = function (id) {
        console.log('#cross' + id);
        $('#cross' + id).fadeOut('slow', function () {
            $('#cross' + id).remove();
        });
    };
    var removeAllLines = function () {
        console.log('.removable_line');
        $('.removable_line').fadeOut('slow', function () {
            $('.removable_line').remove();
        });
    };
    var removeItem = function (id) {
        $.ajax({
            type: 'POST',
            url: '<c:url value="/removeItemFromCart"/>',
            dataType: 'JSON',
            data: 'productId=' + id,
            success: function (data) {
                $('#cart_total_price').html(data.totalPrice);
                $('#cart_items_number').html(data.totalItems);
                $('#total_price_with_discount_and_delivery').html(data.totalPrice);
                $('#total_price_without_discount_and_delivery').html(data.totalPrice);
                removeLine(id);
            },
            error: function (data) {
                alert('fail ' + data);
            }
        });
    };
    var changeNumberOfItems = function (id, newNumber) {
        $.ajax({
            type: 'POST',
            url: '<c:url value="/changeNumberOfItems"/>',
            dataType: 'JSON',
            data: 'productId=' + id + '&numberOfItems=' + newNumber,
            success: function (data) {
                $('#cart_total_price').html(data.totalPrice);
                $('#cart_items_number').html(data.totalItems);
                $('#total_price_with_discount_and_delivery').html(data.totalPrice);
                $('#total_price_without_discount_and_delivery').html(data.totalPrice);
                $('#totalItemPrice' + id).text(data.newItemTotalPrice);
            },
            error: function (data) {
                alert('fail ' + data);
            }
        });
    };
    var clearCart = function () {
        $.ajax({
            type: 'POST',
            url: '<c:url value="/clearCart"/>',
            dataType: 'JSON',
            success: function (data) {
                $('#cart_total_price').html(0);
                $('#cart_items_number').html(0);
                $('#total_price_with_discount_and_delivery').html(0);
                $('#total_price_without_discount_and_delivery').html(0);
                removeAllLines();
            },
            error: function (data) {
                alert('fail ' + data);
            }
        });
    };

    var increaseNumberOfItems = function (id) {
        var divUpd = $('#numberItems' + id).text(), newVal = parseInt(divUpd) + 1;
        changeNumberOfItems(id, newVal);
        $('#numberItems' + id).text(newVal);
        console.log('inc = ' + $('#numberItems' + id).text());
    };
    var decreaseNumberOfItems = function (id) {
        var divUpd = $('#numberItems' + id).text(), newVal = parseInt(divUpd) - 1;
        if(newVal>0){
            changeNumberOfItems(id, newVal);
            $('#numberItems' + id).text(newVal);
            console.log('desc = ' + $('#numberItems' + id).text());
        }
    };
</script>
</html>