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
            <span class="logo-mini"><b>W</b>O</span>
            <span class="logo-lg"><b>Web</b>Organizer</span>
        </a>
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle">
                            <span class="hidden-xs">${userName}</span>
                        </a>
                    </li>
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
                                </div>
                                <div class="form-group">
                                    <label for="note-title" class="control-label">Title:</label>
                                    <input type="text" class="form-control" id="note-title">
                                </div>
                                <div class="form-group">
                                    <label for="note-description" class="control-label">Description:</label>
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
</body>
</html>