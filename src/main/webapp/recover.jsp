<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
<title>Восстановление пароля </title>

<meta charset="UTF-8" />
</head>


<body><br><br><br>
<table style="  margin-left: auto;
    margin-right: auto;
     border-collapse: collapse; width: 50%;" border="0">
    <tbody>
    <tr>
        <td style=""><span style="font-size: 36pt; font-family: impact, sans-serif;">✰</span></td>
        <td style=""><span
                style="font-size: 24pt; font-family: impact, sans-serif;">Password recovery for: ${user}</span></td>

    </tr>
    <tr>
        <td style="">&nbsp;</td>
        <td style=""><span style="font-size: 16pt; font-family: impact, sans-serif;"> <form id="recc" name="recc" action="/verif">
            <h5 class=""><input type="text" placeholder="новый пароль" name="pass"/></h5>
            <input hidden name="mode" value="0110">  <input hidden name="user" value="${user}">
                <button style="margin-bottom: 5px" class="btn btn-warning " onclick="check()"  type="button">
                    Изменить
                </button></form>
            </span>
            <br>В случае успеха вас перенаправит на страницу авторизации.
        </td>

    </tr>





    </tbody>
</table>
<script>
    function check() {
        let form = document.forms.recc;


        var passw = form.elements.pass.value;
        let az = "Ошибки в пароле:\n", nn = 0;
        if (!/[a-z]/.test(passw)) az += "Нет строчных латинских символов.\n"; else nn++;
        if (!/[A-Z]/.test(passw)) az += "Нет заглавных латинских символов.\n"; else nn++;
        if (!/[0-9]/.test(passw)) az += "Нет  цифр.\n"; else nn++;
        if (!/\W/.test(passw)) az += "Нет  специальных или кириллических символов.\n"; else nn++;
        if (passw.length < 8) az += "Пароль менее 8 символов.\n"; else nn++;

        if (nn < 5) {
            alert(`   ${az}  `);
            document.getElementById("pass").focus();
            return;
        }


        document.getElementById("recc").submit();
    }


</script>
</body>
</html>
