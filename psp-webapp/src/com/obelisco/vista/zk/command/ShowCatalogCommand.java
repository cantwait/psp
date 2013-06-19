package com.obelisco.vista.zk.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;

import com.obelisco.vista.zk.controls.ActionType;

public class ShowCatalogCommand extends ShowDialogCommand {
	
	private Collection selected = new ArrayList(); 
	
	@Override
	public Object execute(Component parent) throws Exception {
		Object result = super.execute(parent);
		selected = (Collection) window.getAttribute("CATALOG_RESULT");
		return result;
	}

	public Collection getSelected() {
		return selected;
	}

	
}
