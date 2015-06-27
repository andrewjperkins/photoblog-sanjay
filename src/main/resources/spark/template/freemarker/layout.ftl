<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Brown Face in Brown Place</title>

    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/css/simple-sidebar.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/admin/home">
                        Sanjay Pathmanathan
                    </a>
                </li>
                <li>
                    <img src="/images/sanjay_profile_pic.jpg">
                </li>
                <div class="copyright">
                    <li><b>COPYRIGHT NOTICE:</b></li>
                    <li id="copyright-notice">
                        All photographs, images and work on this website are the property of Sanjay Pathmanathan. Under No Circumstances shall these images and work be copied, reproduced or pulled from this website without the expressed permission of Sanjay Pathmanathan.
                        <br>
                        <br>
                        Please contact me if you would like to ask:
                        <br>
                        Sanjay14_path@hotmail.com
                    </li>
                    <li><a href="/admin/logout">Log Out</a></li>
                </div>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <!--
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                        -->
                        <div id="article-content-wrapper">   
                            <h1><b><i>Brown Face in Brown Place</i></b></h1>
                            <h3><i>Pictures of Sri Lanka</i></h3>
                            <hr>
                            <br>

                            <a href="/admin/article/create">Add a new article</a>

                            <#include "${templateName}">
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>