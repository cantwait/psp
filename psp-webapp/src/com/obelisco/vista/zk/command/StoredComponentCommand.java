package com.obelisco.vista.zk.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;

import com.obelisco.vista.zk.controls.ActionType;

public class StoredComponentCommand extends ShowCommand {

	private String nombrePrograma;
	private boolean dialogo;

	public String getCodigoPrograma() {
		String codigoPrograma = null;

		return codigoPrograma;
	}

	@Override
	public String getCodigoZul() {
		// TODO Auto-generated method stub
		return this.getCodigoPrograma();
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	@Override
	public Object execute() throws Exception {
		ActionType action = ActionType.NINGUNA;

		if (isDialogo()) {

			super.execute();
			for (Entry e : window.getAttributes().entrySet()) {
				System.out.println(e.getKey() + " = " + e.getValue());
			}
			action = (ActionType) window.getAttribute("MODAL_VALUE");
			System.out.println("Action: " +action);
			if (action == null) {
				action = ActionType.NINGUNA;
			}

			return action;

		} else {
			return super.execute();
		}
	}

	@Override
	public Object execute(Component parent) throws Exception {
		if (isDialogo()) {
			return this.execute();
		} else {
			return super.execute(parent);
		}
	}

	public Object getReturnValue(String name) {
		return window.getAttribute(name);
	}

	public boolean isDialogo() {
		return dialogo;
	}

	public void setDialogo(boolean dialogo) {
		this.dialogo = dialogo;
	}

}
