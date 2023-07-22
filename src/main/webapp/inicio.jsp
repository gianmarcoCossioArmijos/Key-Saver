<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="UTF-8">
<title>Key Saver</title>
<link rel="stylesheet" href="./styles.css">
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<main class="h-screen text-3xl bg-[url('https://images.unsplash.com/photo-1579547621700-03c2c337370a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1878&q=80')] bg-cover h-screen">
	
	<header class="flex flex-row justify-between bg-lime-500 rounded-b-md text-3xl font-bold text-white text-center p-4">
	
			<a href="inicio.jsp">Key Saver &#128273</a>
					
			<ul class="flex flex-row">
				<li><a class="mx-6" href="inicio.jsp">Inicio</a></li>
				<li class="mx-6"><a href="generarClave.jsp">Generar clave</a></li>
				<li class="mx-6"><a class="nav-link" href="editarUsuario.jsp">Configuracion</a></li>
			</ul>
	</header>

	<c:set var="uDniPublico" value="${dni}"></c:set>
	
	<form name="formulario">
	<input type="hidden" name="instruccion">
	
	<div class="grid grid-cols-2 gap-y-12 m-10">
	
		<input type="hidden" name="uDniPublico" id="uDniPublico" value="${uDniPublico}">
	
		<input class="bg-white rounded-md w-4/5 h-36 border-2 border-stone-200 m-auto hover:bg-stone-200" type="button" name="lista" id="ingresar" value="Mis Claves &#127774" onclick="formulario.action='ControladorClave'; instruccion.value='reportarClave';formulario.method='post';formulario.submit()"/>
		<input class="bg-white rounded-md w-4/5 h-36 border-2 border-stone-200 m-auto hover:bg-stone-200" type="button" name="registrarClave" id="registrarClave" value="Registrar Clave &#128125" onclick="window.location.href='registrarClave.jsp'"/>
		<input class="bg-white rounded-md w-4/5 h-36 border-2 border-stone-200 m-auto hover:bg-stone-200" type="button" name="generarClave" id="generarClave" value="Generar Clave &#127752" onclick="window.location.href='generarClave.jsp'"/>
		<input class="bg-white rounded-md w-4/5 h-36 border-2 border-stone-200 m-auto hover:bg-stone-200" type="button" name="editarUsuario" id="editarUsuario" value="Editar Usuario &#128541" onclick="formulario.action='ControladorUsuario'; instruccion.value='buscarUsuario';formulario.method='post';formulario.submit()"/>
		<input class="bg-white rounded-md w-4/5 h-36 border-2 border-stone-200 m-auto hover:bg-stone-200" type="button" name="salir" id="salir" value="Cerrar Sesion &#128128" onclick="formulario.action='ControladorUsuario'; instruccion.value='formatearClave';formulario.method='get';formulario.submit()"/>
	
	</div>
	
	</form>
</main>

</body>
</html>