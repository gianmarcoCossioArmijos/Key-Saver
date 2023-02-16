package com.keysaver.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.keysaver.objeto.Usuario;

public class ModeloUsuario {

		private DataSource origen;

		public ModeloUsuario(DataSource origen) {

			this.origen = origen;
		}

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
				throw new Exception ("Error al registrar usuario: " + e);
			} finally {
				pst.close();
				cn.close();
			}
		}

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
				
				if(rs.next()) {
					
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
			
				if(rs.next()) {
				
					int dni = rs.getInt("uDni");
					String nombre = rs.getString("uNombre");
					String apellido = rs.getString("uApellido");
					String clave = rs.getString("uClave");
				
					usuario = new Usuario(dni, nombre, apellido, clave);
				}
			} catch (SQLException e) {
				throw new Exception ("No se ha encontrado el usuario: " + e);
			} finally {
				pst.close();
				cn.close();
			}
			return usuario;	
		}

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
				throw new Exception ("Error al editar usuario: " + e);
			} finally {
				pst.close();
				cn.close();
			}
		}
}
