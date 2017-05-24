(function () {

    window.EventsApi = {

        loadEvents: function (start, end) {
            return $.get('/events/load', {start: start, end: end});
        },

        createEvent: function (event) {
            return $.ajax({
                url: '/events/create',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(event)
            });
        },

        removeEvent: function (eventId) {
            return $.ajax({
                url: '/events/remove',
                method: 'POST',
                data: {eventId: eventId}
            });
        }
    };

})();