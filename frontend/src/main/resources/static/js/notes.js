$(function () {

    const NOTE_CLOSER = '.jsNoteClose';
    const NOTE_BLOCK = '.jsNote';
    const NOTE_ID_ATTR = 'data-note-id';

    window.NotesApi = {
        loadNotes: function (searchLine) {
            var params = {};
            if (searchLine) {
                params.searchLine = searchLine;
            }

            return $.get('/notes/load', params);
        },

        createNote: function (note) {
            return $.ajax({
                url: '/notes/create',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(note)
            });
        },

        updateNote: function (note) {
            return $.ajax({
                url: '/notes/update',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(note)
            });
        },

        removeNote: function (noteId) {
            return $.ajax({
                url: '/notes/remove',
                method: 'POST',
                data: {noteId: noteId}
            });
        }
    };

    /* ------------------------ UI ------------------------ */
    var $searcher = $('#searcher');
    var $notesContainer = $('#notes-container');

    $searcher.searcher().on('search', function (e, data) {
        console.log('Search by "' + data.value + '"');
        refreshNotes(data.value);
    });

    $(document.body).on('click', NOTE_CLOSER, function (event) {
        var $noteBlock = $(event.target).closest(NOTE_BLOCK);
        var noteId = $noteBlock.attr(NOTE_ID_ATTR);

        NotesApi.removeNote(noteId)
            .done($noteBlock.remove.bind($noteBlock))
            .fail(function (jqXHR) {
                Utils.errorMessage("Unable to delete note, id = " + noteId);
            });
    });

    function refreshNotes(searchLine) {
        NotesApi.loadNotes(searchLine)
            .done(function (notes) {
                console.log('Loaded notes', notes);

                renderNotes(notes);
            })
            .fail(function (jqXHR) {
                Utils.errorMessage('Unable to load notes');
            });
    }

    function renderNotes(notes) {
        $notesContainer.empty();

        $.each(notes, function (_, note) {
            $notesContainer.append(renderNote(note));
        });
    }

    function renderNote(note) {
        return $(
            '<div class="jsNote" style="width: 500px; background-color: ' + note.color + '; padding: 3px" data-note-id="' + note.noteId + '">' +
            '   <div class="jsNoteClose" style="float: right; width: 10px; cursor: pointer">X</div>' +
            '   <div style="margin-right: 15px;">' +
            '       <p style="text-align: center">' + note.title + '</p>' +
            '       <p>' + note.description + '</p>' +
            '   </div>' +
            '</div>'
        );
    }

    /* ------------------ Entry point -------------------- */
    refreshNotes();
});