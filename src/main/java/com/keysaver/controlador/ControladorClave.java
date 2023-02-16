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

@WebServlet("/ControladorClave")
public class ControladorClave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloClave modeloClave;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String instruccion = request.getParameter("instruccion");

		switch (instruccion) {

		case "registrarClave":

			try {
				registrarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "reportarClave":

			try {
				reportarClave(request, response);
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

		case "editarClave":

			try {
				editarClave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
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

	private void eliminarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int cId = Integer.parseInt(request.getParameter("cId"));


		Clave clave = modeloClave.buscarClave(cId);

		modeloClave.registrarClaveEliminada(clave);	

		modeloClave.eliminarClave(cId);

		reportarClave(request, response);
	}

	private void guardarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String parametro = request.getParameter("claveGenerada");
		
		request.setAttribute("parametro", parametro);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/registrarClave.jsp");
		dispatcher.forward(request, response);
	}

	private void generarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int dificultad = Integer.parseInt(request.getParameter("dificultad"));
		
		String clave = modeloClave.generarClave(dificultad);
		  
		request.setAttribute("claveGenerada", clave);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/generarClave.jsp");
		dispatcher.forward(request, response);
	}

	private void buscarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cId = Integer.parseInt(request.getParameter("cId"));

		Clave clave = modeloClave.buscarClave(cId);

		request.setAttribute("claveBuscada", clave);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/editarClave.jsp");
		dispatcher.forward(request, response);
	}

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

	private void desactivarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cId = Integer.parseInt(request.getParameter("cId"));

		modeloClave.desactivarClave(cId);

		reportarClave(request, response);
	}

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
