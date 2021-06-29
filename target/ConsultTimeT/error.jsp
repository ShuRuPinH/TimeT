<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>

<html>
<head>
    <title>ERROR </title>
</head>
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
        <td><%= exception%></td>
        <td><%=message%></td>
    </tr>

    </tbody>
</table>

</body>
</html>
