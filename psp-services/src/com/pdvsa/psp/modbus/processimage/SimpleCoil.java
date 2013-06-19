package com.pdvsa.psp.modbus.processimage;

public class SimpleCoil implements Coil {
	protected boolean bSet;

	public SimpleCoil() {
	}
	
	public SimpleCoil(boolean b) {
		set(b);
	}

	public boolean isSet() {
		return bSet;
	}

	public synchronized void set(boolean b) {
		bSet = b;
	}

}
