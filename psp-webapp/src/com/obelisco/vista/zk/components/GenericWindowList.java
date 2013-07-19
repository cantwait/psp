package com.obelisco.vista.zk.components;

import java.util.Collection;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import com.obelisco.vista.zk.controls.Menubar;
import com.obelisco.vista.zk.controls.OperationType;
import com.obelisco.vista.zk.controls.Toolbar;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;


public class GenericWindowList extends GenericWindow implements AfterCompose{
	
	protected Listbox lstbox;
	protected Toolbar tool;
	protected Menubar mnu;
	private Transaccion transaccion;
	private Object entity;
	private Collection entityList;
	private String reportName;

	public GenericWindowList() {		
		super();
		this.addEventListener("onCreate", createListener);
	}

	protected EventListener createListener = new EventListener() {
		
		public void onEvent(Event event) throws Exception {
//			if (transaccion != null) {
//				tool.createFunctions(transaccion.getOperaciones());
//				mnu.createFunctions(transaccion.getOperaciones());
//			}
		}
	};

	@Override
	public void afterCompose() {
		ConventionWires.wireVariables(this, this);
		ConventionWires.addForwards(this, this);
	}

	public void refreshControls() {

		AnnotateDataBinder binderLocal = (AnnotateDataBinder) getAttribute("binder", true);
		if (binderLocal != null) {
			binderLocal.loadAll();
		}

		AnnotateDataBinder binder = (AnnotateDataBinder) getPage().getAttribute("binder");
		if (binder != null) {
			binder.loadAll();
		}

	}

	public void onSelectFunction$tool(Event event) throws InterruptedException {
		Operacion operacion = null;
		if (event instanceof ForwardEvent) {
			ForwardEvent forwardEvent = (ForwardEvent) event;
			operacion = (Operacion) forwardEvent.getOrigin().getData();
			
		}

		executeOperation(operacion);
	}

	public void onSelectFunction$mnu(Event event) throws InterruptedException {

		Operacion operacion = null;

		if (event instanceof ForwardEvent) {
			ForwardEvent forwardEvent = (ForwardEvent) event;
			operacion = (Operacion) forwardEvent.getOrigin().getData();
		}

		executeOperation(operacion);
	}

	protected Object getCurrentEntity() {
		return entity;

	}

	protected void doCustomAction(Object entity, Operacion operacion) {

		

			String nombreMetodo = "doCustomAction";

			Object[] args = new Object[] { entity, operacion };
			Class[] types = new Class[] { Object.class, operacion.getClass() };
			
			
			
			executeZKFunction(nombreMetodo, types, args);

		
		
		

	}

	protected boolean canDeleteEntity() {
		boolean isDelete = false;
		int answer = Integer.valueOf(Messagebox.NO);
		answer = Messagebox
				.show("Desea Eliminar los Elementos Seleccionados?",
						"Eliminar", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION);
		switch (answer) {
		case Messagebox.YES:
			isDelete = true;
			break;

		case Messagebox.NO:
			isDelete = false;
			break;
		}

		return isDelete;
	}

	protected void doEditEntity(Object entity) throws InterruptedException {

		try {

			String nombreMetodo = "doEditEntity";

			Object[] args = new Object[] { entity };
			Class[] types = new Class[] { Object.class };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}

	protected void doNewEntity() throws InterruptedException {

		try {

			String nombreMetodo = "doNewEntity";

			Object[] args = new Object[] {};
			Class[] types = new Class[] {};

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}

	}
	
	protected void doCustomActionOperation(Operacion operacion) throws InterruptedException{
		try{
			String nombreMetodo = "doCustomOperation";
			Object[] args = new Object[] { operacion };
			Class[] types = new Class[] {operacion.getClass()};

			executeZKFunction(nombreMetodo, types, args);
			
		}catch (Exception e) {
			showMessage(e.getMessage());
		}
	}

	protected void resetCurrentEntity() {
		setEntity(null);
	}

	protected void doDeleteEntity(Object entity) throws InterruptedException {

		try {

			String nombreMetodo = "doDeleteEntity";

			Object[] args = new Object[] { entity };
			Class[] types = new Class[] { Object.class };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}

		// loadData();
	}

	protected void executeOperation(Operacion operacion) throws InterruptedException {
		OperationType type = com.obelisco.vista.zk.controls.OperacionHelper.getType(operacion);

		if (type == OperationType.MODIFICAR) {
			if (getCurrentEntity() != null) {
				doEditEntity(getCurrentEntity());
			}
		} else if (type == OperationType.INCLUIR) {
			doNewEntity();
		} else if (type == OperationType.ELIMINAR) {
			if (getCurrentEntity() != null) {

				if (canDeleteEntity()) {
					doDeleteEntity(getCurrentEntity());
				}
			}
		} else if (type == OperationType.IMPRIMIR) {
			doPrint();
		} else if (type == OperationType.DEFINIDA_USUARIO) {			
			if (getCurrentEntity() != null) {				
				doCustomAction(getCurrentEntity(), operacion);
			}
		}else if(type == OperationType.CSV){
			doCustomActionOperation(operacion);
		}

	}

	public void doPrint() {
		// ShowFormat.execute(reportName, getEntityList());
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Collection getEntityList() {
		return entityList;
	}

	public void setEntityList(Collection entityList) {
		this.entityList = entityList;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}