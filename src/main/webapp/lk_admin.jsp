<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="html_parts/before_title.jspf" />
    <title>Панель администратора</title>
</head>
<div class="container">
    <div class="row">
        <div class="mx-auto text-center col-md-8">
            <h3 style="font-weight: bolder" class="display-4">Панель администратора</h3>
            <hr>
            <hr>
            <h4>Пользователи.</h4>
            <p class="lead text-primary">Можно забанить пользователя, войти под ним,
                отредактировать параметры  и ,конечно, удалить. </p>
    </div>
    </div></div>
<div class="contentContainer">
    <div style="background-color: grey; margin-bottom: 10px"  class="row">
        <div class="col-md-3">
            <h5 style="color: white" class="">Пользователи</h5>

        </div>
        <div class="col-md-7">
            <h5 style="color: white" class="">Информация</h5>
        </div>
        <div class="col-md-2">
            <h5 style="color: white" class="">Действия</h5>
        </div>
    </div>
<c:forEach var="user" items="${users}">

<form id="${user.login}" method="post" action="/admin_users">
<div  class="row">
    <div class="col-md-3">
        <input id="_login_" name="login" type="hidden" value="${user.login}">
        <h5 class="">${user.login}
                <c:if test="${user.admin}">
                    <c:out value="<span  class=\"badge\">Admin</span>"  escapeXml="false"/>
                </c:if>

            <c:if test="${user.block}">
                <c:out value="<span id=\"ban\" class=\"badge\">blocked</span>"  escapeXml="false"/>
            </c:if></h5>
    </div>
    <div class="col-md-7">
        <p class="lead">Последний визит ${user.getLastSesion()}</p>
    </div>
    <div class="col-md-2">
        <div class="dropdown">
            <button class="btn btn-warning dropdown-toggle" type="button"  data-bs-toggle="dropdown" aria-expanded="false">
                Действия
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><button name="ban_edt_ent_del" value="0" type="submit" class="dropdown-item" >Забанить / Разбанить</button></li>
                <li><button name="ban_edt_ent_del" value="1" type="submit"   class="dropdown-item" >Редактировать</button></li>
                <li><button name="ban_edt_ent_del" value="2" type="submit"  class="dropdown-item" >Войти под Юзером</button></li>
                <li><hr class="dropdown-divider"></li>
                <li><button name="ban_edt_ent_del" value="3" type="submit" onclick="conf(`${user.login}`)" class="dropdown-item" >Удалить</button></li>

            </ul>
        </div>
    </div>
    <hr>
</div>
</form>

</c:forEach>

</div>

<div class="row">
    <div class="mx-auto text-center col-md-6"><hr>
        <hr>
        <h4>Настроки приложения.</h4>
        <p class="lead text-primary">Изменить, добавить или удалить параметр приложения.</p>
        <p class="text-danger"><b> * Только латинские символы</b></p>
    </div>
</div>
<div class="contentContainer">
    <div style="background-color: grey; margin-bottom: 10px"  class="row">
        <div class="col-md-2">
            <h5 style="color: white" class="">Параметр</h5>

        </div>
        <div class="col-md-5">
            <h5 style="color: white" class="">Описание</h5>
        </div>
        <div class="col-md-1">
            <h5 style="color: white" class="">Текущее значение</h5>
        </div>
        <div class="col-md-2">
            <h5 style="color: white" class="">Новое значение</h5>
        </div>
        <div class="col-md-2">
            <h5 style="color: white" class="">Действия</h5>
        </div>
    </div>
    <form name="new_set" method="POST" accept-charset="UTF-8" action="/admin_edit">
    <div style="background-color: darkorange ;margin-bottom: 5px; padding-top: 10px" class="row">

        <div class="col-md-2">
            <h5 class=""><input style="width: 80%" name="name" placeholder="название"></h5>

        </div>
        <div class="col-md-5">
            <h5  class=""><input name="description" style="width: 80%" placeholder="описание паметра с ед. имерения"></h5>
        </div>
        <div class="col-md-1">
            <h5  class="">  </h5>
        </div>
        <div class="col-md-2">
            <h5  class=""><input style="width: 80%" type="number" name="value" value="0" placeholder="значение"></h5>
        </div>
        <div class="col-md-2">
            <div class="dropdown">
                <button style="margin-bottom: 5px" class="btn btn-warning " name="add_edt_del" value="0"  type="submit" id="dropdownMenuButton2" >
                    Добавить
                </button>

            </div>
        </div>


    </div> </form>
    <hr>

    <c:forEach var="set" items="${sets}">
    <form id="${set.name}" method="POST" action="/admin_edit">
        <div style="margin-bottom: 5px; padding-top: 5px" class="row">

                <div class="col-md-2">
                    <h5 class="">${set.name}
                    <c:if test="${set.system}">
                    <c:out value="<span id=\"system\" class=\"badge\">system</span>"  escapeXml="false"/>
                </c:if></h5>
                    <input name="name" type="hidden" value="${set.name}">
                </div>
                <div class="col-md-5">
                    <p  class="">${set.description}</p>
                    <input name="description" type="hidden" value="${set.description}">
                </div>
                <div class="col-md-1">
                    <h5  class="">${set.value}</h5>
                </div>
                <div class="col-md-2">
                    <h5  class=""><input style="width: 80%" type="number" name="value" value="0" placeholder="значение"></h5>
                </div>
                <div class="col-md-2">
                    <div class="dropdown">
                        <button class="btn btn-warning dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Действия
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><button class="dropdown-item" name="add_edt_del" value="1" type="submit" >Редактировать</button></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><button class="dropdown-item" name="add_edt_del" onclick="conf(`${set.name}`)" value="2" type="submit">Удалить</button></li>
                        </ul>
                    </div>
                </div>
        </div></form>
        <hr>


    </c:forEach>
</div>

<script src="JS/edit.js"></script>
</body>
</html>
