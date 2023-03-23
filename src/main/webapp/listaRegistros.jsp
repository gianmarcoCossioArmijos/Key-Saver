<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Key Saver</title>
</head>
<style type="text/css">

* {
	font-family: monospace;
}

body {
    background-image: url("https://png.pngtree.com/background/20210717/original/pngtree-background-retro-computer-game-end-fault-picture-image_1428217.jpg");
}

table {
   width: 800px;
   border: 1px solid #000;
   background-color: white;
   border-collapse: collapse;
}

th, td {
   width: 25%;
   text-align: left;
   vertical-align: top;
   border: 1px solid #000;
   padding: 0.3em;
   caption-side: bottom;
}

caption {
   padding: 0.3em;
   color: white;
   background-color: #720587;
}

.titulo {
	margin-top: 30px;
	text-align: center;
}

.contenedor_lista_claves {
	width: 1000px;
	background-color: white;
	color: #000000;
	margin: auto;
	border: solid grey 1.5px;
	border-radius: 15px;
}

.tabla {
	margin: auto;
}

.contenedor_tabla_claves {
	margin: auto;
}

.contenedor_registrar_clave {
	padding-left: 70px;
	padding-right: 70px;
	display: grid;
  	grid-template-columns: 780px auto;
}

.boton {
	width: 300px;
	height: 30px;
	color: #ffffff;
	background-color: #720587;
	border-radius: 8px;
}

</style>
<body>

<div class="contenedor_lista_claves">
	
	<div class="titulo">
		<h2>Mis Claves &#127774</h2>
	</div>
	<br>
	<br>
	<br>
	<div class="contenedor_tabla_claves">
	
	<form name="formulario">

		<table border="1" class="tabla">
			<tr>
				<td class="header">ID</td>
				<td class="header">DNI Usuario</td>
				<td class="header">Clave</td>
				<td class="header">Usuario/cuenta</td>
				<td class="header">Pagina/web</td>
				<td class="header">Telefono</td>
				<td class="header">Estado</td>
				<td class="header">Accion</td>
			</tr>
	
	<c:forEach var="temporal" items="${listaClave}">
	
	<c:url var="linkBuscar" value="ControladorClave">
	
		<c:param name="instruccion" value="buscarClave"></c:param>
		<c:param name="cId" value="${temporal.cId}"></c:param>
	
	</c:url>
	
	<c:url var="linkDesactivar" value="ControladorClave">
	
		<c:param name="instruccion" value="desactivarClave"></c:param>
		<c:param name="cId" value="${temporal.cId}"></c:param>
	
	</c:url>
	
	<c:url var="linkEliminar" value="ControladorClave">
	
		<c:param name="instruccion" value="eliminarClave"></c:param>
		<c:param name="cId" value="${temporal.cId}"></c:param>
	
	</c:url>
		<tr>
			<td class="row">${temporal.cId}</td>
			<td class="row">${temporal.uDni}</td>
			<td class="row">${temporal.cClave}</td>
			<td class="row">${temporal.cCuenta}</td>
			<td class="row">${temporal.cPagina}</td>
			<td class="row">${temporal.cTelefono}</td>
			<td class="row">${temporal.cEstado}</td>
			<td class="row">
				<a href="${linkBuscar}" target="blank">editar</a>
				&nbsp;&nbsp;
				<a href="${linkDesactivar}" target="self">desactivar</a>
				&nbsp;&nbsp;
				<a href="${linkEliminar}" target="self">eliminar</a>
			</td>
		</tr>
			
	</c:forEach>

	</table>
	
	</form>

	</div>
	<br>
	<br>
	<br>
	<div class="contenedor_registrar_clave">
	
		<input type="button" value="Registrar Clave" class="boton" onclick="window.location.href='registrarClave.jsp'"/>
		<a class="link_inicio" href="inicio.jsp" target="blank">Ir a Inicio</a>
		
	</div>
	<br>
	<br>
</div>

</body>
</html>