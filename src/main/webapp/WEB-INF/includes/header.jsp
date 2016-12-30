<%@ taglib prefix="ct" uri="http://epam.com/tags/ctags" %>
<%@ taglib prefix="headert" uri="http://epam.com/tags/ftags" %>
<link href="<c:url value="/css/languageDropdownStyle.css" />"
      rel="stylesheet">
<nav class="navbar-default navbar-inverse" style="background: #2f2f2f" role="navigation">
    <div class="container-fluid">
        <headert:locale locale="${com_epam_pre_prod_locale}"/>
        <headert:headerTag user="${com_epam_pre_prod_user}"/>
    </div>
</nav>