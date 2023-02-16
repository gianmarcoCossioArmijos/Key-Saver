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
			<h2>Editar Clave &#128760</h2>
		</div>
		<br>
		<br>
		<div class="campos_editar">
			<form name="formulario_registrar" action="ControladorClave" method="post">
			<input type="hidden" name="instruccion" value="editarClave">
			<input type="hidden" name="cId" value="${claveBuscada.cId}">
			
			<label for="uDni">DNI Usuario:</label><br>
			<input class="input_editar" type="number" id="uDni" name="uDni" value="${claveBuscada.uDni}" required="required"><br><br>
			
			<label for="cClave">Clave:</label><br>
			<input class="input_editar" type="text" id="cClave" name="cClave" value="${claveBuscada.cClave}" required="required"><br><br>

			<label for="cCuenta">Usuario/Cuenta:</label><br>
			<input class="input_editar" type="text" id="cCuenta" name="cCuenta" value="${claveBuscada.cCuenta}" required="required"><br><br>
			
			<label for="cPagina">Pagina/Web:</label><br>
			<input class="input_editar" type="text" id="cPagina" name="cPagina" value="${claveBuscada.cPagina}" required="required"><br><br>
			
			<label for="cTelefono">Telefono:</label><br>
			<input class="input_editar" type="text" id="cTelefono" name="cTelefono" value="${claveBuscada.cTelefono}" required="required"><br><br>
			
			<label for="cEstado">Estado:</label><br>
			<select name="cEstado" id="cEstado" class="input_editar">
                <option value="activo">ACTIVO</option>
                <option value="inactivo">INACTIVO</option>
            </select><br><br><br>
				
			<input class="boton_editar" type="submit" name="editar" id="editar" value="Editar"><br><br>
			<a class="link_inicio" href="inicio.jsp" target="blank">Ir a Inicio</a>

			</form>
		</div>
</div>

</body>
</html>