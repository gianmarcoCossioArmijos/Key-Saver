package com.keysaver.controlador;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.keysaver.modelo.ModeloUsuario;
import com.keysaver.objeto.Usuario;

/**
 * Clase controlador usuario
 * @author gianmarcoCossio
 */
@WebServlet("/ControladorUsuario")
public class ControladorUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
	//Inicializacion del modelo usuario
	private ModeloUsuario modeloUsuario;
	
	//variable publica del dni de usuario que ayudara a verificar si el usuario esta logeado o no
	public static int dniPublico;
	
	//Declaracion del pool de conexiones
	@Resource(name = "jdbc/mysql_bd")
	public DataSource pool;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {

			modeloUsuario = new ModeloUsuario(pool);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Metodo que recibe una instruccion, en caso de recibir formatearClave como instruccion
	 * se ejecutaran sus instruccioness en caso contrario devuelve una excepcion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String instruccion = request.getParameter("instruccion");
		
		switch (instruccion) {
			
		case "formatearClave":
			
			try {
				formatearDni(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			throw new IllegalArgumentException("Error: instruccion invalida");
		}
	}
	
	/**
	 * Metodo que recibe una instruccion, la cual puede ser registrarUsuario, iniciarSesion, buscarUsuario,
	 * o editarUsuario, y de acuerdo a ello ejecutara instrucciones, o en caso contrario devolvera una excepcion
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String instruccion = request.getParameter("instruccion");
		
		switch (instruccion) {
		
		case "registrarUsuario": 
			
			try {
				registrarUsuario(request,response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "iniciarSesion":
			
			try {
				iniciarSesion(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "buscarUsuario":
			
			try {
				buscarUsuario(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "editarUsuario":
			
			try {
				editarUsuario(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		default:
			throw new IllegalArgumentException("Error: instruccion invalida");
		}
	}

	/**
	 * Metodo que obtiene los datos del usuario, los edita en la base de datos y retorna al inicio
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int dni = Integer.parseInt(request.getParameter("dni"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String clave = request.getParameter("clave");
		
		Usuario usuario = new Usuario(dni, nombre, apellido, clave);
		
		modeloUsuario.editarUsuario(usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/inicio.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo que busca al usuario con el dni publico, y retorna ala vista para editar el usuario buscado
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = modeloUsuario.buscarUsuario(dniPublico);
		
		request.setAttribute("usuario", usuario);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/editarUsuario.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo que fomatea dni publico, y asi cerrar sesion, con esto no se podra obtener
	 * informacion de las claves del usuario con el dni a formatear, y nos retorna al inicio
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void formatearDni(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		dniPublico = 0;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo que obtiene el dni y clave del usuario y lo busca en la base de datos
	 * para asi poder logearse, en caso de encontrar al suuario nos retorna al inicio
	 * y en caso contrario nos retorna al login
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int dni = Integer.parseInt(request.getParameter("usuario"));
		String clave = request.getParameter("clave");
		
		boolean respuesta;
		
		try {
			respuesta = modeloUsuario.iniciarSesion(dni,clave);
			
			if (respuesta == true) {
				
				dniPublico = Integer.parseInt(request.getParameter("usuario"));
				
				request.setAttribute("dni", dni);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/inicio.jsp");
				dispatcher.forward(request, response);
				
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene los datos de un usuario nuevo, lo registra, y nos retorna
	 * al login para poder logearnos
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int dni = Integer.parseInt(request.getParameter("uDni"));
		String nombre = request.getParameter("uNombre").toUpperCase();
		String apellido = request.getParameter("uApellido").toUpperCase();
		String clave = request.getParameter("uClave");
		
		Usuario usuario = new Usuario(dni, nombre, apellido, clave);
		
		try {
			modeloUsuario.registrarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
