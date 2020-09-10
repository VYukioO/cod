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
                <li><a class="dropdown-button waves-effect waves-dark" href="login.jsp" data-activates="dropdown2"><i class="fa fa-sign-in fa-fw"></i> <b>Login</a></li>
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
                                <a href="endereco.jsp" class="waves-effect waves-dark"><i class="fa fa-road"></i>Alterar Endereço</a>
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
                    <li><a href="cadUsuario.jsp">Informações Gerais</a></li>
                    <li class="active">Endereço</li>
                </ol>

            </div>


            <div id="page-inner">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-action">
                                <h2>Endereço</h2>
                            </div>
                            <div class="card-content">
                                <form class="col s12">
                                    <div class="row">
                                        <div class="input-field col s5">
                                            <input id="cep" type="text" class="validate">
                                            <label for="cep">CEP</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s9">
                                            <input id="descricao" type="text" class="validate">
                                            <label for="descricao">Descrição</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s8">
                                            <input id="logradouro" type="text" class="validate">
                                            <label for="logradouro">Logradouro</label>
                                        </div>
                                        <div class="input-field col s3">
                                            <input id="numero" type="text" class="validate">
                                            <label for="numero">Número</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s11">
                                            <input id="complemento" type="text" class="validate">
                                            <label for="complemento">Complemento</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s3">
                                            <input id="bairro" type="text" class="validate">
                                            <label for="bairro">Bairro</label>
                                        </div>
                                        <div class="input-field col s8">
                                            <input id="referencia" type="text" class="validate">
                                            <label for="referencia">Referência</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s6">
                                            <input id="cidade" type="text" class="validate">
                                            <label for="cidade">Cidade</label>
                                        </div>
                                        <div class="input-field col s5">
                                            <input id="estado" type="text" class="validate">
                                            <label for="estado">Estado</label>
                                        </div>
                                    </div>
                                    <button id="salvar" class="waves-effect waves-light btn" type="submit">Cadastrar</button>
                                </form>
                                </br>
                                <div class="clearBoth"></div>
                            </div>
                        </div>
                    </div>
                </div>
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


</body>

</html>