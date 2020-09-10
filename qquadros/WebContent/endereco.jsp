﻿<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Loja</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="assets/materialize/css/materialize.min.css" media="screen,projection" />
    <!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css">
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand waves-effect waves-dark" href="index.jsp"><i class="large material-icons">track_changes</i> <strong>QQuadros</strong></a>

                <div id="sideNav" class="waves-effect waves-dark" href=""><i class="material-icons dp48">toc</i></div>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
                <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>John Doe</b> <i class="material-icons right">arrow_drop_down</i></a></li>
            </ul>
        </nav>
        <!-- Dropdown Structure -->
        <ul id="dropdown1" class="dropdown-content">
            <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
            </li>
            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
            </li>
            <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
            </li>
        </ul>
        <ul id="dropdown2" class="dropdown-content w250">
            <li>
                <a href="#">
                    <div>
                        <i class="fa fa-comment fa-fw"></i> New Comment
                        <span class="pull-right text-muted small">4 min</span>
                    </div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                    <div>
                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                        <span class="pull-right text-muted small">12 min</span>
                    </div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                    <div>
                        <i class="fa fa-envelope fa-fw"></i> Message Sent
                        <span class="pull-right text-muted small">4 min</span>
                    </div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                    <div>
                        <i class="fa fa-tasks fa-fw"></i> New Task
                        <span class="pull-right text-muted small">4 min</span>
                    </div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                    <div>
                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                        <span class="pull-right text-muted small">4 min</span>
                    </div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a class="text-center" href="#">
                    <strong>See All Alerts</strong>
                    <i class="fa fa-angle-right"></i>
                </a>
            </li>
        </ul>
        <!--/. NAV TOP  -->
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a href="menu.jsp" class="waves-effect waves-dark"><i class="fa fa-th-list"></i>Menu Principal</a>
                    </li>
                    <li>
                        <a href="carrinho.jsp" class="waves-effect waves-dark"><i class="fa fa-shopping-cart"></i>Carrinho</a>
                    </li>
                    <li>
                        <a href="pedidos.jsp" class="waves-effect waves-dark"><i class="fa fa-paste"></i>Pedidos</a>
                    </li>
                    <li>
                        <a href="trocas.jsp" class="waves-effect waves-dark"><i class="fa fa-exchange"></i>Trocas</a>
                    </li>
                    <li>
                        <a href="cupons.jsp" class="waves-effect waves-dark"><i class="fa fa-ticket"></i>Cupons</a>
                    </li>
                    <li>
                        <a href="#" class="active-menu waves-effect waves-dark"><i class="fa fa-user"></i>Perfil<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="usuario.jsp" class="waves-effect waves-dark"><i class="fa fa-user-edit"></i>Meu Perfil</a>
                            </li>
                            <li>
                                <a href="edtSenha.jsp" class="waves-effect waves-dark"><i class="fa fa-edit"></i>Alterar Senha</a>
                            </li>
                            <li>
                                <a href="endereco.jsp" class="active-menu waves-effect waves-dark"><i class="fa fa-road"></i>Endereço</a>
                            </li>
                            <li>
                                <a href="cartao.jsp" class="waves-effect waves-dark"><i class="fa fa-credit-card"></i>Cartões</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div class="header">
                <h1 class="page-header">
                    Endereços
                </h1>
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Perfil</a></li>
                    <li class="active">Endereços</li>
                </ol>

            </div>

            <div id="page-inner">
                <div class="col-md-12">
                    <!--   Basic Table  -->
                    <div class="card">
                        <div class="card-action">
                            Endereco de entrega
                        </div>
                        <div class="card-content">
                            <div class="table-responsive">
                                <table class="table  table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Logradouro</th>
                                            <th>Bairro</th>
                                            <th>Cidade</th>
                                            <th>Estado</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Rua Bela</td>
                                            <td>Centro</td>
                                            <td>Guararema</td>
                                            <td>São Paulo</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Rua rua</td>
                                            <td>Bairro bairro</td>
                                            <td>Cidade cidade</td>
                                            <td>Estado estado</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Rua rua</td>
                                            <td>Bairro bairro</td>
                                            <td>Cidade cidade</td>
                                            <td>Estado estado</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>Rua rua</td>
                                            <td>Bairro bairro</td>
                                            <td>Cidade cidade</td>
                                            <td>Estado estado</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>Rua rua</td>
                                            <td>Bairro bairro</td>
                                            <td>Cidade cidade</td>
                                            <td>Estado estado</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <a href="cadEndereco.jsp" class="waves-effect waves-light btn hover"><i class="material-icons left">add</i><b>Adicionar Novo Endereço</b></a>
                            </div>
                        </div>
                    </div>
                    <!-- End  Basic Table  -->
                </div>
            </div>
            <footer>
                <p>All right reserved. Template by: <a href="https://webthemez.com/admin-template/">WebThemez.com</a></p>
            </footer>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="assets/js/jquery-1.10.2.js"></script>

    <!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>

    <script src="assets/materialize/js/materialize.min.js"></script>

    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>


    <script src="assets/js/easypiechart.js"></script>
    <script src="assets/js/easypiechart-data.js"></script>

    <script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>

    <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script>
    <script>
        $(document).ready(function() {
            $('ul.tabs').tabs();
            $('.collapsible').collapsible({
                accordion: false, // A setting that changes the collapsible behavior to expandable instead of the default accordion style
                onOpen: function(el) {
                    alert('Open');
                }, // Callback for Collapsible open
                onClose: function(el) {
                        alert('Closed');
                    } // Callback for Collapsible close
            });
        });
    </script>


</body>

</html>