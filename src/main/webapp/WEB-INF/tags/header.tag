<%@ tag body-content="empty" %>
<%@ attribute name="user" rtexprvalue="true" required="true" type="com.epam.task11.entity.db.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<a href="<c:url value="/cart"/>">
    <div class="cart box_1" style="padding-top:10px;padding-left:10px;">
        <h3>
            <div class="total">
                $<span
                    id="cart_total_price">${not empty com_epam_pre_prod_cart?com_epam_pre_prod_cart.totalPrice():0}</span>
                (<span id="cart_items_number">${not empty com_epam_pre_prod_cart?com_epam_pre_prod_cart.size():0}</span>
                <fmt:message key="header.goods.number"/>)
            </div>
            <img src="images/cart.png" alt=""/>
        </h3>
        <p class="simpleCart_empty"><fmt:message key="header.empty.cart"/></p>
    </div>
</a>
<c:choose>
    <c:when test="${not empty user}">
        <ul class="nav navbar-nav navbar-right" style="padding-right: 100px">
            <li>
                <a style="margin-top: 12px;margin-bottom: 8px;" href="${pageContext.servletContext.contextPath}/">
                    <img src="<c:url value="/avatar?imageName=${user.avatarName}&type=avatar"/>"
                         style="border-radius: 50%;" width="30" height="30"/>
                        ${user.userName}
                </a>
            </li>
            <li><a href="<c:url value="/logout"/>"><fmt:message key="button.log.out"/></a></li>
        </ul>
    </c:when>
    <c:otherwise>
        <form class="navbar-form navbar-right" method="post" action="<c:url value="/login"/>"
              style="padding-right: 100px">
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="<fmt:message key="login.username"/>">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.password"/>">
            </div>
            <input type="hidden"
                    name="urlToRedirect"
                    value="${pageCameFrom}${not empty pageContext.request.queryString?'?':''}${pageContext.request.queryString}">
            <button type="submit" class="btn btn-default"><fmt:message key="button.log.in"/></button>
            <a href="<c:url value="/register"/>" style="margin-left: 20px" class="btn btn-default"><fmt:message key="button.register"/></a>
        </form>
    </c:otherwise>
</c:choose>
