package com.obelisco.modelo;

import java.util.HashMap;

import com.pdvsa.psp.model.Usuario;

public class ContextoObeliscoImpl implements ContextoObelisco{

	private Usuario usuarioActual;

	private HashMap datosAplicacion = new HashMap();

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuario) {
		this.usuarioActual = usuario;
	}

	public HashMap getDatosAplicacion() {
		return datosAplicacion;
	}

}
