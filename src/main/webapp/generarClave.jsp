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

.contenedor_generar {
	width: 500px;
	height: 550px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
}

.titulo_generar {
	margin-top: 50px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
}

.titulo_dificultad {
	display: inline-block;
	margin-bottom: 20px;
	margin-left: 80px;
	margin-right: 90px;
	font-size: 16px;
}

dificultad {
	display: inline-block;
	margin: auto;
	font-size: 17px;
}

.generar {
	display: inline-block;
	margin-left: 50px;
	margin-right: 50px;
}

.clave {
	width: 400px;
	height: 150px;
	text-align: center;
}

.boton {
	width: 400px;
	height: 30px;
	color: #ffffff;
	background-color: #720587;
	border-radius: 8px;
}

.input {
	width: 400px;
}

</style>
</head>

<body>

	<c:set var="claveGenerada" value="${claveGenerada}"></c:set>

	<div class="contenedor_generar">

		<div class="titulo_generar">
			<h2>Generar Clave Segura &#127752</h2>
		</div>
		
		<br>
		<br>
		<br>
		<br>
		
		<div class="generar">
		
		<form name="formulario_generar" id="formulario_generar" action="ControladorClave" method="get">
		<input type="hidden" name="instruccion" id="instruccion">
			
		<c:choose>	
			<c:when test="${claveGenerada == null}">
				
				<h1 class="clave"></h1>
				
				<label class="titulo_dificultad" for="dificultad">Longitud:</label>
				<input class="dificultad" type="number" id="dificultad" name="dificultad" min="10" max="15" value="12">
				
				<input class="boton" type="button" name="generar" id="generar" value="Generar Clave" onclick="instruccion.value='generarClave';formulario_generar.submit()"><br><br><br>
	
			</c:when>
			
			<c:otherwise>
				
				<input type="hidden" name="claveGenerada" id="claveGenerada" value="${claveGenerada}">
	
				<h1 class="clave"> <c:out value="${claveGenerada}"></c:out>	</h1>
				
				<label class="titulo_dificultad" for="dificultad">Longitud:</label>
				<input class="dificultad" type="number" id="dificultad" name="dificultad" min="10" max="15" value="12">	
				
				<input class="boton" type="button" name="generar" id="generar" value="Generar Clave" onclick="instruccion.value='generarClave';formulario_generar.submit()"><br><br>
				<input class="boton" type="button" name="guardar" id="guardar" value="Guardar Clave" onclick="instruccion.value='guardarClave';formulario_generar.submit()"><br><br>
					
			</c:otherwise>
		</c:choose>
		
		<a href="inicio.jsp" target="blank">Ir a Inicio</a>
		
		</form>

		</div>
	</div>
</body>
</html>