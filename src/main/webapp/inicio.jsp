<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

.contenedor_botones_inicio {
	width: 750px;
	height: 550px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
	display: grid;
	grid-template-columns: 200px 200px 200px;
	justify-content: space-around;
}

.boton {
	margin: auto;
	border-radius: 15px;
	width: 200px;
	height: 200px;
	color: #ffffff;
	background-color: #720587;
	font-size: 16px;
	font-weight: bold;
}

</style>
</head>
<body>

	<c:set var="uDniPublico" value="${dni}"></c:set>
	
	<form name="formulario">
	<input type="hidden" name="instruccion">
	
	<div class="contenedor_botones_inicio">
	
	<input type="hidden" name="uDniPublico" id="uDniPublico" value="${uDniPublico}">
	
		<input class="boton" type="button" name="lista" id="ingresar" value="Mis Claves &#127774" onclick="formulario.action='ControladorClave'; instruccion.value='reportarClave';formulario.method='post';formulario.submit()"/>
		<input class="boton" type="button" name="registrarClave" id="registrarClave" value="Registrar Clave &#128125" onclick="window.location.href='registrarClave.jsp'"/>
		<input class="boton" type="button" name="generarClave" id="generarClave" value="Generar Clave &#127752" onclick="window.location.href='generarClave.jsp'"/>
		<input class="boton" type="button" name="editarUsuario" id="editarUsuario" value="Editar Usuario &#128541" onclick="formulario.action='ControladorUsuario'; instruccion.value='buscarUsuario';formulario.method='post';formulario.submit()"/>
		<input class="boton" type="button" name="salir" id="salir" value="Cerrar Sesion &#128128" onclick="formulario.action='ControladorUsuario'; instruccion.value='formatearClave';formulario.method='get';formulario.submit()"/>
	
	</div>
	
	</form>
	
</body>
</html>