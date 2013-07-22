package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IOperacionDAO;
import com.pdvsa.psp.dao.ITransaccionDAO;
import com.pdvsa.psp.dao.ITransaccionOperacionUsuarioDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Transaccion.TipoTransaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.ISecurityService;

@WebService(serviceName = "manageSecurityService", endpointInterface = "com.pdvsa.psp.service.ISecurityService")
@Service("securityService")
public class SecurityService implements ISecurityService{
	
	@Autowired
	private ITransaccionDAO transaccionDAO;
	@Autowired
	private ITransaccionOperacionUsuarioDAO transaccionOperacionUsuarioDAO;
	@Autowired
	private IOperacionDAO operacionDAO;

	@Override
	public Transaccion saveTransaccion(Transaccion t) {
		if(t != null){
			return transaccionDAO.save(t);
		}
		return null;
	}

	@Override
	public Transaccion saveTransaccionOperacionUsuario(Transaccion t, List<TransaccionOperacionUsuario> operacionUsuarios) {
		if(operacionUsuarios != null && operacionUsuarios.size() > 0){
			for (TransaccionOperacionUsuario transaccionOperacionUsuario : operacionUsuarios) {
				transaccionOperacionUsuario.setTransaccion(t);
			}
			t.getTransaccionOperaciones().addAll(operacionUsuarios);
		}
		return saveTransaccion(t);
	}

	@Override
	public Operacion saveOperacion(Operacion o) {
		
		return operacionDAO.save(o);
	}

	@Override
	public Boolean removeOperacion(Operacion o) {
		if(o != null){
			return operacionDAO.remove(o);
		}
		return false;
	}

	@Override
	public List<TransaccionOperacionUsuario> getOperacionesByUsersTransactions(
			Transaccion transaccion, Usuario usuario) {
		
		return transaccionOperacionUsuarioDAO.getOperacionesByTransaccionAndUsuario(transaccion, usuario);
	}

	@Override
	public Boolean removeTransaccion(Transaccion t) {
		if(t != null && t.getCodigo() > 0){
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
	public List<TransaccionOperacionUsuario> getOperacionesTransaccionUsuarioByTransaccion(Transaccion transaccion) {
		Search s = new Search();
		s.addFilterEqual("transaccion", transaccion);
		return transaccionOperacionUsuarioDAO.search(s);
	}

	@Override
	@WebMethod
	@WebResult(name = "raiz")
	public Transaccion getRootTransaction() {
		Search s = new Search();
		s.addFilterEqual("tipoTransaccion", TipoTransaccion.ROOT);
		
		Transaccion raiz = transaccionDAO.searchUnique(s);
		refreshTree(raiz);
		return raiz;
	}
	
	private void refreshTree(Transaccion t){
		
		if(t != null){
			List<Transaccion> hijos = getChildremByFather(t);
			for (Transaccion transaccion : hijos) {
				refreshTree(t);
				t.getAux().add(transaccion);
			}
		}
	}

}
