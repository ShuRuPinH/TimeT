<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
<html>
<head>
    <title>Title</title>
</head>
<body>


${header.get("cookie")}

<br><br>



<br><br>

${in}
////////////////////////
<p id="rez"> be be be</p>
////////////////////////

<form method="post" action="/test_01">
    <input value="val"  type="text" name="name">
    <input id="val" type="text" name="val">

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


<hr>
<hr>

<% String clas = this.getClass().toString();

%>

<h4> Class = <%=clas%> </h4>

<script src="JS/base64.js">
</script>

<script>
   document.getElementById('rez').innerText = Base64Decode('${param.val}');

</script>;.
</body>
</html>
