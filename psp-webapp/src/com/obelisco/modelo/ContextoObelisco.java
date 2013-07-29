package com.obelisco.modelo;

import java.util.HashMap;

import com.pdvsa.psp.model.Usuario;

public interface ContextoObelisco {
	
	public static final String ID_CONTEXTO_OBELISCO = "CONTEXTO_OBELISCO";

	public Usuario getUsuarioActual();

	public void setUsuarioActual(Usuario usuario);

	public HashMap getDatosAplicacion();

}
