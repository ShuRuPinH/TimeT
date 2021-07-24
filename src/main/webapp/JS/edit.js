
function conf (name) {
    var form = document.getElementById(name);

//Declare your event handler
    var onMyFormSubmit = function (e) {
        e = e || window.event; //Get the event if it's not passed in.

        var valid = false;

        if (!confirm(`Вы уверены, что хотите удалить запись: `+name+`? \n Отменить будет невозможно.`)) { //Do your validation here.
            e.returnValue = false;
            e.preventDefault();
            return false;
        }
    };

//Attach an event handler to the submit event.
    if (form.addEventListener) {
        form.addEventListener('submit', onMyFormSubmit, false);
    } else {
        form.attachEvent('onsubmit', onMyFormSubmit);
    }

}

