$(function () {

    var NotesApi = {
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

    /* --------------------- Constants ---------------------*/

    const NOTE_TITLE = '.jsNoteTitle';
    const NOTE_DESCRIPTION = '.jsNoteDescription';

    const NOTE_SAVE_BUTTON = '.jsNoteSave';
    const NOTE_EDIT_BUTTON = '.jsNoteEdit';
    const NOTE_DELETE_BUTTON = '.jsNoteDelete';

    const NOTE_BLOCK = '.jsNote';
    const POPUP_CLOSE_BUTTON = '.jsPopupClose';

    const POPUP_CONTEXT = 'data.popup.context';
    const NOTE_ID_ATTR = 'data-note-id';

    const POPUP_MODES = {
        EDIT: 'edit',
        NEW: 'new'
    };

    /* ------------------------ UI ------------------------ */

    var $body = $(document.body);

    var $searcher = $('#searcher');
    var $notesContainer = $('#notes-container');
    var $addNoteButton = $('#add-new-note');

    var $closePopupButton = $(POPUP_CLOSE_BUTTON);
    var $saveNoteButton = $(NOTE_SAVE_BUTTON);

    var $notePopup = $('#note-popup');
    var $popupNoteLabel = $('#note-popup-label');
    var $popupNoteTitle = $('#note-title');
    var $popupNoteDescription = $('#note-description');

    /* --------------------- Listeners ---------------------*/

    $searcher.searcher().on('search', function (e, data) {
        console.log('Search by "' + data.value + '"');
        reloadNotes();
    });

    $body.on('click', NOTE_DELETE_BUTTON, function (event) {
        var $noteBlock = $(event.target).closest(NOTE_BLOCK);
        var noteId = $noteBlock.attr(NOTE_ID_ATTR);

        NotesApi.removeNote(noteId)
            .done($noteBlock.remove.bind($noteBlock))
            .fail(function (jqXHR) {
                Utils.errorMessage("Unable to delete note, id = " + noteId);
            });
    });

    $body.on('click', NOTE_EDIT_BUTTON, function (event) {
        var $noteBlock = $(event.target).closest(NOTE_BLOCK);

        var noteId = $noteBlock.attr(NOTE_ID_ATTR);
        var title = $noteBlock.find(NOTE_TITLE).text();
        var description = $noteBlock.find(NOTE_DESCRIPTION).text();

        openPopup(POPUP_MODES.EDIT, {
            noteId: noteId,
            title: title,
            description: description
        });
    });

    $addNoteButton.on('click', function () {
        openPopup(POPUP_MODES.NEW);
    });

    $saveNoteButton.on('click', function () {
        var title = $popupNoteTitle.val();
        var description = $popupNoteDescription.val();

        var note = {
            title: title,
            description: description,
            color: 'rgb(0, 166, 90)'
        };

        var context = $notePopup.data(POPUP_CONTEXT);

        if (context.mode === POPUP_MODES.EDIT) {
            note.noteId = context.noteId;
            updateNode(note);
        } else {
            createNote(note);
        }
    });

    $closePopupButton.on('click', $notePopup.modal.bind($notePopup, 'hide'));

    /* --------------------- Note manipulation functions  ---------------------*/

    function updateNode(note) {
        NotesApi.updateNote(note)
            .done(function () {
                console.log('Note updated');

                $notePopup.modal('hide');
                reloadNotes();
            })
            .fail(function (jqXHR) {
                console.log('Cannot create event', jqXHR);
                Utils.errorMessage('Unable to update note' + note.title);
            });
    }

    function createNote(note) {
        NotesApi.createNote(note)
            .done(function (noteResponse) {
                console.log('Note created', noteResponse);

                $notePopup.modal('hide');
                reloadNotes();
            })
            .fail(function (jqXHR) {
                console.log('Cannot create note', jqXHR);
                Utils.errorMessage('Unable to create note ' + note.title);
            });
    }

    function reloadNotes() {
        var searchLine = $searcher.searcher('currentValue');

        NotesApi.loadNotes(searchLine)
            .done(function (notes) {
                console.log('Loaded notes', notes);

                renderNotes(notes);
            })
            .fail(function (jqXHR) {
                Utils.errorMessage('Unable to load notes');
            });
    }

    /* --------------------- Rendering ---------------------*/

    function renderNotes(notes) {
        $notesContainer.empty();

        $.each(notes, function (_, note) {
            $notesContainer.append(renderNote(note));
        });
    }

    function renderNote(note) {
        return $(
            '<li class="jsNote" data-note-id="' + note.noteId + '">' +
            '    <i class="fa fa-sticky-note-o" style="background-color: ' + note.color + ' !important;"></i>' +
            '    <div class="timeline-item">' +
            '        <h3 class="timeline-header jsNoteTitle" id="head">' + note.title + '</h3>' +
            '        <div class="timeline-body jsNoteDescription" id="body">' + note.description + '</div>' +
            '        <div class="timeline-footer">' +
            '            <a class="btn btn-primary btn-xs jsNoteEdit">Edit</a>' +
            '            <a class="btn btn-danger btn-xs jsNoteDelete">Delete</a>' +
            '        </div>' +
            '    </div>' +
            '</li>'
        );
    }

    function openPopup(mode, note) {
        $notePopup.data(POPUP_CONTEXT, {
            mode: mode,
            noteId: note ? note.noteId : null
        });

        if (mode === POPUP_MODES.EDIT) {
            $popupNoteLabel.text('Edit note');
            $popupNoteTitle.val(note.title);
            $popupNoteDescription.val(note.description);
        } else {
            $popupNoteLabel.text('Add note');
            $popupNoteTitle.val('');
            $popupNoteDescription.val('');
        }

        $notePopup.modal('show');
    }

    /* ------------------ Entry point -------------------- */
    reloadNotes();
});