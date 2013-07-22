package com.obelisco.vista.zk.controller;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import com.obelisco.vista.zk.command.ShowCommand;
import com.obelisco.vista.zk.components.GenericList;
import com.pdvsa.psp.model.Operacion;

public class ListComposer extends GenericForwardComposer<GenericList> {

	private static final long serialVersionUID = 5088714388728110730L;

//	private Menubar mnu;
//	
//	private Toolbar tool;
//	
//	private Listbox lstbox;

	public void onSelectFunction$mnu(Event event) {

		Operacion operacion = (Operacion) event.getData();
		
		
		if (operacion != null) {
			
//			Object actual = page.getVariable("actual");
			
			Object actual = getPage().getAttribute("actual");
			
			GenericList list = (GenericList)self;
			
			if (actual != null) {
				
				alert(list.getDialog());
			}
			
		}
		

	}
	
	public void onSelectFunction$tool(Event event) {
		
		ShowCommand command = new ShowCommand();
		
		GenericList list = (GenericList)self;
		command.setArchivoZul(list.getDialog());
		
/*		
		Operacion operacion = (Operacion) event.getData();
		
		
		if (operacion != null) {
			
			Object actual = page.getVariable("actual");
			
			
			
			if (actual != null) {
				alert(list.getDialogfile());
			}
			
		}
*/		

	}

	
	

}
