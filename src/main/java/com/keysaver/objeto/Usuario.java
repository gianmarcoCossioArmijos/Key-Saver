package com.keysaver.objeto;

/**
 * Clase que crea un objeto de tipo usuario
 * @author gianmarcoCossio
 */
public class Usuario {

	private int uDni;
	private String uNombre;
	private String uApellido;
	private String uClave;
	
	public Usuario(int uDni, String uNombre, String uApellido, String uClave) {
		
		this.uDni = uDni;
		this.uNombre = uNombre;
		this.uApellido = uApellido;
		this.uClave = uClave;
	}

	@Override
	public String toString() {
		return "Usuario [uDni=" + uDni + ", uNombre=" + uNombre + ", uApellido=" + uApellido + ", uClave="
				+ uClave + "]";
	}

	public int getuDni() {
		return uDni;
	}

	public void setuDni(int uDni) {
		this.uDni = uDni;
	}

	public String getuNombre() {
		return uNombre;
	}

	public void setuNombre(String uNombre) {
		this.uNombre = uNombre;
	}

	public String getuApellido() {
		return uApellido;
	}

	public void setuApellido(String uApellido) {
		this.uApellido = uApellido;
	}

	public String getuClave() {
		return uClave;
	}

	public void setuClave(String uClave) {
		this.uClave = uClave;
	}
}
