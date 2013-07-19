package com.obelisco.vista.zk.components;

import org.zkoss.xel.Function;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;

import com.obelisco.exception.ObeliscoException;

import bsh.TargetError;


public class GenericPanel extends Panel {

	
	private static final long serialVersionUID = 6003254324021470927L;

	protected void showMessage(String mensaje) {
		
		Messagebox.show(mensaje);
		
	}
	
	protected Object executeZKFunction(String functionName, Class[] types,
			Object[] args) throws ObeliscoException {

		Object result = null;
		Function function = getPage().getZScriptFunction(this, functionName,
				types);

		if (function != null) {

			try {

				result = function.invoke(this, args);

			} catch (TargetError e) {
				e.printStackTrace();

				throw new ObeliscoException(e.getTarget().getMessage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ObeliscoException(e.getMessage());				
			}

		} 

		return result;

	}
	
	
}
