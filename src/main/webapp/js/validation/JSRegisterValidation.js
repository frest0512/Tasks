var first_name_pattern = new RegExp("^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$");
var last_name_pattern = new RegExp("^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$");
var email_pattern = new RegExp("^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$");
var password_pattern = new RegExp("^[a-zA-Z0-9_]{1,20}$");

document.getElementById("reg_form").addEventListener("submit", function (event) {
    var first_name = document.getElementById("first_name").value;
    var last_name = document.getElementById("last_name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var pass_conf = document.getElementById("password_confirmation").value;

    event.preventDefault();
    var isToSubmit = true;
    if (!first_name_pattern.test(first_name)) {
        document.getElementById("error_first_name").innerHTML = 'First name is not correct';
        isToSubmit = false;
    } else {
        document.getElementById("error_first_name").innerHTML = "";
    }
    if (!last_name_pattern.test(last_name)) {
        document.getElementById("error_last_name").innerHTML = 'Last name is not correct';
        isToSubmit = false;
    } else {
        document.getElementById("error_last_name").innerHTML = "";
    }
    if (!email_pattern.test(email)) {
        document.getElementById("error_email").innerHTML = 'Email is not correct';
        isToSubmit = false;
    } else {
        document.getElementById("error_email").innerHTML = "";
    }
    if (!password_pattern.test(password)) {
        document.getElementById("error_password").innerHTML = 'Password is not correct';
        isToSubmit = false;
    } else {
        document.getElementById("error_password").innerHTML = "";
    }
    if (pass_conf !== password) {
        document.getElementById("error_conf_password").innerHTML = 'Password is not correct';
        isToSubmit = false;
    } else {
        document.getElementById("error_conf_password").innerHTML = "";
    }
    if (isToSubmit) {
        event.submit();
    }
});