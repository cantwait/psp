package com.obelisco.vista.zk.components;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;

import com.obelisco.vista.zk.controls.ActionType;

public class GenericDialog extends GenericWindow implements AfterCompose {

	
	private static final long serialVersionUID = 7915506007298613508L;
	private Button btnAceptar, btnCancelar;

	@Override
	public void afterCompose() {
		
		ConventionWires.wireVariables(this, this);
		ConventionWires.addForwards(this, this);
	}

	public void refreshControls() {

		AnnotateDataBinder binder = (AnnotateDataBinder) getPage().getAttribute("binder");
		if (binder != null) {
			binder.loadAll();
		}

		AnnotateDataBinder binderLocal = (AnnotateDataBinder) getAttribute("binder", true);
		if (binderLocal != null) {
			binderLocal.loadAll();
		}

	}

	public void confirmData() throws Exception {

		String nombreMetodo = "doConfirmData";

		Object[] args = new Object[] {};
		Class[] types = new Class[] {};

		executeZKFunction(nombreMetodo, types, args);
	}

	public void setReturnData() throws Exception {

		String nombreMetodo = "doSetReturnData";

		Object[] args = new Object[] {};
		Class[] types = new Class[] {};

		executeZKFunction(nombreMetodo, types, args);

	}

	public boolean checkData() throws Exception {
		// Se llama a un metodo de Validacion que esta en el Dialogo

		boolean isValid = false;

		String nombreMetodo = "doValidate";

		Object[] args = new Object[] {};
		Class[] types = new Class[] {};

		Boolean retorno = (Boolean) executeZKFunction(nombreMetodo, types, args);
		if (retorno != null) {

			isValid = retorno.booleanValue();

		}

		return isValid;
	}

	public void onClick$btnAceptar(Event event) throws InterruptedException {
		try {

			if (checkData()) {
				setReturnData();
				confirmData();
				event.getTarget().setAttribute("MODAL_VALUE", ActionType.ACEPTAR);
//				setAttribute("MODAL_VALUE", ActionType.ACEPTAR);
				detach();
			} else {
				showMessage("Por Favor Revise los Datos del Formulario. Imposible Confirmar la Accion");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showMessage(e.getMessage());
		}
	}

	public void onClick$btnCancelar(Event event) {
		setAttribute("MODAL_VALUE", ActionType.CANCELAR);
		detach();
	}

}
