package com.obelisco.vista.zk.command;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;

import com.obelisco.vista.zk.controls.ActionType;

public class ShowCommand implements IObeliscoCommand {

	protected String archivoZul;
	protected String codigoZul;
	private Map parametros = new HashMap();
	protected Window window = null;

	// private boolean modal = true;

	protected Window getWindow() {
		String winFile = "/WEB-INF/templates/window.zul";

		Window window = (Window) Executions.createComponents(winFile, null,	parametros);

		return window;
	}

	protected boolean checkExist(String resource) {
		boolean exist = false;

		Execution exec = Executions.getCurrent();

		try {
			Execution exe = Executions.getCurrent();
			String fileName = exec.getDesktop().getWebApp().getRealPath(resource);
			File file = new File(fileName);
			exist = file.exists();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exist;
	}

	 @Override
	public Object execute() throws Exception {
		Component component = null;
		
		String executeCode = getCodigoZul(); 

		if (executeCode != null) {

			component = Executions.createComponentsDirectly(executeCode,"zul", null, parametros);

		} else {

			String executeFile = getArchivoZul();

			if (checkExist(executeFile)) {
				
				component = Executions.createComponents(executeFile, null, parametros);
			}

		}
		
		

		if (component == null) {
			component = getWindow();
		}

		if (component instanceof Window) {
			window = (Window) component;
		} else {
			window = getWindow();
			component.setParent(window);
		}
		
		
		
		

		window.doModal();
//		window.doHighlighted();

		return null;
	}

	@Override
	public Object execute(Component parent) throws Exception {

		if (parent != null) {
			if (parent instanceof Panel) {
				Panel panel = (Panel) parent;
				panel.getPanelchildren().getChildren().clear();

				Component component = null;

				if (getCodigoZul() != null) {

					component = Executions.createComponentsDirectly(getCodigoZul(), "zul", panel.getPanelchildren(), parametros);

				} else {

					String executeFile = getArchivoZul();

					if (checkExist(executeFile)) {
						component = Executions.createComponents(getArchivoZul(), panel.getPanelchildren(),	parametros);

					}

				}

				if (component == null) {
					component = getWindow();
					component.setParent(panel.getPanelchildren());
				}

			} else {
				parent.getChildren().clear();

				Component component = null;

				if (getCodigoZul() != null) {
					component = Executions.createComponentsDirectly(getCodigoZul(), "zul", parent, parametros);

				} else {
					
					String executeFile = getArchivoZul();
					if (checkExist(executeFile)) {
						component = Executions.createComponents(getArchivoZul(), parent, parametros);
					}

				}

				if (component == null) {
					component = getWindow();
					component.setParent(parent);
				}

			}

		} else {
			return this.execute();
		}

		return null;
	}

	public String getArchivoZul() {
		return archivoZul;
	}

	public void setArchivoZul(String archivoZul) {
		this.archivoZul = archivoZul;
	}

	public Map getParametros() {
		return parametros;
	}

	public void setParametros(Map parametros) {
		this.parametros = parametros;
	}

	public String getCodigoZul() {
		return codigoZul;
	}

	public void setCodigoZul(String codigoZul) {
		this.codigoZul = codigoZul;
	}

	
}
