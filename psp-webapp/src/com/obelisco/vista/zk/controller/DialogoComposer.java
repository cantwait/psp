package com.obelisco.vista.zk.controller;

import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.Binding;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;

import com.obelisco.vista.zk.controls.Menubar;

public class DialogoComposer extends GenericForwardComposer {

	private Button btnAceptar, btnCancelar;

	public void onClick$btnAceptar(Event event) {
		self.detach();
		
	}
	

	public void onClick$btnCancelar(Event event) {
		self.detach();
	}
	
	

}
