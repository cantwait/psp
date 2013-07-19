package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.OperacionUsuario;
import com.pdvsa.psp.model.Usuario;

public interface IOperacionUsuarioDAO extends GenericDAO<OperacionUsuario, Long>{
	
	List<OperacionUsuario> getOperacionUsuarioByUsuario(Usuario usuario);
	List<OperacionUsuario> getOperacionUsuarioByOperacion(Operacion operacion);

}
