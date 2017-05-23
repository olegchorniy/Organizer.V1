$(function () {

    var $registrationForm = $('#registration-form');

    function register() {
        var request = {};

        $registrationForm.find('input').each(function () {
            request[$(this).attr('name')] = $(this).val();
        });

        $.ajax({
            url: '/register',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(request)
        }).done(function () {
            alert('Success');
        }).fail(function (jqXHR) {
            alert('Error');
            console.debug(jqXHR);
        });
    }

    $registrationForm.on('submit', register);
});