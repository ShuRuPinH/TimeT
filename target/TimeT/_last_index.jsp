
<jsp:include page="html_parts/before_title.jspf" />
    <title>INDEX</title>

</head>

<body>

<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="/start">Start</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Login</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="test.jsp">Test JSTL</a>
    </li>
    <li class="nav-item">
        <a class="nav-link " href="script.jsp" tabindex="-1" aria-disabled="true">Disabled</a>
    </li>
</ul>


<form method="post" action="test" name="param">
    <input name="param1" type="text">
    <input type="submit">
</form>

<c:set var="in" scope="session" value="${param.in}" />

</body>


</html>
