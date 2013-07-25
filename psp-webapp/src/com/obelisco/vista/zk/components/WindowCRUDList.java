package com.obelisco.vista.zk.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.obelisco.modelo.servicios.seguridad.AutenticarUsuario;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.ISecurityService;

public class WindowCRUDList extends GenericWindowList implements AfterCompose{
	
	
	private static final long serialVersionUID = 2549160560441391377L;
	private Transaccion transaccion;

	public WindowCRUDList() {
		super();
		this.addEventListener("onCreate", createListener);
	}

	protected EventListener createListener = new EventListener() {

		public void onEvent(Event event) throws Exception {
			if (transaccion != null) {
				List<Operacion> operaciones = new ArrayList<Operacion>();
				Usuario currentUser = getAutenticarUsuario().getCurrentUser();
				if(currentUser != null){
					List<Operacion> ops = getSecurityService().getOperacionesByUsersTransactions(transaccion.getCodigo(), currentUser.getId());
					if(ops != null && ops.size() > 0){
						operaciones.addAll(ops);
					}
				}
				
				Collections.sort(operaciones, new Comparator<Operacion>(){

					@Override
					public int compare(Operacion o1, Operacion o2) {
						int i1 = ((Operacion) o1).getOrden().intValue();
						int i2 = ((Operacion) o2).getOrden().intValue();

						return Math.abs(i1) - Math.abs(i2);
					}
					
				});
				tool.createFunctions(operaciones);
			}
		}

	};	
	
	@Override
	protected void doDeleteEntity(Object entity) throws InterruptedException {
		// TODO Auto-generated method stub
		try {

			String nombreMetodo = "doDeleteEntity";

			Object[] args = new Object[] {this.getEntity()};
			Class[] types = new Class[] {this.getEntity().getClass()};

			executeZKFunction(nombreMetodo, types, args);
			
		}
		catch (Exception e) {
			showMessage(e.getMessage());
		}
		
		loadData();
	}

	private void loadData() throws InterruptedException {
		// TODO Auto-generated method stub
		
		try {

			String nombreMetodo = "doLoadData";

			Object[] args = new Object[] {};
			Class[] types = new Class[] {};

			executeZKFunction(nombreMetodo, types, args);
			
		}
		catch (Exception e) {
			showMessage(e.getMessage());
		}

		refreshControls();		
		
	}

/*	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub

		Components.wireVariables(this, this);

		// NO need to register onXxx event listeners

		// auto forward
		Components.addForwards(this, this);
		
	}
*/	
	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
		
}
