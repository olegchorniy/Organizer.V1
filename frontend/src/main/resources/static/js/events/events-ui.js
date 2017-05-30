$(function () {

    var $calendar = $('#calendar');

    var $newEvents = $('#external-events');

    var $addEventButton = $("#add-new-event");
    var $colorChooser = $("#color-chooser");
    var $newEventTitle = $("#new-event");

    /* ------------ Initialize color picker ------------ */
    var colorPickerOpts = {
        initialColor: $addEventButton.css('background-color')
    };

    $colorChooser.colorPicker(colorPickerOpts)
        .on('change.color', function (event, data) {
            $addEventButton.css({
                "background-color": data.color,
                "border-color": data.color
            });
        });

    /* ------------ Initialize button for new events adding. ------------ */

    $addEventButton.click(function (e) {
        e.preventDefault();

        var title = $newEventTitle.val();
        if (title.length === 0) {
            return;
        }

        var $closer = createCloser();

        var currentColor = $colorChooser.colorPicker('getCurrentColor');
        var $newEvent = $("<div />")
            .html(title)
            .prepend($closer)
            .addClass("external-event")
            .css({
                "background-color": currentColor,
                "border-color": currentColor,
                "color": "#fff"
            });

        $closer.on('click', function () {
            $newEvent.remove();
        });

        $newEvents.prepend($newEvent);
        initEvents($newEvent);

        $newEventTitle.val("");
    });

    function initEvents(ele) {
        ele.each(function () {
            var $this = $(this);

            $this.data('eventObject', {
                title: $.trim($this.text())
            });

            $this.draggable({
                zIndex: 1070,
                revert: true, // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
        });
    }

    /* ------------ Initialize calendar ------------ */

    $calendar.fullCalendar({
        header: {
            left: 'prev,next',
            center: 'title',
            right: 'today'
        },
        buttonText: {
            today: 'today'
        },
        events: loadEvents,
        editable: false,
        droppable: true,
        drop: function (date, event, ui) {
            var $this = $(this);

            var originalEventObject = $this.data('eventObject');
            var copiedEventObject = $.extend({}, originalEventObject);

            copiedEventObject.start = date;
            copiedEventObject.allDay = true;
            copiedEventObject.color = $this.css("background-color");

            addEvent(copiedEventObject, $this);
        },
        eventRender: function (event, $element) {
            var $closer = createCloser();

            $closer.click(removeEvent.bind(null, event._id));
            $element.prepend($closer);
        }
    });

    function createCloser() {
        return $('<span style="cursor: pointer" class="removeEvent glyphicon glyphicon-trash pull-right jsClose"></span>');
    }

    /* --------------- Event manipulation functions --------------- */

    function loadEvents(start, end, timezone, callback) {
        EventsApi.loadEvents(start.valueOf(), end.valueOf())
            .done(function (events) {
                console.log('Received events', events);

                callback(events.map(transformEvent));
            })
            .fail(function (jqXHR) {
                console.log('Cannot load events', jqXHR);

                Utils.errorMessage('Unable to load events');
            });
    }

    function transformEvent(event) {
        return {
            id: event.id,
            title: event.title,
            start: event.start,
            color: event.color,
            allDay: true
        };
    }

    function removeEvent(eventId) {
        EventsApi.removeEvent(eventId)
            .done(function () {
                $calendar.fullCalendar('removeEvents', eventId);
            })
            .fail(function (jqXHR) {
                console.log('Cannot remove event', jqXHR);
                Utils.errorMessage('Unable to remove event ' + eventId);
            });
    }

    function addEvent(eventObject, $eventBlock) {
        var eventRequest = {
            title: eventObject.title,
            start: eventObject.start.valueOf(),
            color: eventObject.color
        };

        EventsApi.createEvent(eventRequest)
            .done(function (eventResponse) {
                eventObject.id = eventResponse.eventId;
                $calendar.fullCalendar('renderEvent', eventObject);
                $eventBlock.remove();
            })
            .fail(function (jqXHR) {
                console.log('Cannot create event', jqXHR);
                Utils.errorMessage('Unable to create event ' + eventObject.title);
            });
    }
});