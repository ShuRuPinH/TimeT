<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
    <title>ERROR </title>
</head>
<body><br><br><br>
<table style="  margin-left: auto;
    margin-right: auto;
     border-collapse: collapse; width: 50%;" border="0">
    <tbody>
    <tr>
        <td style=""><span style="font-size: 36pt; font-family: impact, sans-serif;">ERROR</span></td>
        <td style=""><span
                style="font-size: 24pt; font-family: impact, sans-serif;">Something wrong...</span></td>

    </tr>
    <tr>
        <td style="">&nbsp;</td>
        <td style=""><span style="font-size: 24pt; font-family: impact, sans-serif;">Что-то не так ...</span>
        </td>

    </tr>
    <tr><td></td><td><br><br></td></tr>
    <tr>
        <td><%= request.getAttribute("er_type")%>:</td>
        <td><%= request.getAttribute("error")%></td>
    </tr>

    </tbody>
</table>

</body>
</html>
