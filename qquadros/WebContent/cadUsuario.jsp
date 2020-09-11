<%@ page language="java" contentType="text/html; charset=utf-8"
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
		
		if(login == "on"){
			response.sendRedirect("clienteMenu.jsp");
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
                                <li><a class="dropdown-button waves-effect waves-dark" href="login.jsp" data-activates="dropdown2"><i class="fa fa-sign-in fa-fw"></i> <b>Login</b></a></li>
                            </ul>
                        </nav>
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
                                        <a href="#" class="waves-effect waves-dark"><i class="fa fa-user"></i>Perfil<span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="edtUsuario.jsp" class="waves-effect waves-dark"><i class="fa fa-id-badge"></i>Meu Perfil</a>
                                            </li>
                                            <li>
                                                <a href="edtSenha.jsp" class="waves-effect waves-dark"><i class="fa fa-edit"></i>Alterar Senha</a>
                                            </li>
                                            <li>
                                                <a href="" class="waves-effect waves-dark"><i class="fa fa-road"></i>Alterar Endereço</a>
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
                                    Cadastro de Usuário
                                </h1>
                                <ol class="breadcrumb">
                                    <li class="active">Dados de Usuário</li>
                                </ol>

                            </div>

                            <div id="page-inner">
                                <c:forEach var="mensagem" items="${resultado.getMsgs()}">
                                    <c:choose>
                                        <c:when test="${mensagem.getMsgStatus() == 'ERRO'}">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="card-content">
                                                        <div class="card">
                                                            <div class="alert alert-danger">${mensagem.getMsg()}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="card-content">
                                                        <div class="card">
                                                            <div class="alert alert-success">${mensagem.getMsg()}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="card">
                                            <div class="card-action">
                                                <h2>Informações Gerais</h2>
                                            </div>
                                            <div class="card-content">
                                                <form method="POST" action="app" id="usuarioFormulario">
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input type="email" id="txtEmail" name="txtEmail" class="validate">
                                                            <label for="txtEmail">E-mail</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input type="password" id="txtSenha" name="txtSenha" class="validate">
                                                            <label for="txtSenha">Senha</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="input-field col s12">
                                                            <input type="password" id="txtConfirmaSenha" name="txtSenha" class="validate">
                                                            <label for="txtConfirmaSenha">Confirmação de Senha</label>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="file-field col s8">
                                                            <div class="btn btn-primary waves-effect waves-light">
                                                                <input type="file" id="file"> Procurar
                                                            </div>
                                                            <div class="file-path-wrapper">
                                                                <input class="file-path" type="text" placeholder="Escolha uma imagem de perfil">
                                                                <input type="text" class="display-none" id="base64" name="txtFile">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <input type="hidden" name="tarefa" value="cadastrarUsuario" />
                                                    <button id="btnCadUsuario" class="waves-effect waves-light btn" type="submit">Cadastrar</button>
                                                </form>
                                                <div class="clearBoth"></div>
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

                    <script src="assets/js/bootstrap.min.js"></script>

                    <script src="assets/materialize/js/materialize.min.js"></script>

                    <script src="assets/js/jquery.metisMenu.js"></script>

                    <!-- <script src="assets/js/morris/raphael-2.1.0.min.js"></script> -->
                    <script src="assets/js/morris/morris.js"></script>

                    <script src="assets/js/easypiechart.js"></script>
                    <script src="assets/js/easypiechart-data.js"></script>

                    <script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>

                    <!-- <script src="assets/js/custom-scripts.js"></script> -->


            </body>

            </html>