<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
<title>Сервис напоминаний</title>


</head>
<body>


<div class="container">

    <div class="row">
        <div class="mx-auto text-center col-md-8"><br><img id="logo" src="images/index_logo.png"/><br>
            <h5 style="font-weight: bolder" class="display-5">Сервис СМС-напоминаний</h5><br>
            <p style="font-weight: bolder" class="lead text-secondary">
                Web-приложение с использованием Java технологий: Servlets, JSP, JSTL и пр.
            </p>
            <p style="font-weight: bolder" class="lead text-primary">В нужный момент вам придет СМС с вашим текстом,<br>
                можно задать некоторое кол-во повторов, через нужный интервал.<br> * Только на номера России.
            </p>
            <p class="lead text-primary">
                         Возможна организация СМС рассылок (в разработке).


            </p>
                <br>
            <div class="d-grid gap-2 col-6 mx-auto">

                    <button class="btn btn-dark" onclick="location.href='/login';" type="submit">Войти</button>


            </div>
        </div>
    </div></div>
</body>
</html>
