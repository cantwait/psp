package com.pdvsa.psp.modbus.processimage;

import com.pdvsa.psp.modbus.util.Observable;

public class ObservableCoil extends Observable implements Coil {
	protected boolean bvalue;

	public boolean isSet() {
		return this.bvalue;
	}

	public void set(boolean b) {
		bvalue = b;
		notifyObservers("value");
	}
}
