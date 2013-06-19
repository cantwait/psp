package com.obelisco.vista.zk.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;


import com.obelisco.vista.zk.controls.ActionType;
import com.sun.java.swing.plaf.windows.resources.windows;

public class ShowDialogCommand extends ShowCommand {

	@Override
	public Object execute() throws Exception {
		// TODO Auto-generated method stub
		ActionType action = ActionType.NINGUNA;
		
		
		
		super.execute();

		action = (ActionType) window.getAttribute("MODAL_VALUE");
		
		if (action == null){
			action = ActionType.NINGUNA;
		}
		
		return action;
	}

	@Override
	public Object execute(Component parent) throws Exception {
		return this.execute();
	}
	
	public Object getReturnValue(String name) {
		return window.getAttribute(name);
	}
	
	public Map getReturnCollection(int scope){
		return window.getAttributes(scope);
	}
	
}
