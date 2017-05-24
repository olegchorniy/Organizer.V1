(function () {

    window.Utils = {
        errorMessage: function (text) {
            alert('Error: ' + text);
        },

        successMessage: function (text) {
            alert('Success: ' + text);
        },

        collectInputValues: function ($container) {
            var values = {};

            $container.find('input').each(function () {
                values[$(this).attr('name')] = $(this).val();
            });

            return values;
        }
    };
})();