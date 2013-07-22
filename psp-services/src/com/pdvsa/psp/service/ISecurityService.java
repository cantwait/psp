package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

@WebService
public interface ISecurityService {
	
	@WebMethod
	@WebResult(name = "transaccion")
	Transaccion saveTransaccion(Transaccion t);
	@WebMethod
	@WebResult(name="transaccion")
	Transaccion saveTransaccionOperacionUsuario(Transaccion t, List<TransaccionOperacionUsuario> operacionUsuarios);
	@WebMethod
	@WebResult(name="resultado")
	Boolean removeTransaccion(Transaccion t);
	
	@WebMethod
	@WebResult(name="operacion")
	Operacion saveOperacion(Operacion o);
	@WebMethod
	@WebResult(name="resultado")
	Boolean removeOperacion(Operacion o);
	@WebMethod
	@WebResult(name="operaciones")
	List<TransaccionOperacionUsuario> getOperacionesByUsersTransactions(Transaccion transaccion, Usuario usuario);
	@WebMethod
	@WebResult(name="transaccion-operacion-usuario")
	List<TransaccionOperacionUsuario> getOperacionesTransaccionUsuarioByTransaccion(Transaccion transaccion);
	@WebMethod
	@WebResult(name="transacciones")
	List<Transaccion> getChildremByFather(Transaccion father);
	@WebMethod
	@WebResult(name="raiz")
	Transaccion getRootTransaction();

}
