$(document)
    .ready(
        function () {
            var first_name_pattern = new RegExp("^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$");
            var last_name_pattern = new RegExp("^([a-zA-Z]{1,20}|[\u0410-\u044f]{1,20})$");
            var email_pattern = new RegExp("^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$");
            var password_pattern = new RegExp("^[a-zA-Z0-9_]{1,20}$");
            var username_pattern = new RegExp("^[a-zA-Z0-9]{1,20}$");
            $('#first_name').focusout(
                function () {
                    var first_name = $('#first_name').val()
                    if (!first_name_pattern.test(first_name)) {
                        $('#error_first_name')
                            .text('First name is not correct');
                    } else {
                        $('#error_first_name')
                            .text('');
                    }

                })

            $('#last_name').focusout(
                function () {
                    var last_name = $('#first_name').val()
                    if (!last_name_pattern.test(last_name)) {
                        $('#error_last_name')
                            .text('Last name is not correct');
                    } else {
                        $('#error_last_name')
                            .text('');
                    }

                })

            $('#email')
                .focusout(
                    function () {
                        var email = $('#email').val()
                        if (!email_pattern.test(email)) {
                            $('#error_email').text('Email is not correct');
                        }
                        else {
                            $('#error_email').text('');
                        }

                    })
            $('#username')
                .focusout(
                    function () {
                        var username = $('#username').val()
                        if (!username_pattern.test(username)) {
                            $('#error_username').text('Username is not correct');
                        }
                        else {
                            $('#error_username').text('');
                        }

                    })
            $('#password').focusout(
                function () {
                    var password = $('#password').val()

                    if (!password_pattern.test(password)) {
                        $('#error_password').text('Password is not correct');
                    } else {
                        $('#error_password').text('');
                    }

                })
            $('#password_confirmation').focusout(
                function () {
                    var password = $('#password_confirmation').val()
                    var password_conf = $('#password').val()
                    if (password !== password_conf) {
                        $('#error_conf_password').text('Password is not correct');
                    } else {
                        $('#error_conf_password').text('');
                    }

                })

            $('#register_button')
                .click(
                    function (event) {
                        var first_name = $('#first_name').val()
                        var last_name = $('#last_name').val()
                        var username = $('#username').val()
                        var email = $('#email').val()
                        var password_confirmation = $('#password_confirmation').val()
                        var password = $('#password').val()
                        if (!first_name_pattern.test(first_name)) {
                            $('#error_first_name').text('First name is not correct');
                            event.preventDefault();
                        } else if (!last_name_pattern.test(last_name)) {
                            $('#error_last_name').text('Last name is not correct');
                            event.preventDefault();
                        } else if (email.indexOf("@") == -1) {
                            $('#error_email').text('Email is not correct');
                            event.preventDefault();
                        } else if (!email_pattern.test(email)) {
                            $('#error_email').text('Email is not correct');
                            event.preventDefault();
                        } else if (!password_pattern.test(password)) {
                            $('#error_password').text('Password is not correct');
                            event.preventDefault();
                        } else if (!username_pattern.test(username)) {
                            $('#error_username').text('Password is not correct');
                            event.preventDefault();
                        } else if (password !== password_confirmation) {
                            $('#error_conf_password').text('Password is not correct');
                            event.preventDefault();
                        }

                    });

        });