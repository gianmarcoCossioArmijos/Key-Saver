package com.keysaver.controlador;

import java.io.IOException;  
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.keysaver.modelo.ModeloClave;
import com.keysaver.objeto.Clave;

/**
 * Clase controlador usuario
 * @author gianmarcoCossio
 */
@WebServlet("/ControladorClave")
public class ControladorClave extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//Inicializacion del modelo clave
	private ModeloClave modeloClave;

	//Declaracion del pool de conexiones
	@Resource(name = "jdbc/mysql_bd")
	public DataSource pool;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {

			modeloClave = new ModeloClave(pool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Metodo que recibe una instruccion, la cual puede ser generarClave, guardarClave, desactivarClave,
	 * buscarClave, eliminarClave, y de acuerdo a ello ejecutara instrucciones, o en caso contrario devolvera una excepcion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String instruccion = request.getParameter("instruccion");

		switch (instruccion) {
			
		case "generarClave":
			
			try {
				generarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "guardarClave":
			
			try {
				guardarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "desactivarClave":

			try {
				desactivarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "buscarClave":

			try {
				buscarClave(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "eliminarClave":
			
			try {
				eliminarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			throw new IllegalArgumentException("Error: instruccion invalida");
		}
	}
	
	/**
	 * Metodo que recibe una instruccion, la cual puede ser reportarClave, registrarClave, editarClave,
	 * y de acuerdo a ello ejecutara instrucciones, o en caso contrario devolvera una excepcion
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String instruccion = request.getParameter("instruccion");

		switch (instruccion) {
		
		case "reportarClave":

			try {
				reportarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "registrarClave":

			try {
				registrarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "editarClave":

			try {
				editarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			throw new IllegalArgumentException("Error: instruccion invalida");
		}
	}
	
	/**
	 * Metodo que recibe un id como parametro, busca una clave con dicho parametro,
	 * elimina la clave en la base de datos, y en caso contrario devuelve una excepcion
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int cId = Integer.parseInt(request.getParameter("cId"));


		Clave clave = modeloClave.buscarClave(cId);

		modeloClave.registrarClaveEliminada(clave);	

		modeloClave.eliminarClave(cId);

		reportarClave(request, response);
	}

	/**
	 * Metodo que recibe una clave generada, y nos devuelve al formulario para registrar clave
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void guardarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String parametro = request.getParameter("claveGenerada");
		
		request.setAttribute("parametro", parametro);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/registrarClave.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo que recibe una dificultad , la cual sera usada para generar una clave segura,
	 * y nos devuelve a la misma vista de generar clave segura
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void generarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int dificultad = Integer.parseInt(request.getParameter("dificultad"));
		
		String clave = modeloClave.generarClave(dificultad);
		  
		request.setAttribute("claveGenerada", clave);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/generarClave.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo que recibe un id de clave, luego busca una clave con dicho id, y
	 * finalmente nos envia al formulario para editar clave
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void buscarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cId = Integer.parseInt(request.getParameter("cId"));

		Clave clave = modeloClave.buscarClave(cId);

		request.setAttribute("claveBuscada", clave);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/editarClave.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo que recibe datos de una clave, para editarlos
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void editarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("cId"));
		int uDni = Integer.parseInt(request.getParameter("uDni"));
		String cClave = request.getParameter("cClave");
		String cCuenta = request.getParameter("cCuenta");
		String cPagina = request.getParameter("cPagina");
		String cTelefono = request.getParameter("cTelefono");
		String cEstado = request.getParameter("cEstado");

		Clave clave = new Clave(id, uDni, cClave, cCuenta, cPagina, cTelefono, cEstado);

		modeloClave.editarClave(clave);

		reportarClave(request, response);
	}

	/**
	 * Metodo que recibe id de clave, para luego cambiar el estado de la clave
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void desactivarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cId = Integer.parseInt(request.getParameter("cId"));

		modeloClave.desactivarClave(cId);

		reportarClave(request, response);
	}

	/**
	 * Metodo que reporta las claves de un usuario utilizando el dni publico
	 * para obtener dichas claves, y luego nos envia a la vista de las claves
	 * @param request
	 * @param response
	 */
	private void reportarClave(HttpServletRequest request, HttpServletResponse response) {

		List<Clave> clave;
		
		int uDni = ControladorUsuario.dniPublico;

		try {

			clave = modeloClave.reportarClave(uDni);

			request.setAttribute("listaClave", clave);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/listaRegistros.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que recibe datos de una clave, para luego registrarla en la
	 * base de datos
	 * @param request
	 * @param response
	 */
	private void registrarClave(HttpServletRequest request, HttpServletResponse response) {

		int uDni = Integer.parseInt(request.getParameter("uDni"));
		String cClave = request.getParameter("cClave");
		String cCuenta = request.getParameter("cCuenta");
		String cPagina = request.getParameter("cPagina");
		String cTelefono = request.getParameter("cTelefono");
		String cEstado = request.getParameter("cEstado");

		Clave clave = new Clave(uDni, cClave, cCuenta, cPagina, cTelefono, cEstado);

		try {
			modeloClave.registrarClave(clave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		reportarClave(request, response);
	}
}
