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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<!--banner-->
<div class="banner-top">
    <div class="container">
        <h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Products</h2>
        <h3 class="animated wow fadeInRight" data-wow-delay=".5s"><a
                href="<c:url value="/"/>">Home</a><label>/</label>Products</h3>
        <div class="clearfix"></div>
    </div>
</div>
<!--content-->
<div class="product">
    <div class="container">

        <div class="col-md-3 product-bottom">
            <div class="price animated wow fadeInLeft" data-wow-delay=".5s" style="margin-bottom: 20px">
                <h3><fmt:message key="products.searching.form"/></h3>
            </div>
            <div class="animated wow fadeInLeft" data-wow-delay=".5s">
                <form action="<c:url value="/products"/>" method="get">
                    <div class="contact-form2">
                        <h4><fmt:message key="products.searching.form.price"/></h4>
                        <input name="priceFrom" type="text" placeholder="<fmt:message key="products.searching.form.price.placeholder.from"/>"
                               value="${com_epam_pre_prod_product_form.searchForm.priceFrom}"
                               style="width: 45%; margin-right: 8%">
                        <input name="priceTo" type="text" placeholder="<fmt:message key="products.searching.form.price.placeholder.to"/>"
                               value="${com_epam_pre_prod_product_form.searchForm.priceTo}" style="width: 45%;">
                    </div>
                    <div class="contact-form2">
                        <h4><fmt:message key="products.searching.form.product"/></h4>
                        <input name="productName" type="text"
                               value="${com_epam_pre_prod_product_form.searchForm.productName}"
                               placeholder="<fmt:message key="products.searching.form.product.placeholder"/>">
                    </div>
                    <div class="contact-form2">
                        <h4><fmt:message key="products.searching.form.manufacturer"/></h4>
                        <c:forEach items="${com_epam_pre_prod_manufacturers}" var="manufacturer">
                            <input name="manufacturer" type="checkbox"
                                   value="${manufacturer.id}" ${fn:contains(com_epam_pre_prod_product_form.searchForm.manufacturer,manufacturer.id)?'checked':''}> ${manufacturer.name}
                            <br/>
                        </c:forEach>
                    </div>
                    <div class="contact-form2">
                        <h4><fmt:message key="products.searching.form.category"/></h4>
                        <c:forEach items="${com_epam_pre_prod_categories}" var="category">
                            <input name="category" type="checkbox"
                                   value="${category.id}" ${fn:contains(com_epam_pre_prod_product_form.searchForm.categories,category.id)?'checked':''}> ${category.name}
                            <br/>
                        </c:forEach>
                    </div>
                    <input type="submit" value="<fmt:message key="button.search"/>" class="btn btn-lg btn-primary" style="width: 100%">
                </form>
            </div>
        </div>

        <%--middle--%>
        <div class="col-md-9 animated wow fadeInRight" data-wow-delay=".5s">
            <div class="mens-toolbar">
                <p><fmt:message key="products.showing"/> ${com_epam_pre_prod_products_range_from}–${com_epam_pre_prod_products_range_to}
                    <fmt:message key="products.showing.of"/> ${com_epam_pre_prod_products_number}</p>

                <p class="showing"><fmt:message key="products.order.by"/>
                    <select onchange="javascript:location.href = this.value">
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber}" sortBy="name asc" itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.sortingType == 'name asc'?'selected':''}>
                            Name↓
                        </option>
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber}" sortBy="name desc" itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.sortingType == 'name desc'?'selected':''}>
                            Name↑
                        </option>
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber}" sortBy="price asc" itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.sortingType == 'price asc'?'selected':''}>
                            Price↓
                        </option>
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber}" sortBy="price desc" itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.sortingType == 'price desc'?'selected':''}>
                            Price↑
                        </option>

                    </select>
                </p>
                <p><fmt:message key="products.show.number.items.on.page"/>
                    <select onchange="javascript:location.href = this.value">
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="1" sortBy="${com_epam_pre_prod_product_form.sortingType}" itemsOnPage="9" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.itemsNumberOnPage == 9?'selected':''}>
                            9
                        </option>
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="1" sortBy="${com_epam_pre_prod_product_form.sortingType}" itemsOnPage="18" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.itemsNumberOnPage == 18?'selected':''}>
                            18
                        </option>
                        <option value="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="1" sortBy="${com_epam_pre_prod_product_form.sortingType}" itemsOnPage="30" param="${com_epam_pre_prod_product_form.searchForm}"/>" ${com_epam_pre_prod_product_form.itemsNumberOnPage == 30?'selected':''}>
                            30
                        </option>
                    </select>
                </p>


                <div class="clearfix"></div>
            </div>


            <div class="mid-popular">
                <c:if test="${com_epam_pre_prod_products.size()==0}">
                    <div align="center">
                        No products found...
                    </div>
                </c:if>
                <c:forEach items="${com_epam_pre_prod_products}" var="product">

                    <div class="col-sm-4 item-grid item-gr simpleCart_shelfItem" align="center">
                        <div class="grid-pro">
                            <div class=" grid-product ">
                                <figure>
                                    <a href="../single.html">
                                        <div class="grid-img">
                                            <img src="<c:url value="/avatar?imageName=${product.imageSecond}&type=product"/>"
                                                 class="img-responsive"
                                                 height="408"
                                                 width="300"
                                                 alt="">
                                        </div>
                                        <div class="grid-img">
                                            <img src="<c:url value="/avatar?imageName=${product.imageFirst}&type=product"/>"
                                                 class="img-responsive"
                                                 height="408"
                                                 width="300"
                                                 alt="">
                                        </div>
                                    </a>
                                </figure>
                            </div>
                            <div class=" ">
                                    ${product.name}
                                <h6><a href="../single.html"><fmt:message key="products.product.detail"/></a></h6>
                                <p>
                                    <del>${product.price}</del>
                                    <em class="item_price">${product.price}</em></p>

                                <a id="get-data" data-text="<fmt:message key="products.product.add.to.cart"/>" class="but-hover1 item_add add_to_cart"
                                   onclick="btnClick(${product.id})"><fmt:message key="products.product.add.to.cart"/></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
                <c:if test="${com_epam_pre_prod_products.size()!=0}">
                    <div align="center">
                        <ul class="pagination">
                            <c:if test="${com_epam_pre_prod_product_form.pageNumber!=1}">
                                <li><a href="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber-1}"
                                    sortBy="${com_epam_pre_prod_product_form.sortingType}"
                                    itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}"
                                    param="${com_epam_pre_prod_product_form.searchForm}"/>"><<</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${com_epam_pre_prod_number_of_pages}" step="1" var="i">
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${i}"
                                    sortBy="${com_epam_pre_prod_product_form.sortingType}"
                                    itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}"
                                    param="${com_epam_pre_prod_product_form.searchForm}"/>">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${com_epam_pre_prod_product_form.pageNumber!=com_epam_pre_prod_number_of_pages}">
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/products?<ct:productUrl page="${com_epam_pre_prod_product_form.pageNumber+1}"
                                    sortBy="${com_epam_pre_prod_product_form.sortingType}"
                                    itemsOnPage="${com_epam_pre_prod_product_form.itemsNumberOnPage}"
                                    param="${com_epam_pre_prod_product_form.searchForm}"/>">>></a>
                                </li>
                            </c:if>

                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <%--//middle--%>


        <div class="clearfix"/>
    </div>
</div>
</div>
<!--//products-->
<%@ include file="includes/subfooter.jsp" %>
<%@ include file="includes/footer.jsp" %>
<!-- //footer -->
</body>
<script>
    var btnClick = function (id) {
        $.ajax({
            type: 'POST',
            url: '<c:url value="/addtocart"/>',
            dataType: 'JSON',
            data: 'productId=' + id,
            success: function (data) {
                $('#cart_total_price').html(data.totalPrice);
                $('#cart_items_number').html(data.totalItems);
            },
            error: function (data) {
                alert('fail ' + data);
            }
        })
    };
</script>
</html>