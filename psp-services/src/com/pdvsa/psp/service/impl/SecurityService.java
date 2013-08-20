package com.pdvsa.psp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IOperacionDAO;
import com.pdvsa.psp.dao.IRolDAO;
import com.pdvsa.psp.dao.ITransaccionDAO;
import com.pdvsa.psp.dao.ITransaccionOperacionUsuarioDAO;
import com.pdvsa.psp.dao.IUserRoleDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Transaccion.TipoTransaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.ISecurityService;

@WebService(serviceName = "manageSecurityService", endpointInterface = "com.pdvsa.psp.service.ISecurityService")
@Service("securityService")
public class SecurityService implements ISecurityService {

	@Autowired
	private ITransaccionDAO transaccionDAO;
	@Autowired
	private ITransaccionOperacionUsuarioDAO transaccionOperacionUsuarioDAO;
	@Autowired
	private IOperacionDAO operacionDAO;
	@Autowired
	private IUserRoleDAO userRolDAO;

	@Override
	public Transaccion saveTransaccion(Transaccion t) {
		if (t != null) {
			System.out.println("Guardando Transaccion");
			if (t.getTransaccionOperaciones() != null
					&& t.getTransaccionOperaciones().size() > 0) {
				System.out.println("Hijos: "
						+ t.getTransaccionOperaciones().size());
			}
			return transaccionDAO.save(t);
		}
		return null;
	}

	@Override
	public Transaccion saveTransaccionOperacionUsuario(Transaccion t,
			List<TransaccionOperacionUsuario> operacionUsuarios) {

		if (operacionUsuarios != null && operacionUsuarios.size() > 0) {
			System.out
					.println("Cantidad de hijos: " + operacionUsuarios.size());
			for (TransaccionOperacionUsuario transaccionOperacionUsuario : operacionUsuarios) {
				System.out.println("esta entrando por aqui!!");
				transaccionOperacionUsuario.setTransaccion(t);
				t.getTransaccionOperaciones().add(transaccionOperacionUsuario);
			}
			// t.getTransaccionOperaciones().addAll(operacionUsuarios);
		}
		return this.saveTransaccion(t);
	}

	@Override
	public Operacion saveOperacion(Operacion o) {

		return operacionDAO.save(o);
	}

	@Override
	public Boolean removeOperacion(Operacion o) {
		if (o != null) {
			return operacionDAO.remove(o);
		}
		return false;
	}

	@Override
	public List<Operacion> getOperacionesByUsersTransactions(Integer transaccionId, Long usuarioId) {
		
		List<Rol> rolesXUsuario = new ArrayList<Rol>();
		List<Long> ids = new ArrayList<Long>();
		rolesXUsuario = userRolDAO.getRolesByUser(usuarioId);
		
		for (Rol rol : rolesXUsuario) {
			ids.add(rol.getId());
		}
		
		return transaccionOperacionUsuarioDAO.getOperacionesByTransaccionAndUsuario(transaccionId, ids);
	}

	@Override
	public Boolean removeTransaccion(Transaccion t) {
		if (t != null && t.getCodigo() > 0) {
			return transaccionDAO.remove(t);
		}
		return false;
	}

	@Override
	public List<Transaccion> getChildremByFather(Transaccion father) {
		Search s = new Search();
		s.addFilterEqual("padre", father);
		return transaccionDAO.search(s);
	}

	@Override
	public List<TransaccionOperacionUsuario> getOperacionesTransaccionUsuarioByTransaccion(
			Long transaccionId) {
		List<TransaccionOperacionUsuario> ops = new ArrayList<TransaccionOperacionUsuario>();
		Search s = new Search();
		s.addFilterEqual("transaccion.codigo", transaccionId);
		ops = transaccionOperacionUsuarioDAO.search(s);
		return ops;
	}

	@Override
	@WebMethod
	@WebResult(name = "raiz")
	public Transaccion getRootTransaction() {
		Search s = new Search();
		s.addFilterEqual("tipoTransaccion", TipoTransaccion.ROOT);
		Transaccion raiz = transaccionDAO.searchUnique(s);
		return raiz;
	}

	@Override
	public List<Transaccion> getTransactionLikeNombre(String nombre) {

		Search s = new Search();
		s.addFilterILike("nombre", nombre);
		return transaccionDAO.search(s);
	}

	@Override
	public List<Operacion> getAllOperaciones() {

		return operacionDAO.findAll();
	}

	public List<TransaccionOperacionUsuario> getOperacionesUsuariosByTransaccionId(
			Integer transaccionId) {
		return transaccionOperacionUsuarioDAO
				.getOperacionesUsuarioByTransaccion(transaccionId);
	}

	@Override
	public List<Operacion> getOperacionesByTransaccionId(Integer transaccionId) {

		return transaccionOperacionUsuarioDAO
				.getOperacionByTransaccion(transaccionId);
	}

	@Override
	public List<Rol> getRolesByOperacionAndTransaccion(
			Integer transaccionId, String operacionId) {

		return transaccionOperacionUsuarioDAO
				.getRolesByTransaccionAndOperacion(transaccionId,
						operacionId);
	}

	@Override
	@WebMethod
	@WebResult(name = "tipotransacciones")
	public List<TipoTransaccion> getTipoTransacciones() {

		List<TipoTransaccion> tipos = new ArrayList<TipoTransaccion>();

		for (TipoTransaccion tipoTransaccion : TipoTransaccion.values()) {
			tipos.add(tipoTransaccion);
		}

		return tipos;

	}

}
