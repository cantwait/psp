package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Transaccion.TipoTransaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

@WebService
public interface ISecurityService {
	
	@WebMethod
	@WebResult(name = "transaccion")
	Transaccion saveTransaccion(@WebParam(name="transaccion")Transaccion t);
	@WebMethod
	@WebResult(name="transaccion")
	Transaccion saveTransaccionOperacionUsuario(@WebParam(name="transaccion")Transaccion t, @WebParam(name="operacionusuarios")List<TransaccionOperacionUsuario> operacionUsuarios);
	@WebMethod
	@WebResult(name="resultado")
	Boolean removeTransaccion(@WebParam(name="transaccion")Transaccion t);
	
	@WebMethod
	@WebResult(name="operacion")
	Operacion saveOperacion(@WebParam(name="operacion")Operacion o);
	@WebMethod
	@WebResult(name="resultado")
	Boolean removeOperacion(@WebParam(name="operacion")Operacion o);
	@WebMethod
	@WebResult(name="operaciones")
	List<Operacion> getOperacionesByUsersTransactions(@WebParam(name="transaccionId")Integer transaccionId, @WebParam(name="usuarioId")Long usuarioId);
	@WebMethod
	@WebResult(name="transaccion-operacion-usuario")
	List<TransaccionOperacionUsuario> getOperacionesTransaccionUsuarioByTransaccion(@WebParam(name="transaccionId")Long transaccionId);
	@WebMethod
	@WebResult(name="transacciones")
	List<Transaccion> getChildremByFather(Transaccion father);
	@WebMethod
	@WebResult(name="raiz")
	Transaccion getRootTransaction();
	@WebMethod
	@WebResult(name="transacciones")
	List<Transaccion> getTransactionLikeNombre(@WebParam(name="nombre")String nombre);
	@WebMethod
	@WebResult(name="operaciones")
	List<Operacion> getAllOperaciones();
	
	@WebMethod
	@WebResult(name="transaccionoperacionusuario")
	List<TransaccionOperacionUsuario> getOperacionesUsuariosByTransaccionId(@WebParam(name="transactionId")Integer transaccionId);
	
	@WebMethod
	@WebResult(name="operaciones")
	List<Operacion> getOperacionesByTransaccionId(@WebParam(name="transaccionId")Integer transaccionId);
	
	@WebMethod
	@WebResult(name="roles")
	List<Rol> getRolesByOperacionAndTransaccion(@WebParam(name="transaccionId") Integer transaccionId, @WebParam(name="operacionId") String operacionId);
	
	@WebMethod
	@WebResult(name="tipotransacciones")
	List<TipoTransaccion> getTipoTransacciones();

}
