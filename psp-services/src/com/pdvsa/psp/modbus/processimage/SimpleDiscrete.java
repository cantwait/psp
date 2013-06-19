package com.pdvsa.psp.modbus.processimage;

public class SimpleDiscrete implements Discrete {
	protected boolean bSet = false;

	public SimpleDiscrete() {
	}
	
	public SimpleDiscrete(boolean b) {
		set(b);
	}

	public boolean isSet() {
		return bSet;
	}
	
	public synchronized void set(boolean b) {
		bSet = b;
	}

}
