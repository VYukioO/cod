﻿<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
        <%@page import="br.edu.fatec.les.facade.Resultado"%>
            <!DOCTYPE html>
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
                <%
		String login = "";
		login = (String) session.getAttribute("status");
		
		if(login == "off" || login == null){
			response.sendRedirect("login.jsp");
		}
	
	%>
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
                                                <a href="usuario.jsp" class="active-menu waves-effect waves-dark"><i class="fa fa-user-edit"></i>Meu Perfil</a>
                                            </li>
                                            <li>
                                                <a href="edtSenha.jsp" class="waves-effect waves-dark"><i class="fa fa-edit"></i>Alterar Senha</a>
                                            </li>
                                            <li>
                                                <a href="endereco.jsp" class="waves-effect waves-dark"><i class="fa fa-road"></i>Endereço</a>
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
                                    Perfil
                                </h1>
                                <ol class="breadcrumb">
                                    <li class="active">Perfil</li>
                                </ol>

                            </div>

                            <div id="page-inner">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="card">
                                            <div class="card-action">
                                                <h2>Meu Perfil</h2>
                                            </div>
                                            <div class="card-content text-center">
                                                <div class="row">
                                                    <!-- <div class="card-image waves-effect waves-block waves-light col-md-4">
                                        <img class="activator" src="http://materializecss.com/images/office.jpg">
                                    </div> -->
                                                    <h3>Nome do Usuario</h3>
                                                    </br>
                                                    <p>email@email.com</p>
                                                    <p>cpf.cpf.cpf-xx</p>
                                                </div>
                                            </div>
                                            <p>usua ${usuario}</p>
                                            <c:if test="${usuario != null}">
                                                <form method="POST" action="app">
                                                    <input type="hiden" name="txtUsuarioId" value="${usuario.getId()}">
                                                    <input type="text" name="txtEmail" value="${usuario.getEmail()}">
                                                    <!-- <input type="text" name="txtUsuarioId" value="${usuario.getImage().getFoto}"> -->
                                                    <input type="hidden" name="tarefa" value="editarUsuario">

                                                    <button type="submit" id="btnEditarUsuario" class="">Editar Usuario</button>
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="card">
                                            <div class="card-action">
                                                <h3>Endereços</h3>
                                            </div>
                                            <div class="card-content">
                                                <ul class="collapsible" data-collapsible="accordion">
                                                    <li>
                                                        <div class="collapsible-header"><i class="material-icons right"></i>Descrição Endereço 1</div>
                                                        <div class="collapsible-body">
                                                            <p>
                                                                Rua rua rua, 333 - Centro - Guararema - SP</br>
                                                                08900-000</br>
                                                                perto do supermercado
                                                            </p>
                                                            <a class="btn waves-effect waves-light btn-primary hover" href="edtEndereco.jsp">Editar</a>
                                                            <a class="btn waves-effect waves-light btn-danger hover" href="#">Excluir</a>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="collapsible-header"><i class="material-icons"></i>Descrição Endereço 2</div>
                                                        <div class="collapsible-body">
                                                            <p>
                                                                Rua rua rua 2, 333 - Centro - Mogi das Cruzes - SP</br>
                                                                08900-000</br>
                                                                perto de uma escola
                                                            </p>
                                                            <a class="btn waves-effect waves-light btn-primary hover" href="edtEndereco.jsp">Editar</a>
                                                            <a class="btn waves-effect waves-light btn-danger hover" href="#">Excluir</a>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="collapsible-header"><i class="material-icons"></i>Descrição Endereço 3</div>
                                                        <div class="collapsible-body">
                                                            <p>
                                                                Rua rua rua 3, 4622 - Centro - Guararema - SP</br>
                                                                08900-000</br>
                                                                Ao lado do posto de combustivel
                                                            </p>
                                                            <a class="btn waves-effect waves-light btn-primary hover" href="edtEndereco.jsp">Editar</a>
                                                            <a class="btn waves-effect waves-light btn-danger hover" href="#">Excluir</a>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="card">
                                            <div class="card-action">
                                                <h3>Cartões</h3>
                                            </div>
                                            <div class="card-content">
                                                <ul class="collapsible" data-collapsible="accordion">
                                                    <li>
                                                        <div class="collapsible-header"><i class="material-icons right"></i>
                                                            <table>
                                                                <th>Bandeira</th>
                                                                <th>Final:XXXX</th>
                                                            </table>
                                                        </div>
                                                        <div class="collapsible-body">
                                                            <p>
                                                                Bandeira Final: XXXX
                                                                </br>
                                                                Nome no cartao
                                                            </p>
                                                            <a class="btn waves-effect waves-light btn-primary hover" href="edtEndereco.jsp">Editar</a>
                                                            <a class="btn waves-effect waves-light btn-danger hover" href="#">Excluir</a>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="collapsible-header"><i class="material-icons right"></i>
                                                            <table>
                                                                <th>Bandeira</th>
                                                                <th>Final:XXXX</th>
                                                            </table>
                                                        </div>
                                                        <div class="collapsible-body">
                                                            <p>
                                                                Bandeira Final: XXXX
                                                                </br>
                                                                Nome no cartao
                                                            </p>
                                                            <a class="btn waves-effect waves-light btn-primary hover" href="#">Editar</a>
                                                            <a class="btn waves-effect waves-light btn-danger hover" href="#">Excluir</a>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>

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