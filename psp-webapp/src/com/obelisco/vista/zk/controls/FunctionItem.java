package com.obelisco.vista.zk.controls;


import com.pdvsa.psp.model.Operacion;

public class FunctionItem extends org.zkoss.zul.Menuitem {

	private static final long serialVersionUID = 7384227865527674059L;
	private Operacion operacion;

	private void initControl() {		
		setLabel(operacion.getNombre());
		setTooltip(operacion.getNombre());

	}

	public FunctionItem(Operacion operacion) {
		super();
		this.operacion = operacion;
		initControl();

	}

	public Operacion getOperacion() {
		return operacion;
	}

}
