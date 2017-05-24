$(function () {

    var $registrationForm = $('#registration-form');

    function register() {
        var request = Utils.collectInputValues($registrationForm);

        $.ajax({
            url: '/register',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(request)
        }).done(function () {
            Utils.successMessage('Success');
        }).fail(function (jqXHR) {
            Utils.errorMessage('Error');
            console.debug(jqXHR);
        });
    }

    $registrationForm.on('submit', function (event) {
        event.preventDefault();
        register();
    });
});