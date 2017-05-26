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

    /* ----------------------- jQuery Plugins Helper --------------------------- */

    window.Utils.defineJQueryPlugin = function (pluginName, pluginDataKey, pluginClass) {
        $.fn[pluginName] = function (options) {
            var args = $.makeArray(arguments);
            var result = this;

            this.each(function () {
                var $this = $(this);
                var instance = $this.data(pluginDataKey);

                //such call is treated as an instance method invocation
                if (typeof options === 'string') {
                    result = instance[options].apply(instance, args.slice(1));

                    //stop iteration over the elements performed by 'each' method
                    return false;
                } else {
                    if (instance) {
                        throw 'Searcher have already been initialized on current element';
                    }

                    //initialize
                    $this.data(pluginDataKey, new pluginClass($this, options));
                }
            });

            return result;
        };
    };
})();