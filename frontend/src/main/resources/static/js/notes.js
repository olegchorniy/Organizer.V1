$(function () {

    window.NotesApi = {
        loadNotes: function () {
            return $.get('/notes/load');
        }
    };

    var $searcher = $('#searcher');

    $searcher.searcher().on('search', function (e, data) {
        console.log('Search by "' + data.value + '"');
    });
});