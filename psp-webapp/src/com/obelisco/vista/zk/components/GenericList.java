package com.obelisco.vista.zk.components;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import com.obelisco.vista.zk.command.ShowDialogCommand;
import com.obelisco.vista.zk.controls.ActionType;
import com.obelisco.vista.zk.controls.Menubar;
import com.obelisco.vista.zk.controls.OperacionHelper;
import com.obelisco.vista.zk.controls.OperationType;
import com.obelisco.vista.zk.controls.Toolbar;
import com.pdvsa.psp.model.Operacion;

public class GenericList extends GenericPanel implements AfterCompose {

	private String dialog;

	private String entityClass;

	private String entityVariableName;

	private Object entity;
	
	private String reportName;

	private Collection entityList;

	protected Menubar mnu;

	protected Toolbar tool;

	protected Listbox lstbox;

	protected EventListener onSelectFunction = new EventListener() {

		//@Override
		public void onEvent(Event event) throws Exception {
			handlerOnSelectFunction(event);
		}

	};

	//@Override
	public void afterCompose() {

		// Components.wireVariables(this, this);

		// Components.addForwards(this, this);
		
		
		Toolbar toolbar = null;
		for (int i = 10; i > 0; i--) {

			String toolName = "tool" + String.valueOf(i);
			toolbar = (Toolbar) this.getPanelchildren().getFellowIfAny(
					toolName);
			if (toolbar != null) {
				break;
			}

		}
		
		if (toolbar != null) {
			tool = toolbar;
		} else {
			tool = (Toolbar) this.getPanelchildren().getFellowIfAny(
			"tool");
		}

		if (tool != null) {
			tool.addEventListener("onSelectFunction", onSelectFunction);
			tool.createFunctions(OperacionHelper.getOperacionesCatalogo());
		}


		Menubar menubar = null;
		for (int i = 10; i > 0; i--) {

			String mnuName = "mnu" + String.valueOf(i);
			menubar = (Menubar) this.getPanelchildren().getFellowIfAny(
					mnuName);
			if (menubar != null) {
				break;
			}

		}
		
		if (menubar != null) {
			mnu = menubar;
		} else {
			mnu = (Menubar) this.getPanelchildren().getFellowIfAny(
			"mnu");
		}

		if (mnu != null) {
			mnu.addEventListener("onSelectFunction", onSelectFunction);
			//mnu.createFunctions(OperacionHelper.getOperacionesCatalogo());
		}
	
	
	}
	

	protected ActionType showDialog(Operacion operacion, Object entity) {

		ActionType action = ActionType.NINGUNA;

		try {
			ShowDialogCommand command = new ShowDialogCommand();

			command.getParametros().put("aEntity", entity);
			command.getParametros().put("aOperacion", operacion);
			command.setArchivoZul(getDialog());

			action = (ActionType) command.execute(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return action;

	}

	protected boolean canDeleteEntity() {

		boolean isDelete = false;

		isDelete = Messagebox.show(
				"Desea Eliminar los Elementos Seleccionados?", "Eliminar",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES;

		return isDelete;

	}

	protected Object getCurrentEntity() {
		Object actual = getEntity();
		return actual;
	}

	protected void resetCurrentEntity() {
		setEntity(null);
	}

	protected Object getNewEntity() {
		Object entity = null;
		try {
			Class clase = Class.forName(getEntityClass());
			entity = clase.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	public void refreshControls() {
		
		AnnotateDataBinder binder = (AnnotateDataBinder) getPage().getAttribute("binder", true); 
		if (binder != null) {
			binder.loadAll();
		}

		AnnotateDataBinder binderLocal = (AnnotateDataBinder) getAttribute("binder", true);
		if (binderLocal != null) {
			binderLocal.loadAll();
		}

	}

	protected void doDeleteEntity(Object entity) {

		getEntityList().remove(entity);
		resetCurrentEntity();
		refreshControls();

	}

	protected void doNewEntity(Object entity) {

		if (showDialog(OperacionHelper.getOperacion(OperationType.INCLUIR),
				entity) == ActionType.ACEPTAR) {
			getEntityList().add(entity);
			refreshControls();
		}

	}

	protected void doEditEntity(Object entity) {

		if (showDialog(OperacionHelper.getOperacion(OperationType.MODIFICAR),
				entity) == ActionType.ACEPTAR) {
			refreshControls();
		}

	}

	protected void doCustomAction(Object entity, Operacion operacion) {

		// TODO Auto-generated method stub
		try {

			String nombreMetodo = "doCustomAction";

			Object[] args = new Object[] { entity, operacion };
			Class[] types = new Class[] { Object.class, operacion.getClass() };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}

	}

	protected void executeOperation(Operacion operacion) {
		OperationType type = OperacionHelper.getType(operacion);

		if (type == OperationType.MODIFICAR || type == OperationType.INCLUIR) {
			Object current = getNewEntity();

			if (type == OperationType.MODIFICAR) {
				// entity = getCurrentEntity();
				current = getCurrentEntity();
				if (current != null)
					doEditEntity(current);
			} else if (type == OperationType.INCLUIR) {
				doNewEntity(current);
			}

		} else if (type == OperationType.ELIMINAR) {

			if (getCurrentEntity() != null) {

				if (canDeleteEntity()) {

					doDeleteEntity(getCurrentEntity());

				}

			}

		} else if (type == OperationType.DEFINIDA_USUARIO) {

			if (getCurrentEntity() != null) {
				doCustomAction(getCurrentEntity(), operacion);
			}

		}

	}	

	
	public void handlerOnSelectFunction(Event event) {
		Operacion operacion = null;

		if (event instanceof ForwardEvent) {
			ForwardEvent forwardEvent = (ForwardEvent) event;

			operacion = (Operacion) forwardEvent.getOrigin().getData();

		} else {

			operacion = (Operacion) event.getData();
		}

		executeOperation(operacion);
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public Collection getEntityList() {
		return entityList;
	}

	public void setEntityList(Collection entityList) {
		this.entityList = entityList;
	}

	public String getEntityVariableName() {
		return entityVariableName;
	}

	public void setEntityVariableName(String entityVariableName) {
		this.entityVariableName = entityVariableName;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}