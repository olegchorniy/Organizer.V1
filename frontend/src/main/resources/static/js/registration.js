$(function () {

    var $registrationForm = $('#registration-form');

    function serializeForm($form) {
        var values = {};

        $form.find('input').each(function () {
            values[$(this).attr('name')] = $(this).val();
        });

        return values;
    }

    function register() {
        var request = serializeForm($registrationForm);

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

    $registrationForm.on('submit', function (event) {
        event.preventDefault();
        register();
    });
});