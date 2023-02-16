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

@WebServlet("/ControladorUsuario")
public class ControladorUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ModeloUsuario modeloUsuario;
	
	public static int dniPublico;
	
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
		case "formatearClave":
			
			try {
				formatearClave(request, response);
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
	
	private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = modeloUsuario.buscarUsuario(dniPublico);
		
		request.setAttribute("usuario", usuario);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/editarUsuario.jsp");
		dispatcher.forward(request, response);
	}

	private void formatearClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		dniPublico = 0;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

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
