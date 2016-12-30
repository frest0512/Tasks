<div class="container">
    <div class="logo-nav" style="padding-left: 100px;">
        <nav class="navbar navbar-default">
            <!-- Brand and toggle exist grouped for better mobile display -->
            <div class="navbar-header nav_2">
                <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                        data-target="#bs-megadropdown-tabs">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="navbar-brand logo-nav-left ">
                    <h1 class="animated wow pulse" data-wow-delay=".5s"><a
                            href="${pageContext.servletContext.contextPath}/">MEGA<span>SHOP</span></a></h1>
                </div>
            </div>
            <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.servletContext.contextPath}/" class="act"><fmt:message key="sub.header.home"/></a></li>
                    <!-- Mega Menu -->
                    <li class="dropdown">
                        <a href="<c:url value="/products"/>"><fmt:message key="sub.header.products"/></a>
                    </li>
                    <li><a href="#bottom"><fmt:message key="sub.header.contact.us"/></a></li>
                </ul>
            </div>
        </nav>
    </div>

</div>