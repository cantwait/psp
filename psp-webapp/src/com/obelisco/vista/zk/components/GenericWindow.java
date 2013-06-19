package com.obelisco.vista.zk.components;

import org.zkoss.xel.Function;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.obelisco.exception.ObeliscoException;

import bsh.TargetError;

public class GenericWindow extends Window {

	protected Object executeZKFunction(String functionName, Class[] types,
			Object[] args) throws ObeliscoException {

		Object result = null;
		Function function = getPage().getZScriptFunction(this, functionName, types);
		
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

	protected void showMessage(String mensaje) throws InterruptedException {

		Messagebox.show(mensaje);

	}

}
