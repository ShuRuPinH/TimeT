<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


${header.get("cookie")}

<br><br>
<c:forEach var="d" items="${param}">
${d.key}   =   ${d.value}.
        </c:forEach><br>

<br><br>

${in}
////////////////////////
<%= pageContext.getRequest().getAttribute("is_del")%>
////////////////////////

<form method="post" action="test">
    <input placeholder="cookie" type="text" name="cookie">
    <input placeholder="val" type="text" name="val">
    <input placeholder="domain" type="text" name="domain">
    <input type="submit">

</form>
/////////// list args  /////// <br>

<%= session.getAttribute("list")%><br><br>

<c:set var="txt" value="list"/>
<c:forEach var="arz" items="${sessionScope.get(txt)}">
${arz}<br>
</c:forEach>
<br>

${sessionScope}

</body>
</html>
