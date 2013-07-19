package com.obelisco.vista.zk.controls;


import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;

import com.pdvsa.psp.model.Operacion;

public class FunctionImagemap extends Button implements AfterCompose {
	
	
	private static final long serialVersionUID = 6881487192821495408L;
	private Operacion operacion;
	
	private void initControl() {

		setLabel(operacion.getNombre());
		setMold("trendy");
		setStyle("background-color:#fff000;border: 1px solid;	font-size: 9px;");		
		
	}
	
	public FunctionImagemap(Operacion operacion) {
		super();
		this.operacion = operacion;
		initControl();
	}

	public Operacion getOperacion() {
		return operacion;
	}

	@Override
	public void afterCompose() {
		
	}

}
