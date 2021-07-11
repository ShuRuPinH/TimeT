<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="html_parts/before_title.jspf"/>


<title>Личный кабинет</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="mx-auto text-center col-md-8">
            <h3 style="font-weight: bolder" class="display-4">Кабинет Пользователя </h3>
            <hr>
            ${user}
            <hr>
            <p class="lead text-primary">В нужный момент вам придет СМС с вашим текстом,
                можно задать некоторое кол-во повторов, через нужный интервал.
                * Только на номера России. </p>
        </div>
    </div>
</div>
<form name="addnote"  method="POST" accept-charset="UTF-8" action="/add_note">
    <input hidden="hidden" name="user" value="${user}">


    <div class="contentContainer">
        <div style="background-color: deepskyblue; margin-bottom: 0px" class="row">

            <h3 style="text-align: center; color: white" class="">Добавить напоминание</h3>

        </div>
        <div style="background-color: grey; margin-bottom: 10px" class="row">
            <div class="col-md-2">
                <h5 style="color: white" class="">Дата и время</h5>

            </div>
            <div class="col-md-2">
                <h5 style="color: white" class="">Номер телефона</h5>

            </div>
            <div class="col-md-4">
                <h5 style="color: white" class="">Текст</h5>
            </div>
            <div class="col-md-2">
                <h5 style="color: white" class="">Повторы</h5>
            </div>
            <div class="col-md-2">
                <h5 style="color: white" class="">Интервал</h5>
            </div>
        </div>
        <div style="margin-bottom: 10px" class="row">
            <div class="col-md-2">
                <h5 class=""><input class="form-control" id="datetime" name="datetime" required type="datetime-local">
                </h5>

            </div>
            <div class="col-md-2">
                <h5 style="color: white" class=""><input class="form-control" type="text" id="phone" name="phone"
                                                         pattern="[8]{1} [0-9]{3} [0-9]{7}" required
                                                         placeholder="8 777 1234567"></h5>
            </div>
            <div class="col-md-4">
                <h5 style="color: white" class=""><textarea class="form-control" maxlength="160" id="mess" name="mess" cols="25"
                                                             required
                                                            placeholder="Хотел напомнить, чтобы не забыть..."></textarea>
                </h5>
            </div>
            <div class="col-md-2">
                <h5 style="color: white" class=""><input class="form-control" min="0" step="1" max="10" name="reps"
                                                         type="number" placeholder="+ X раз"></h5>
            </div>
            <div class="col-md-2">
                <h5 style="color: white" class=""><input class="form-control" name="intr" min="0" step="0.1"
                                                         type="number" placeholder="0.5 мин"></h5>
            </div>
        </div>

        <div style="background-color: deepskyblue; margin-bottom: 1px" class="row">
            <div class="col-md-2">
                <p style="color: gray" class="">Время МСК (GMT+3)<br>

                    YYYY-MM-DD'T'HH:MM (2021-05-20T15:28)</p>

            </div>
            <div class="col-md-2">
                <p style="color: gray" class="">Формат: 8 777 1234567<br>
                    Только по России, начинать с '8'</p>

            </div>
            <div class="col-md-4">
                <p style="color: gray" class="">СМС может уместить до 70 знаков на кириллице или до 160 знаков на
                    латинице</p>
            </div>
            <div class="col-md-2">
                <span style="font-size: smaller; color: yellow" class="">необязательное</span>
                <p style="color: gray" class="">Столько раз, но не более 10</p>
            </div>
            <div class="col-md-2">
                <span style="font-size: smaller; color: yellow" class="">необязательное</span>
                <p style="color: gray" class="">через столько минут</p>
            </div>
        </div>


        <div class="d-grid gap-2">
            <button id="thebutton" class="row btn btn-info" type="submit">Добавить напоминание в очередь</button>
        </div>
    </div>
</form>
<br><br>
<div class="contentContainer">
    <div style="background-color: dodgerblue; margin-bottom: 4px" class="row">

            <h3 style="text-align: center; color: white" class="">Актуальные напоминания </h3>

    </div>
    <div style="background-color: grey; margin-bottom: 4px" class="row">
        <div class="col-md-3">
            <h4 style="color: white" class="">Дата и время </h4>

        </div>
        <div class="col-md-1">
            <h4 style="color: white" class="">Телефон</h4>
        </div>
        <div class="col-md-3">
            <h4 style="color: white" class="">Текст </h4>
        </div>
        <div class="col-md-2">
            <h4 style="color: white" class="">Повторы</h4>
        </div>
        <div class="col-md-2">
            <h4 style="color: white" class="">Интервал</h4>
        </div>
        <div class="col-md-1">
            <h6 class=""></h6>
        </div>
    </div>
    <c:set var="cnt" value="0"/>
    <c:forEach var="note" items="${notes}">
        <form method="post"  onsubmit="return confirm('Вы уверены?');" action="/add_note">
            <input hidden name="delete" value="1">
            <input hidden name="user" value="${note.user}">
            <input hidden name="create" value="${note.create}">
            <input hidden name="start" value="${note.start}">

            <div style="background-color: mediumturquoise;padding-top: 7px; margin-bottom: 2px" class="row">
                <div class="col-md-3">
                    <p id="date_time_${cnt}">
                        <script>
                            document.getElementById("date_time_${cnt}").innerHTML = new Date(${note.start}).toLocaleString('ru-RU');
                        </script>
                    </p>
                    <c:set var="cnt" value="${cnt + 1}" scope="page"/>
                </div>
                <div class="col-md-1">
                    <h6 class="">${note.number} </h6>
                </div>
                <div class="col-md-3">
                    <h6 class=""> ${note.message} </h6>
                </div>
                <div class="col-md-2">
                    <h6 class="">Повторится ${note.repeats} раз</h6>
                </div>
                <div class="col-md-2">
                    <h6 class="">с интервалом в ${note.interval} мин.</h6>
                </div>
                <div  class="col-md-1">
                    <input style="border: none; "  type="submit" value=""  id="del_note"/>
                </div>
            </div>

        </form>
    </c:forEach>

</div>
<br><br>
<div class="contentContainer">
    <div style="background-color: #1237c6; margin-bottom: 4px" class="row">

        <h3 style="text-align: center; color: white" class="">Архив напоминаний </h3>
    </div>
        <div style="background-color: grey; margin-bottom: 4px" class="row">
            <div class="col-md-3">
                <h4 style="color: white" class="">Дата и время </h4>

            </div>
            <div class="col-md-1">
                <h4 style="color: white" class="">Телефон</h4>
            </div>
            <div class="col-md-3">
                <h4 style="color: white" class="">Текст </h4>
            </div>
            <div class="col-md-2">
                <h4 style="color: white" class="">Повторы</h4>
            </div>
            <div class="col-md-2">
                <h4 style="color: white" class="">Интервал</h4>
            </div>
            <div class="col-md-1">
                <h6 class=""></h6>
            </div>
        </div>

</div>

</body>
</html>
