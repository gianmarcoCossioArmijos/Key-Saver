package com.keysaver.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keysaver.objeto.Clave;

public class ModeloClave {

	private DataSource origen;

	public ModeloClave(DataSource origen) {

		this.origen = origen;
	}

	public List<Clave> reportarClave(int uDni) throws Exception {
		
		List<Clave> clave = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		cn = origen.getConnection();
		
		String sql = "SELECT * FROM clave WHERE uDni=?";
		pst = cn.prepareStatement(sql);
		
		pst.setInt(1, uDni);
		
		rs = pst.executeQuery();

		while (rs.next()) {

			int cId = rs.getInt("cId");
			int dni = rs.getInt("uDni");
			String cClave = rs.getString("cClave");
			String cCuenta = rs.getString("cCuenta");
			String cPagina = rs.getString("cPagina");
			String cTelefono = rs.getString("cTelefono");
			String estado = rs.getString("cEstado");

			Clave temporal = new Clave(cId, dni, cClave, cCuenta, cPagina, cTelefono, estado);
			clave.add(temporal);
		}
		pst.close();
		cn.close();
		
		return clave;
	}

	public void desactivarClave(int id) throws Exception {
		
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
		
			cn = origen.getConnection();
		
			String sql = "UPDATE clave SET cEstado=? WHERE cId=?";
			pst = cn.prepareStatement(sql);
		
			pst.setString(1, "inactivo");
			pst.setInt(2, id);
		
			pst.execute();
			
		} catch(SQLException e) {
			throw new Exception ("Error al eliminar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}

	public void registrarClave(Clave clave) throws Exception {
		
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
			cn = origen.getConnection();

			String sql = "INSERT INTO clave (uDni,cClave,cCuenta,cPagina,cTelefono,cEstado) VALUES (?,?,?,?,?,?)";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, clave.getuDni());
			pst.setString(2, clave.getcClave());
			pst.setString(3, clave.getcCuenta());
			pst.setString(4, clave.getcPagina());
			pst.setString(5, clave.getcTelefono());
			pst.setString(6, clave.getcEstado());

			pst.execute();

		} catch (SQLException e) {
			throw new Exception ("Error al registrar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}

	public Clave buscarClave(int cId) throws Exception  {
		
		int idClave = cId;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Clave clave = null;
		
		try {
			cn = origen.getConnection();

			String sql = "SELECT * FROM clave WHERE cId=?";

			pst = cn.prepareStatement(sql);

			pst.setInt(1, idClave);

			rs = pst.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("cId");
				int uDni = rs.getInt("uDni");
				String cClave = rs.getString("cClave");
				String cCuenta = rs.getString("cCuenta");
				String cPagina = rs.getString("cPagina");
				String cTelefono = rs.getString("cTelefono");
				String cEstado = rs.getString("cEstado");

				clave = new Clave(id, uDni, cClave, cCuenta, cPagina, cTelefono, cEstado);
			} else {
				throw new Exception("No se ha encontrado el registro con ID: " + idClave);
			}

		} catch (SQLException e) {
			throw new Exception ("Error al buscar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
		
		return clave;
	}

	public void editarClave(Clave clave) throws Exception {
		
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
			cn = origen.getConnection();

			String sql = "UPDATE clave SET uDni=?,cClave=?,cCuenta=?,cPagina=?,cTelefono=?,cEstado=? WHERE cId=?";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, clave.getuDni());
			pst.setString(2, clave.getcClave());
			pst.setString(3, clave.getcCuenta());
			pst.setString(4, clave.getcPagina());
			pst.setString(5, clave.getcTelefono());
			pst.setString(6, clave.getcEstado());
			pst.setInt(7, clave.getcId());

			pst.execute();

		} catch (SQLException e) {
			throw new Exception ("Error al editar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}

	public String generarClave(int dificultad) {
		
		int random;
		List<String> generados = new ArrayList<>();
		
		for(int i = 0; i < dificultad; i++) {
			
			String caracter = "";
			String[] ascii = new String[6];
			
			ascii[0] = "@";
			ascii[1] = "#";
			ascii[2] = "$";
			ascii[3] = "%";
			ascii[4] = "&";
			ascii[5] = "?";
			
			if (i == 3 || i == 9 || i == 10) {
				
				int num = (int)(Math.random() * 6 + 0);
				caracter = ascii[num];
		
			} else {
				
				if (i % 2 == 0) {
					
					int numero = (int)(Math.random() * 2 + 1);
					random = (int)(Math.random() * 26 + 65);
					
					if (numero == 1) {
						
						caracter = Character.toString((char)random).toLowerCase();
					} else {
					
						caracter = Character.toString((char)random);
					}
				} else {
					
					random = (int)(Math.random() * 9 + 0);
					caracter = String.valueOf(random);
				}	
				
			}
			generados.add(caracter);
		}
		
		String caracteresGenerados = String.join("", generados);
		return caracteresGenerados;
	}

	public void eliminarClave(int cId) throws Exception {
		
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
		
			cn = origen.getConnection();
		
			String sql = "DELETE FROM clave WHERE cId=?";
			pst = cn.prepareStatement(sql);
		
			pst.setInt(1, cId);
		
			pst.execute();
			
		} catch(SQLException e) {
			throw new Exception ("Error al eliminar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}

	public void registrarClaveEliminada(Clave clave) throws Exception {
		
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
			cn = origen.getConnection();

			String sql = "INSERT INTO claveEliminada (uDni,eClave,eCuenta,ePagina,eTelefono,eEstado) VALUES (?,?,?,?,?,?)";
			pst = cn.prepareStatement(sql);

			pst.setInt(1, clave.getuDni());
			pst.setString(2, clave.getcClave());
			pst.setString(3, clave.getcCuenta());
			pst.setString(4, clave.getcPagina());
			pst.setString(5, clave.getcTelefono());
			pst.setString(6, clave.getcEstado());

			pst.execute();

		} catch (SQLException e) {
			throw new Exception ("Error al registrar clave: " + e);
		} finally {
			pst.close();
			cn.close();
		}
	}
}
