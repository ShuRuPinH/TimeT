<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
    <title>Авторизация</title>

</head>
<body>
<br><br>
<div class="container h-100">
    <div class="row" style="height:20%">

    </div>
    <div class="row " style="height:30%">


        <div class="col-3">

        </div>
        <div class="shadow p-3 mb-5 bg-white rounded col-6">
            <div style="text-align: center" class="d-grid gap-2">
                <h3> Авторизация / регистрация</h3>
            </div>
            <div class="tab-content">
                <div id="signInTab" class="tab-pane fade show active">
                    <br>
                    <form id="main_form" name="avtor" accept-charset="UTF-8" method="post" action="/check"  >

                        <div class="form-group input-group">

                            <input type="email" id="email" name="email" class="form-control"  aria-label="Default"
                                   aria-describedby="inputGroup-sizing-default" placeholder="Введите Вашу почту">
                        </div>
                        <br>
                        <div class="form-group input-group">

                            <input type="password" id="password" name="password" class="form-control" aria-label="Default"
                                   aria-describedby="inputGroup-sizing-default" placeholder="Введите Ваш пароль"
                                   data-toggle="tooltip" data-placement="bottom" title="Минимум 8 символов.
Обязательно присутствие строчных и заглавных латинских букв,
цифр, и специальных или кириллических символов.">
                        </div>
                        <br>
                        <div style="text-align: right" class="row">
                            <div class="col-12">
                                <div id="2nd_pass" class="custom-control custom-checkbox ">

                                    <span style="color: #326ba8; font-weight: bolder " onclick="reg()">
                                        зарегистрироваться
                                    </span>
                                </div>
                            </div>

                        </div>
                        <div class="d-grid gap-2">
                            <input type="hidden" name="auth" value="0102">
                            <button type="button"  class=" btn btn-primary shadow " onclick="check()" >В О Й Т И</button>
                            <a href="forget.html"style="    margin-left: auto;
    margin-right: auto;"   class="  brand-link">Не помню пароль ;(</a>
                        </div>

                    </form>
                </div>


            </div>
        </div>
        <div class="col-3">

        </div>
    </div>

</div>



<script src="JS/dig.js">

</script>
</body>
</html>
