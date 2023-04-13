package com.keysaver.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.keysaver.objeto.Usuario;

/**
 * Esta clase contiene los metodos CRUD para usuarios
 * @author gianmarcoCossio
 */
public class ModeloUsuario {

	//Declaracion de datasource
	private DataSource origen;

	/**
	 * Metodo que inicializa el datasource
	 * @param origen
	 */
	public ModeloUsuario(DataSource origen) {

		this.origen = origen;
	}

	/**
	 * Metodo que recibe un usuario como parametro y lo guardaen la base de datos
	 * o devuelve una excepcion en caso de no poder guardar el usuario
	 * @param usuario
	 * @throws Exception
	 */
	public void registrarUsuario(Usuario usuario) throws Exception {

		Connection cn = null;
		PreparedStatement pst = null;

		try {

			cn = origen.getConnection();

			String sql = "INSERT INTO usuario (uDni,uNombre,uApellido,uClave) VALUES (?,?,?,?)";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, usuario.getuDni());
			pst.setString(2, usuario.getuNombre());
			pst.setString(3, usuario.getuApellido());
			pst.setString(4, usuario.getuClave());

			pst.execute();

		} catch (SQLException e) {
			throw new Exception("Error al registrar usuario: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}

	/**
	 * Metodo para iniciar sesion, que recibe un dni y una clave como parametros
	 * con dichos parametros busca un usuario en la base de datos y devuelve true
	 * en caso de encontrar al usuario o una excepcion de lo contrario
	 * @param dni
	 * @param clave
	 * @return respuesta
	 * @throws Exception
	 */
	public boolean iniciarSesion(int dni, String clave) throws Exception {

		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean respuesta = false;

		try {

			cn = origen.getConnection();

			String sql = "SELECT * FROM usuario WHERE uDni=? AND uClave=?";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, dni);
			pst.setString(2, clave);

			rs = pst.executeQuery();

			if (rs.next()) {

				respuesta = true;
			} else {
				throw new Exception("No se ha encontrado el usuario: " + dni);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			cn.close();
		}
		return respuesta;
	}

	/**
	 * Metodo que recibe un dni como parametro, luego busca un usuario
	 * en la base de datos y duevuelve un usuario en caso de encontrarlo
	 * o una excepcion de lo contrario
	 * @param dniPublico
	 * @return usuario
	 * @throws Exception
	 */
	public Usuario buscarUsuario(int dniPublico) throws Exception {

		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario usuario = null;

		try {

			cn = origen.getConnection();

			String sql = "SELECT * FROM usuario WHERE uDni=?";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, dniPublico);

			rs = pst.executeQuery();

			if (rs.next()) {

				int dni = rs.getInt("uDni");
				String nombre = rs.getString("uNombre");
				String apellido = rs.getString("uApellido");
				String clave = rs.getString("uClave");

				usuario = new Usuario(dni, nombre, apellido, clave);
			}
		} catch (SQLException e) {
			throw new Exception("No se ha encontrado el usuario: " + e);
		} finally {
			pst.close();
			cn.close();
		}
		return usuario;
	}

	/**
	 * Metodo que recibe un usuario como parametro, y actualiza o edita en la base de datos
	 * dicho usuario utilizando el dni del usuario recibido por paramatro, o devuelve
	 * una excepcion en caso contrario
	 * @param usuario
	 * @throws Exception
	 */
	public void editarUsuario(Usuario usuario) throws Exception {

		Connection cn = null;
		PreparedStatement pst = null;

		try {

			cn = origen.getConnection();

			String sql = "UPDATE usuario SET uNombre=?,uApellido=?,uClave=? WHERE uDni=?";
			pst = cn.prepareStatement(sql);

			pst.setString(1, usuario.getuNombre());
			pst.setString(2, usuario.getuApellido());
			pst.setString(3, usuario.getuClave());
			pst.setInt(4, usuario.getuDni());

			pst.execute();

		} catch (SQLException e) {
			throw new Exception("Error al editar usuario: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}
}
