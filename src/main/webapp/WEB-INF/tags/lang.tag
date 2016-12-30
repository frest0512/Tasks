<%@ tag body-content="empty" %>
<%@ attribute name="locale" rtexprvalue="true" required="true" type="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://epam.com/tags/funcs" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ul class="nav navbar-nav navbar-left">
    <li><a href="<c:url value="/"/>"><fmt:message key="sub.header.home"/></a></li>
    <li class="w3-hide-small lan-dropdown" style="float:right;">
        <a href="#" class="lan-dropbtn">
            <c:choose>
                <c:when test="${pageContext.request.locale.language=='en'}">
                    <img src="<c:url value="/images/language/en.png"/>" alt="" width="30" height="20">
                </c:when>
                <c:when test="${pageContext.request.locale.language=='ru'}">
                    <img src="<c:url value="/images/language/ru.png"/>" alt="" width="30" height="20">
                </c:when>
            </c:choose>
        </a>
        <div class="lan-dropdown-content">
            <a class="lan-itemlist"
               href="<c:url value="${pageCameFrom}${fn:addLang(pageContext.request.queryString,'lang=en')}"/>">
                <img src="<c:url value="/images/language/en.png"/>" alt="" width="30" height="20">
            </a>
            <a class="lan-itemlist"
               href="<c:url value="${pageCameFrom}${fn:addLang(pageContext.request.queryString,'lang=ru')}"/>">
                <img src="<c:url value="/images/language/ru.png"/>" alt="" width="30" height="20">
            </a>

        </div>
    </li>
</ul>