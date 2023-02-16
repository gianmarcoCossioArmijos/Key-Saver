<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Key Saver</title>
<style type="text/css">

* {
	font-family: monospace;
}

body {
    background-image: url("https://png.pngtree.com/background/20210717/original/pngtree-background-retro-computer-game-end-fault-picture-image_1428217.jpg");
}

.contenedor_inicio_sesion {
	width: 400px;
	height: 450px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
}

.titulo_sesion {
	margin-top: 30px;
	margin-left: 65px;
	margin-right: 65px;
	text-align: center;
}

.campos_sesion {
	display: inline-block;
	margin-left: 65px;
	margin-right: 65px;
}

.boton_sesion {
	width: 270px;
	height: 30px;
	color: #ffffff;
	background-color: #720587;
	border-radius: 8px;
}

.input_sesion {
	width: 270px;
}

.emoji {
	text-align: center;
	font-size: 40px
}

</style>
</head>

<body>

	<div class="contenedor_inicio_sesion">

		<div class="titulo_sesion">
			<h2>Iniciar Sesion</h2>
		</div>
		<br>
		<br>
		<div class="campos_sesion">
			<form name="formulario_inicio_sesion" action="ControladorUsuario" method="get">
			<input type="hidden" name="instruccion" value="iniciarSesion">

			<label for="usuario">Usuario:</label><br>
			<input class="input_sesion" type="number" id="usuario" name="usuario" required="required"><br><br><br>
			
			<label for="clave">Clave:</label><br>
			<input class="input_sesion" type="password" id="clave" name="clave" required="required"><br><br><br><br>
				
			<input class="boton_sesion" type="submit" name="ingresar" id="ingresar" value="Iniciar Sesion"><br><br>
			<a href="registrarUsuario.jsp" target="blank">¿No estas registrado? Registrate</a>
			
			<h1 class="emoji">&#127919</h1>

			</form>
		</div>
	</div>

</body>
</html>