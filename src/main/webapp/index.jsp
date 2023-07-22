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
	
	<main class="h-screen w-screen text-2xl py-5 bg-[url('https://images.unsplash.com/photo-1579547621700-03c2c337370a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1878&q=80')] bg-cover h-screen">

	<div class="w-2/4 h-full flex m-auto flex-col justify-center border-2 border-stone-200 rounded-lg">

		<div class="h-1/5 flex flex-col justify-center content-center bg-lime-500 rounded-t-md text-xl text-center p-4">
			<h5 class="font-bold text-3xl text-white">INICIAR SESION</h5>
		</div>

		<div class="flex flex-col h-4/5 p-4 bg-white">

			<form name="flex flex-col justify-center" action="ControladorUsuario" method="post">
				<input type="hidden" name="instruccion" value="iniciarSesion">
	
				<div class="w-full flex flex-row items-center h-1/3 my-1">
					<label class="w-1/4 h-12" for="usuario">Usuario:</label>
					<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="number" id="usuario" name="usuario" required="required">
				</div>

				<div class="w-full flex flex-row items-center h-1/3  my-1">
					<label class="w-1/4 h-12" for="clave">Clave:</label>
					<input class="w-3/4 h-12 border-2 border-stone-200 rounded-lg" type="password" id="clave" name="clave" required="required">
				</div>	

				<div class="w-full flex flex-col h-1/3 text-center  my-1">
					<input class="w-full my-3 p-6 border-1 border-2 border-stone-200 bg-lime-500 hover:bg-lime-600 rounded-lg text-white" type="submit" name="ingresar" id="ingresar" value="Iniciar Sesion">
					<a class="text-blue-500 hover:underline" href="registrarUsuario.jsp" target="blank">¿No estas registrado? Registrate</a>
				
					<h1 class="text-5xl">&#127919</h1>
				</div>
	
				</form>

		</div>
	</div>
	</main>

</body>
</html>