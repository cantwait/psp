package com.obelisco.vista.zk.components;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Tree;

import com.obelisco.exception.ObeliscoException;
import com.obelisco.modelo.data.seguridad.Operacion;
import com.obelisco.vista.zk.command.ObjectCommand;
import com.obelisco.vista.zk.command.ShowObjectCommand;
import com.obelisco.vista.zk.controls.Menubar;
import com.obelisco.vista.zk.controls.OperacionHelper;
import com.obelisco.vista.zk.controls.Toolbar;
import com.obelisco.vista.zk.controls.OperationType;

public class GenericTreeWindow extends GenericWindow implements AfterCompose {

	protected Tree tree;

	protected Toolbar tool;

	protected Menubar mnu;

	protected Component workArea;

	protected boolean executeCustomView = false;

	public GenericTreeWindow() {
		super();
		this.addEventListener("onCreate", createListener);
	}

	protected EventListener createListener = new EventListener() {

		public void onEvent(Event event) throws Exception {

		}

	};

	// @Override
	public void afterCompose() {
		ConventionWires.wireVariables(this, this);
		// auto forward
		ConventionWires.addForwards(this, this);
	}

	protected void viewObject(Object object) {
		try {

			if (!executeCustomView) {
				ObjectCommand command = new ObjectCommand();
				command.setObjeto(object);
				command.execute(workArea);
			} else {
				doShowEntity(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onSelectNode$tree(Event event) {
		if (event instanceof ForwardEvent) {
			ForwardEvent forwardEvent = (ForwardEvent) event;
			Object o = forwardEvent.getOrigin().getData();
			if (o != null) {
				viewObject(o);
			}
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
		Object selected = null;
		if (tree.getSelectedItem() != null) {
			selected = tree.getSelectedItem().getValue();
		}
		return selected;

	}

	protected void doCustomAction(Object entity, Operacion operacion) throws InterruptedException {

		try {

			String nombreMetodo = "doCustomAction";

			Object[] args = new Object[] { entity, operacion };
			Class[] types = new Class[] { Object.class, operacion.getClass() };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}

	}

	protected boolean canDeleteEntity() throws InterruptedException {

		boolean isDelete = false;

		isDelete = Messagebox.show(
				"Desea Eliminar los Elementos Seleccionados?", "Eliminar",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES;

		return isDelete;

	}

	protected void doShowEntity(Object entity) throws InterruptedException {

		try {

			String nombreMetodo = "doShowEntity";

			Object[] args = new Object[] { entity };
			Class[] types = new Class[] { Object.class };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}
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

	protected void doNewEntity(Object parent) throws InterruptedException {

		try {

			String nombreMetodo = "doNewEntity";

			Object[] args = new Object[] { parent };
			Class[] types = new Class[] { Object.class };

			executeZKFunction(nombreMetodo, types, args);

		} catch (Exception e) {
			showMessage(e.getMessage());
		}
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

	}

	protected void executeOperation(Operacion operacion) throws InterruptedException {
		OperationType type = OperacionHelper.getType(operacion);

		if (type == OperationType.MODIFICAR) {

			if (getCurrentEntity() != null) {
				doEditEntity(getCurrentEntity());
			}

		} else if (type == OperationType.INCLUIR) {

			doNewEntity(getCurrentEntity());

		} else if (type == OperationType.ELIMINAR) {

			if (getCurrentEntity() != null) {

				if (canDeleteEntity()) {

					doDeleteEntity(getCurrentEntity());

				}

			}

		} else if (type == OperationType.IMPRIMIR) {
			showMessage("Funcionalidad NO Implementada");
		} else if (type == OperationType.DEFINIDA_USUARIO) {

			if (getCurrentEntity() != null) {
				doCustomAction(getCurrentEntity(), operacion);
			}

		}

	}

	public boolean isExecuteCustomView() {
		return executeCustomView;
	}

	public void setExecuteCustomView(boolean executeCustomView) {
		this.executeCustomView = executeCustomView;
	}

}
