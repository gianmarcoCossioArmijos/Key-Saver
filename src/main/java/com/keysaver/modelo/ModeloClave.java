package com.keysaver.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keysaver.objeto.Clave;

/**
 * Clase que contiene metodos CRUD y otros metodos para claves
 * @author gianmarcoCossio
 */
public class ModeloClave {

	//Declaracion de datasource
	private DataSource origen;

	/**
	 * Metodo que inicializa el datasource
	 * @param origen
	 */
	public ModeloClave(DataSource origen) {

		this.origen = origen;
	}

	/**
	 * Metodo que recibe un dni como parametro, luego busca las claves
	 * que tengan como usuario relacionado dicho dni, y decuelve una lista
	 * de claves en cado de encotrar claves existentes, o una excepcion de lo contrario
	 * @param uDni
	 * @return clave
	 * @throws Exception
	 */
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

	/**
	 * Metodo que recibe un id de obejto clave como parametro, luego
	 * actuliza el estado de una clave que contenga dicho parametro
	 * o devuelve una excepcion en caso de no encontrar dicho parametro
	 * @param id
	 * @throws Exception
	 */
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

	/**
	 * Metodoq ue recibe una clave como  parametro, luego la guarda en
	 * la base de datos, o devuelve una excepcion en caso contrario
	 * @param clave
	 * @throws Exception
	 */
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

	/**
	 * Metodo que recibe un id de clave como parametro, luego busca una clave con dicho
	 * parametro en la base de datos y devuelve una clave en caso de encontrarla
	 * o una excepcion en caso contrario
	 * @param cId
	 * @return clave
	 * @throws Exception
	 */
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

	/**
	 * Metodo que recibe una clave como parametro, luego actuliza dicha clave
	 * en la base de datos utilizando su id para actualizarla, o devuelve
	 * una excepcion en caso contrario
	 * @param clave
	 * @throws Exception
	 */
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

	/**
	 * Metodo que recibe una dificultad como parametro, luego genera una contraseña
	 * segura segun la dificultad seleccionada, y duevuelve la contraseña segura generada
	 * @param dificultad
	 * @return
	 */
	public String generarClave(int dificultad) {
		
		int random;
		
		//Array de strings que contendra la contraseña segura generada
		List<String> generados = new ArrayList<>();
		
		//Bucle que recorre la longitud de dificultad seleccioanda
		for(int i = 0; i < dificultad; i++) {
			
			//Declaracion de variable que contendra caracter especial
			String caracter = "";
			
			//Array de string que contiene caracteres especiales
			String[] ascii = new String[6];
			
			ascii[0] = "@";
			ascii[1] = "#";
			ascii[2] = "$";
			ascii[3] = "%";
			ascii[4] = "&";
			ascii[5] = "?";
			
			/* La tercera, novena y decima letra de la contraseña segura
			 * que se generara seran caracteres especiles
			 */
			if (i == 3 || i == 9 || i == 10) {
				
				/* Se utiliza math random para devolver un numero del 0 al 6 y asignar
				 * el caracter especial que se encuentre en la posicion de dicho numero,
				 * y asigna dicho caracter ala variable caracter
				 */
				int num = (int)(Math.random() * 6 + 0);
				caracter = ascii[num];
		
			} else {
				
				/* Los caracteres pares dentro de la contraseña que se generara en posicion par seran letras
				 * y los caracteres en posicion impar impares seran numeros
				 */
				if (i % 2 == 0) {
					
					/* Se utiliza math random para devolver un numero del 1 al 2, en caso
					 * de ser 1 la letra sera minuscula y en caso de ser 2 la letra sera mayuscula
					 */
					int numero = (int)(Math.random() * 2 + 1);
					
					 /* Se utiliza math random para devolver un numero del 26 al 65, se obtendra el caracter ascii
					  * de dicho numero y se convertira a string o letra
					  */
					random = (int)(Math.random() * 26 + 65);
					
					if (numero == 1) {
						
						caracter = Character.toString((char)random).toLowerCase();
					} else {
					
						caracter = Character.toString((char)random);
					}
				} else {
					
					/* Se utiliza math random para devolver un numero del 26 al 65, se obtendra el caracter ascii
					 * de dicho numero y se convertira a string o letra
					 */
					random = (int)(Math.random() * 9 + 0);
					caracter = String.valueOf(random);
				}	
				
			}
			
			//se añade un caracter al array negerados en cada vuelta de bucle
			generados.add(caracter);
		}
		
		String caracteresGenerados = String.join("", generados);
		return caracteresGenerados;
	}

	/**
	 * Metodo que recibe un id de clave comom parametro, y elimina la contraseña
	 * con dicho id de la base de datos
	 * @param cId
	 * @throws Exception
	 */
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

	/**
	 * Metodo que recibe una clave eliminada como parametro, luego registra la clave
	 * en la tabla claveEliminada de la base de datos o devuelve una excepcion de lo contrario 
	 * @param clave
	 * @throws Exception
	 */
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
