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

			<c:set var="claveGenerada" value="${parametro}"></c:set>

			<div class="h-1/5 flex flex-col justify-center content-center bg-lime-500 rounded-t-md text-xl text-center p-4">
				<h5 class="font-bold text-3xl text-white">Registrar Clave &#128125</h5>
			</div>

			<div class="w-full p-3">
				
				<form class="w-full flex flex-col" name="formulario_registrar" action="ControladorClave" method="post">
				
					<input type="hidden" name="instruccion" value="registrarClave">
			
					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="uDni">DNI Usuario:</label>
						<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="number" id="uDni" name="uDni" required="required">
					</div>
			
					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="cClave">Clave:</label>

						<c:choose>	
							<c:when test="${claveGenerada == ''}">
								<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="text" id="cClave" name="cClave" required="required">
							</c:when>
			
							<c:otherwise>
								<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="text" id="cClave" name="cClave" required="required" value="${claveGenerada}">
							</c:otherwise>
						</c:choose>
					</div>

					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="cCuenta">Usuario/Cuenta:</label>
						<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="text" id="cCuenta" name="cCuenta" required="required">
					</div>

					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="cPagina">Pagina/Web:</label>
						<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="text" id="cPagina" name="cPagina" required="required">
					</div>

					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="cTelefono">Telefono:</label>
						<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="text" id="cTelefono" name="cTelefono" required="required">
					</div>

					<div class="w-full flex flex-row items-center h-1/7  my-1">
						<label class="w-1/4 h-12" for="cEstado">Estado:</label>
						<select name="cEstado" id="cEstado" class="w-3/4 h-12 border-2 border-stone-200 rounded-lg">
                			<option value="activo">ACTIVO</option>
                			<option value="inactivo">INACTIVO</option>
            			</select>
					</div>
				
					<div class="w-full flex flex-col items-center h-1/7  my-1">
						<input class="w-full my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white" type="submit" name="registrar" id="registrar" value="Registrar">
						<a class="text-blue-500 hover:underline" href="inicio.jsp" target="blank">Ir a Inicio</a>
					</div>

				</form>
			</div>
		</div>

	</main>
</body>
</html>