<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

.contenedor_registrar {
	width: 500px;
	height: 550px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
}

.titulo_registrar {
	margin-top: 30px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
}

.campos_registrar {
	margin-left: 50px;
	margin-right: 50px;
}

.boton_registrar {
	width: 400px;
	height: 30px;
	color: #ffffff;
	background-color: #720587;
	border-radius: 8px;
}

.input_registrar {
	width: 400px;
}

</style>
</head>
<body>

<div class="contenedor_registrar">

		<div class="titulo_registrar">
			<h2>Registrate! &#128526</h2>
		</div>
		<br>
		<br>
		<br>
		<div class="campos_registrar">
			<form name="formulario_registrar" action="ControladorUsuario" method="post">
			<input type="hidden" name="instruccion" value="registrarUsuario">
			
			<label for="uDni">DNI:</label><br>
			<input class="input_registrar" type="number" id="uDni" name="uDni" required="required"><br><br><br>

			<label for="uNombre">Nombre:</label><br>
			<input class="input_registrar" type="text" id="uNombre" name="uNombre" required="required"><br><br><br>
			
			<label for="uApellido">Apellido:</label><br>
			<input class="input_registrar" type="text" id="uApellido" name="uApellido" required="required"><br><br><br>
			
			<label for="uClave">Clave:</label><br>
			<input class="input_registrar" type="text" id="uClave" name="uClave" required="required"><br><br><br><br>
				
			<input class="boton_registrar" type="submit" name="registrar" id="registrar" value="Registrar"><br><br>
			<a class="link_inicio" href="index.jsp" target="blank">Ir a Login</a>

			</form>
		</div>
</div>

</body>
</html>