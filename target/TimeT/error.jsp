<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
    <title>ERROR </title>

    <meta charset="UTF-8" />
</head>

<%
    String message = pageContext.getException().getMessage();
    String exceptions = pageContext.getException().getClass().toString();
%>

<body><br><br><br>
<table style="  margin-left: auto;
    margin-right: auto;
     border-collapse: collapse; width: 50%;" border="0">
    <tbody>
    <tr>
        <td style=""><span style="font-size: 36pt; font-family: impact, sans-serif;">EXEPTION</span></td>
        <td style=""><span
                style="font-size: 24pt; font-family: impact, sans-serif;">Something wrong with exeption...</span></td>

    </tr>
    <tr>
        <td style="">&nbsp;</td>
        <td style=""><span style="font-size: 24pt; font-family: impact, sans-serif;">Где-то вылетело исключение</span>
        </td>

    </tr>
    <tr>
        <td><%= exceptions%></td>
        <td><%=message%></td>
    </tr>




    </tbody>
</table>
<div class="d-grid gap-2 col-6 mx-auto"><br><br>
    <button class="btn btn-dark" onclick="history.back()" type="button">Н А З А Д</button>

</div>
</body>
</html>
