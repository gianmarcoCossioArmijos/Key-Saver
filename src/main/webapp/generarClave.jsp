<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Key Saver</title>
<link rel="stylesheet" href="./styles.css">
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body>

	<main class="h-screen w-screen text-3xl bg-[url('https://images.unsplash.com/photo-1579547621700-03c2c337370a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1878&q=80')] bg-cover h-screen">
	
		<header class="mb-5 p-4 flex flex-row justify-between bg-lime-500 rounded-b-md text-3xl font-bold text-white text-center">
		
				<a href="inicio.jsp">Key Saver &#128273</a>
						
				<ul class="flex flex-row">
					<li><a class="mx-6" href="inicio.jsp">Inicio</a></li>
					<li class="mx-6"><a href="generarClave.jsp">Generar clave</a></li>
					<li class="mx-6"><a class="nav-link" href="editarUsuario.jsp">Configuracion</a></li>
				</ul>
		</header>

		<div class="w-2/4 m-auto flex flex-col justify-center border-2 border-stone-200 rounded-lg bg-white">

			<c:set var="claveGenerada" value="${claveGenerada}"></c:set>

			<div class="h-1/5 flex flex-col justify-center content-center bg-lime-500 rounded-t-md text-xl text-center p-4">
				<h5 class="font-bold text-3xl text-white">Generar Clave Segura &#127752</h5>
			</div>
		
			<div class="w-full p-3">
		
				<form class="w-full flex flex-col" name="formulario_generar" id="formulario_generar" action="ControladorClave" method="get">
					
					<input type="hidden" name="instruccion" id="instruccion">
			
					<c:choose>	
						<c:when test="${claveGenerada == null}">
				
							<div class="w-full flex items-center h-48 my-1 text-3xl text-center">
								<h1 class="m-auto"></h1>
							</div>
				
							<div class="w-full flex items-center my-1">
								<label class="w-1/4 h-12" for="dificultad">Longitud:</label>
								<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="number" id="dificultad" name="dificultad" min="10" max="15" value="12">
							</div>

							<div class="w-full flex items-center my-1"></div>
								<input class="w-full my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white" class="boton" type="button" name="generar" id="generar" value="Generar Clave" onclick="instruccion.value='generarClave';formulario_generar.submit()">
							</div>

						</c:when>
			
						<c:otherwise>
				
							<input type="hidden" name="claveGenerada" id="claveGenerada" value="${claveGenerada}">
	
							<div class="w-full h-48 flex items-center my-1 text-3xl text-center">
								<h1 class="m-auto"><c:out value="${claveGenerada}"></c:out></h1>
							</div>
				
							<div class="w-full flex items-center my-1">
								<label class="w-1/4 h-12" for="dificultad">Longitud:</label>
								<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="number" id="dificultad" name="dificultad" min="10" max="15" value="12">	
							</div>

							<div class="w-full flex flex-col items-center my-1">
								<input class="w-full my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white" class="boton" type="button" name="generar" id="generar" value="Generar Clave" onclick="instruccion.value='generarClave';formulario_generar.submit()">
								<input class="w-full my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white" class="boton" type="button" name="guardar" id="guardar" value="Guardar Clave" onclick="instruccion.value='guardarClave';formulario_generar.submit()">
							</div>

						</c:otherwise>
					</c:choose>
		
					<a class="m-auto text-blue-500 hover:underline" href="inicio.jsp" target="blank">Ir a Inicio</a>
		
				</form>

			</div>
		</div>

	</main>
</body>
</html>