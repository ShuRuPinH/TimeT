var reg_true = false;

function reg() {
    reg_true = true;
    document.getElementById("2nd_pass").innerHTML = `<div class="form-group input-group">
        <input type="password" id="password2" name="password2"  class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" 
        placeholder="Повторите пароль"></div>
        <span style="color: #326ba8; font-weight: bolder " onclick="_reg()">зарегистрирован</span><br>`;
}
function _reg() {
    reg_true = false;
    document.getElementById("2nd_pass").innerHTML = `  <div id="2nd_pass" class="custom-control custom-checkbox ">
        <span style="color: #326ba8; font-weight: bolder " onclick="reg()">зарегистрироваться</span></div>`;
}
function check() {
    let form = document.forms.avtor;

    let email = form.elements.email.value;
    if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
        alert("Что-то не так с e-mail!");
        document.getElementById("email").focus();
        return;

    }
    var pass1 = form.elements.password.value;
    let az = "Ошибки в пароле:\n", nn = 0;
    if (!/[a-z]/.test(pass1)) az += "Нет строчных латинских символов.\n"; else nn++;
    if (!/[A-Z]/.test(pass1)) az += "Нет заглавных латинских символов.\n"; else nn++;
    if (!/[0-9]/.test(pass1)) az += "Нет  цифр.\n"; else nn++;
    if (!/\W/.test(pass1)) az += "Нет  специальных или кириллических символов.\n"; else nn++;
    if (pass1.length < 8) az += "Пароль менее 8 символов.\n"; else nn++;

    if (nn < 5) {
        alert(`   ${az}  `);
        document.getElementById("password").focus();
        return;
    }

    if (reg_true) {
        let pass2 = form.elements.password2.value;

        if (pass1 != pass2) {
            alert("Подтверждение пароля не совпадает с паролем!");
            document.getElementById("password2").focus();
            return;
        }
    }
    document.getElementById("main_form").submit();
}


/*



Задача 2, Реализация Ajax

При помощи чистого javascript или jquery реализовать технологию Ajax. Данные брать со своего же локального сервера,
который, в свою очередь, будет их читать из файла. Состав данных (количество и тип полей) определяется самостоятельно, исходя из решаемой задачи.
*/