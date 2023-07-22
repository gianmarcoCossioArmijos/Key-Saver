<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
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

		<div class="m-5 bg-lime-500 rounded-lg bg-white">
	
			<div class="w-full p-3">
				<h2>Mis Claves &#127774</h2>
			</div>

			<div class="w-full p-3">
	
				<form class="w-full" name="formulario">

					<table class="w-full border-separate border-spacing-2 border border-slate-500">
						<tr class="border border-slate-600">
							<td class="w-1/8 m-2">ID</td>
							<td class="w-1/8 m-2">DNI Usuario</td>
							<td class="w-1/8 m-2">Clave</td>
							<td class="w-1/8 m-2">Usuario/cuenta</td>
							<td class="w-1/8 m-2">Pagina/web</td>
							<td class="w-1/8 m-2">Telefono</td>
							<td class="w-1/8 m-2">Estado</td>
							<td class="w-1/8 m-2">Accion</td>
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
									<button class="py-1 px-3 border-1 border-2 border-stone-200 rounded-md bg-lime-500 hover:bg-lime-600">
										<a href="${linkBuscar}" target="blank">editar</a>
									</button>
									&nbsp;&nbsp;
									<button class="py-1 px-3 border-1 border-2 border-stone-200 bg-yellow-500 rounded-md hover:bg-yellow-600">
										<a href="${linkDesactivar}" target="self">desactivar</a>
									</button>
									&nbsp;&nbsp;
									<button class="py-1 px-3 border-2 border-stone-200 bg-red-500 rounded-md hover:bg-red-600">
										<a href="${linkEliminar}" target="self">eliminar</a>
									</button>
								</td>
							</tr>
			
						</c:forEach>

					</table>
	
				</form>

			</div>

			<div class="flex flex-row justify-between bg-white p-3">
				<button class="w-1/4 my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white"><input type="submit" value="Registrar Clave" onclick="window.location.href='registrarClave.jsp'"/></button>
				<a class="text-blue-500 hover:underline content-center" href="inicio.jsp" target="blank"><h1 class="text-2xl text-end">Ir a Inicio</h1></a>
			</div>

		</div>

	</main>

</body>
</html>