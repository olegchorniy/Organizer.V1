<#import "color_picker.ftl" as colorPicker>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>WebOrganizer | Notes</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- fullCalendar 2.2.5-->
    <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.print.css" media="print">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>W</b>S</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Web</b>Services</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle">
                            <span class="hidden-xs">Alexander Pierce</span>
                        </a>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="/auth/logout" class="btn btn-default btn-flat"
                           style="background-color: rgba(1, 9, 33, 0.23)">Sign
                            out</a>
                    </li>
                </ul>
            </div>

        </nav>
    </header>

    <aside class="main-sidebar">
        <section class="sidebar" style="height: auto;">

            <ul class="sidebar-menu">
                <li class="active treeview">
                    <a href="/notes">
                        <i class="fa fa-edit"></i> <span>Notes</span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="/events">
                        <i class="fa fa-calendar"></i> <span>Events</span>
                    </a>
                </li>
            </ul>
        </section>

    </aside>

    <div class="content-wrapper">
        <section class="content-header">

        </section>

        <div style="padding: 10px">
            <input style="width: 100%" id="searcher">
        </div>

        <div class="tab-content">
            <div class="tab-pane active" id="timeline">
                <ul class="timeline timeline-inverse" id="notes-container">
                </ul>
            </div>
        </div>

        <section class="content">
            <button id="add-new-note" type="button" class="btn btn-default">Add note</button>

            <div class="modal fade" id="note-popup" tabindex="-1" role="dialog" aria-labelledby="note-popup-label">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="note-popup-label"></h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                <@colorPicker.colorPicker/>
                                </div>
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">Title:</label>
                                    <input type="text" class="form-control" id="note-title">
                                </div>
                                <div class="form-group">
                                    <label for="message-text" class="control-label">Description:</label>
                                    <textarea class="form-control" id="note-description"></textarea>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary jsNoteSave" id="save">Save</button>
                            <button type="button" class="btn btn-default jsPopupClose" data-dismiss="modal" id="close">
                                Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>

<script src="/js/utils.js"></script>
<script src="/js/searcher.js"></script>
<script src="/js/notes.js"></script>

<#--<script language="Javascript">
    $(document).ready(function () {
        $("#note-popup").on('click', '#close', function () {

            $("#note-popup").find("#recipient-name").val("");
            $("#note-popup").find("#message-text").val("");
            $("#note-popupLabel").removeAttr("note_id");
        });
    });
</script>


<script language="Javascript">
    $(document).ready(function () {
        $("#note-popup").on('click', '#save', function () {
            var div1 = document.getElementById("note-popupLabel");
            var node = div1.getAttribute("note_id");
            var title = $("#note-popup").find("#recipient-name").val();
            var body = $("#note-popup").find("#message-text").val();
            if (node) {
                $("[id=" + node + "]").find("#head").empty().append(title);
                $("[id=" + node + "]").find("#body").empty().append(body);
            }
            else {
                var count = $("li:last").attr('id');
                count++;
                $(document).find(".timeline").append("<li id=\"" + count + "\"><i class=\"fa fa-sticky-note-o bg-purple\"></i><div class=\"timeline-item\"><h3 class=\"timeline-header\" id=\"head\">" + title + "</h3><div class=\"timeline-body\" id=\"body\">" + body + "</div><div class=\"timeline-footer\"><a class=\"btn btn-primary btn-xs\" data-toggle=\"modal\" data-target=\"#note-popup\" data-whatever=\"edit\" data-test=\"" + count + "\">Edit</a><a class=\"btn btn-danger btn-xs\" id=\"rem\">Delete</a></div></div></li>");
            }
        });
        $("#note-popup").find("#recipient-name").val("");
        $("#note-popup").find("#message-text").val("");
        $("#note-popupLabel").removeAttr("note_id");

    });
</script>


<script>
    $('#note-popup').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var modal = $(this);
        var recipient = button.data('whatever');
        var node = button.data('test');
        var title = $("[id=" + node + "]").find("#head").text();
        var body = $("[id=" + node + "]").find("#body").text();

        if (recipient === 'new')
            modal.find('.modal-title').text('New note1');
        if (recipient === 'edit') {
            modal.find('.modal-title').text('Edit note');
            modal.find('.modal-body input').val(title);
            modal.find('.modal-body textarea').val(body);
            $("#note-popupLabel").attr("note_id", node);
        }
    })
</script>

<script language="Javascript">
    $(document).ready(function () {
        $(document).on('click', '#rem', function () {
            $(this).closest("li").remove();
        });
    });
</script>-->
</body>
</html>