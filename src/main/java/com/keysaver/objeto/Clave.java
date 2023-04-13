package com.keysaver.objeto;

/**
 * Clase para crear un objeto de tipo clave
 * @author gianmarcoCossio
 */
public class Clave {
	
	private int cId;
	private int uDni;
	private String cClave;
	private String cCuenta;
	private String cPagina;
	private String cTelefono;
	private String cEstado;
	
	public Clave(int cId, int uDni, String cClave, String cCuenta, String cPagina, String cTelefono, String cEstado) {
		super();
		this.cId = cId;
		this.uDni = uDni;
		this.cClave = cClave;
		this.cCuenta = cCuenta;
		this.cPagina = cPagina;
		this.cTelefono = cTelefono;
		this.cEstado = cEstado;
	}

	public Clave(int uDni, String cClave, String cCuenta, String cPagina, String cTelefono, String cEstado) {
		super();
		this.uDni = uDni;
		this.cClave = cClave;
		this.cCuenta = cCuenta;
		this.cPagina = cPagina;
		this.cTelefono = cTelefono;
		this.cEstado = cEstado;
	}

	@Override
	public String toString() {
		return "Clave [cId=" + cId + ", uDni=" + uDni + ", cClave=" + cClave + ", cCuenta=" + cCuenta + ", cPagina="
				+ cPagina + ", cTelefono=" + cTelefono + ", cEstado=" + cEstado + "]";
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getuDni() {
		return uDni;
	}

	public void setuDni(int uDni) {
		this.uDni = uDni;
	}

	public String getcClave() {
		return cClave;
	}

	public void setcClave(String cClave) {
		this.cClave = cClave;
	}

	public String getcCuenta() {
		return cCuenta;
	}

	public void setcCuenta(String cCuenta) {
		this.cCuenta = cCuenta;
	}

	public String getcPagina() {
		return cPagina;
	}

	public void setcPagina(String cPagina) {
		this.cPagina = cPagina;
	}

	public String getcTelefono() {
		return cTelefono;
	}

	public void setcTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public String getcEstado() {
		return cEstado;
	}

	public void setcEstado(String cEstado) {
		this.cEstado = cEstado;
	}
}
