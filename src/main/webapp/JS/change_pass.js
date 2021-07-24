function check() {
    let form = document.forms.edit_user_params;


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

    document.getElementById("pass_adm_lim").value=0;
    document.getElementById("edit_user_params").submit();
}