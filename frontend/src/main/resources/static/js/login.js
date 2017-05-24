$(function () {

    var $email = $('#email');
    var $password = $('#password');

    function authenticate() {
        var email = $email.val();
        var password = $password.val();

        $.ajax({
            url: '/auth/login',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                password: password
            })
        }).done(function (response) {
            window.location = '/events';
        }).fail(function (jqXHR) {
            Utils.errorMessage('code = ' + jqXHR.status);
            console.log('Error', jqXHR);
        });
    }

    $('#login-form').on('submit', function (event) {
        event.preventDefault();
        authenticate();
    });
});