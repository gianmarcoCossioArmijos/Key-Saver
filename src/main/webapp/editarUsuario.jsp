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

.contenedor_editar {
	width: 500px;
	height: 550px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
}

.titulo_editar {
	margin-top: 30px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
}

.campos_editar {
	margin-left: 50px;
	margin-right: 50px;
}

.boton_editar {
	width: 400px;
	height: 30px;
	color: #ffffff;
	background-color: #720587;
	border-radius: 8px;
}

.input_editar {
	width: 400px;
}

</style>
</head>
<body>

<div class="contenedor_editar">

		<div class="titulo_editar">
			<h2>Editar Usuario &#128541</h2>
		</div>
		<br>
		<br>
		<br>
		<br>
		<div class="campos_editar">
			<form name="formulario_registrar" action="ControladorUsuario" method="post">
			<input type="hidden" name="instruccion" value="editarUsuario">
			<input type="hidden" name="dni" value="${usuario.uDni}">

			<label for="nombre">Nombre:</label><br>
			<input class="input_editar" type="text" id="nombre" name="nombre" required="required" value="${usuario.uNombre}"><br><br><br><br>
			
			<label for="apellido">Apellido:</label><br>
			<input class="input_editar" type="text" id="apellido" name="apellido" required="required" value="${usuario.uApellido}"><br><br><br><br>
			
			<label for="clave">Clave:</label><br>
			<input class="input_editar" type="text" id="clave" name="clave" required="required" value="${usuario.uClave}"><br><br><br><br><br>
				
			<input class="boton_editar" type="submit" name="editar" id="editar" value="Editar"><br><br>
			<a class="link_inicio" href="inicio.jsp" target="blank">Ir a Inicio</a>

			</form>
		</div>
</div>

</body>
</html>