<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf"/>
<title>Редактирование пользователя</title>
</head>
<body>

<form name="edit_user_params" id="edit_user_params" method="POST"  accept-charset="UTF-8" action="/edit_user_params">
<div class="container">
    <div class="row">
        <div class="mx-auto text-center col-md-8">
            <input hidden="hidden" name="user" value="${userReq.login}">
            <input hidden="hidden" name="pass_adm_lim" id="pass_adm_lim">
            <h3 style="font-weight: bolder" class="display-4">Редактирование пользователя</h3>
            <hr>  ${userReq.login}
            <hr>


            <p class="lead text-primary">Изменить параметры  пользователя, посмотреть его сессии и
                напоминания.</p>
        </div>
    </div>
</div>
<div class="contentContainer">
    <div style="background-color: grey; margin-bottom: 10px" class="row">
        <div class="col-md-3">
            <h5 style="color: white" class="">Параметр</h5>

        </div>

        <div class="col-md-3">
            <h5 style="color: white" class="">Текущее значение</h5>
        </div>
        <div class="col-md-4">
            <h5 style="color: white" class="">Новое значение</h5>
        </div>
        <div class="col-md-2">
            <h5 style="color: white" class="">Действие</h5>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h5 class="">Пароль</h5>
        </div>
        <div class="col-md-3">
            <h5 class="">* * * * *</h5>
        </div>
        <div class="col-md-4">
            <h5 class=""><input type="text" name="pass"/></h5>
        </div>
        <div class="col-md-2">
            <div class="dropdown">
                <button style="margin-bottom: 5px" class="btn btn-warning " onclick="check()" value="0" type="button">
                    Изменить

                </button>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <h5 class="">Администратор?</h5>
        </div>
        <div class="col-md-3">
            <h5 class="">${userReq.admin}</h5>
        </div>
        <div class="col-md-4">
            <h5 class=""><select name="adm" class="form-select" aria-label="Default select example">
                <option selected>True - админ, False - юзер</option>
                <option value="true">True</option>
                <option value="false">False</option>

            </select></h5>
        </div>
        <div class="col-md-2">
            <div class="dropdown">
                <button disabled="disabled" style="margin-bottom: 5px" class="btn btn-warning " onclick="document.getElementById('pass_adm_lim').value=1;" type="submit">
                    Изменить
                </button>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <h5 class="">Лимит СМС в день</h5>
        </div>
        <div class="col-md-3">
            <h5 class="">${userReq.limit}</h5>
        </div>
        <div class="col-md-4">
            <h5 class=""><input  type="number" min="0" max="2000" name="limit"/></h5>
        </div>
        <div class="col-md-2">
            <div class="dropdown">
                <button  style="margin-bottom: 5px" class="btn btn-warning " onclick="document.getElementById('pass_adm_lim').value=2;" type="submit">
                    Изменить
                </button>
            </div>
        </div>
    </div>



</div></form>
<div class="d-grid gap-2 col-6 mx-auto"><hr>
    <button class="btn btn-dark" onclick="history.back()" type="button">Н А З А Д</button>

</div>
<script src="JS/change_pass.js"></script>
<jsp:include page="html_parts/bottom.jspf"/>