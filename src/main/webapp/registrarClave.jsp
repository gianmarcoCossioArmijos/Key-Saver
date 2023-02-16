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

<c:set var="claveGenerada" value="${parametro}"></c:set>

<div class="contenedor_registrar">

		<div class="titulo_registrar">
			<h2>Registrar Clave &#128125</h2>
		</div>
		<br>
		<br>
		<div class="campos_registrar">
			<form name="formulario_registrar" action="ControladorClave" method="post">
			<input type="hidden" name="instruccion" value="registrarClave">
			
			<label for="uDni">DNI Usuario:</label><br>
			<input class="input_registrar" type="number" id="uDni" name="uDni" required="required"><br><br>
			
			<label for="cClave">Clave:</label><br>
			<c:choose>	
				<c:when test="${claveGenerada == ''}">
					<input class="input_registrar" type="text" id="cClave" name="cClave" required="required"><br><br>
				</c:when>
			
				<c:otherwise>
					<input class="input_registrar" type="text" id="cClave" name="cClave" required="required" value="${claveGenerada}"><br><br>	
				</c:otherwise>
			</c:choose>

			<label for="cCuenta">Usuario/Cuenta:</label><br>
			<input class="input_registrar" type="text" id="cCuenta" name="cCuenta" required="required"><br><br>
			
			<label for="cPagina">Pagina/Web:</label><br>
			<input class="input_registrar" type="text" id="cPagina" name="cPagina" required="required"><br><br>
			
			<label for="cTelefono">Telefono:</label><br>
			<input class="input_registrar" type="text" id="cTelefono" name="cTelefono" required="required"><br><br>
			
			<label for="cEstado">Estado:</label><br>
			<select name="cEstado" id="cEstado" class="input_registrar">
                <option value="activo">ACTIVO</option>
                <option value="inactivo">INACTIVO</option>
            </select><br><br><br>
				
			<input class="boton_registrar" type="submit" name="registrar" id="registrar" value="Registrar"><br><br>
			<a class="link_inicio" href="inicio.jsp" target="blank">Ir a Inicio</a>

			</form>
		</div>
</div>

</body>
</html>