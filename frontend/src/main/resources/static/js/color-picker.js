(function () {

    const COLOR_PICKER_DATA_KEY = 'ColorPicker';
    const COLOR_ITEM_SELECTOR = '.jsColor';
    const CHANGE_COLOR_EVENT = 'change.color';

    function ColorPicker($parentElement, options) {

        var self = this;
        self.currentColor = options.initialColor;

        $parentElement.find(COLOR_ITEM_SELECTOR).click(function (e) {
            e.preventDefault();

            self.currentColor = $(this).css("color");

            $parentElement.trigger(CHANGE_COLOR_EVENT, {color: self.currentColor});
        });
    }

    ColorPicker.prototype = {
        getCurrentColor: function () {
            return this.currentColor;
        }
    };


    $.fn.colorPicker = function (options) {
        var args = $.makeArray(arguments);
        var result = this;

        this.each(function () {
            var $this = $(this);
            var instance = $this.data(COLOR_PICKER_DATA_KEY);

            //such call is treated as an instance method invocation
            if (typeof options === 'string') {
                result = instance[options].apply(instance, args.slice(1));

                //stop iteration over the elements performed by 'each' method
                return false;
            } else {
                if (instance) {
                    throw 'ColorPicker have already been initialized on current element';
                }

                //initialize
                $this.data(COLOR_PICKER_DATA_KEY, new ColorPicker($this, options));
            }
        });

        return result;
    };

})();