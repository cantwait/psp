package com.pdvsa.psp.modbus.processimage;

public class SimpleInputRegister extends SynchronizedAbstractRegister implements InputRegister {
	
	public SimpleInputRegister() {
	}
	
	public SimpleInputRegister(byte b1, byte b2) {
		register[0] = b1;
		register[1] = b2;
	}
	
	public SimpleInputRegister(int value) {
		setValue(value);
	}

}
