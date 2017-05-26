(function ($) {

    var SEARCHER_DATA_KEY = 'Searcher';
    var SEARCH_EVENT = 'search';
    var ENTER_KEY_CODE = 13;

    function Searcher($element, options) {
        if (!$element.is('input')) {
            throw 'Searcher could be initialized only on "input" element.';
        }

        var self = this;
        $.extend(self, options);
        self._$input = $element;

        if (self.immediateSearchOnEnter && self.searchEvents.indexOf('keyup') === -1) {
            // 'keyup' event must be handled if we want to respond to the 'Enter' key pressing.
            self.searchEvents += ' keyup';
        }

        self._$input.on(self.searchEvents, function (event) {

            console.debug('Event in searcher: ', event);

            var newValue = self._$input.val();

            if (self.immediateSearchOnEnter
                && event.type == 'keyup'
                && event.keyCode == ENTER_KEY_CODE) {

                self._currentValue = newValue;

                clearTimeout(self._currentTimeoutId);
                self._fireEvent();

                return;
            }

            /* respond only to events which modify the value of the input */
            if (self._currentValue !== newValue) {
                self._currentValue = newValue;

                if (self._currentTimeoutId !== null) {
                    clearTimeout(self._currentTimeoutId);
                }

                self._currentTimeoutId = setTimeout(self._fireEvent.bind(self), self.waitingIntervalMillis);
            }
        });
    }

    Searcher.prototype = {
        /* Internal properties */
        _currentTimeoutId: null,
        _currentValue: null,
        _$input: null,

        /* Configurable properties */
        waitingIntervalMillis: 250,
        searchEvents: 'keyup change paste',
        immediateSearchOnEnter: true,

        _fireEvent: function () {
            this._$input.trigger(SEARCH_EVENT, {value: this._currentValue});
        }
    };

    Utils.defineJQueryPlugin('searcher', SEARCHER_DATA_KEY, Searcher);
})(jQuery);