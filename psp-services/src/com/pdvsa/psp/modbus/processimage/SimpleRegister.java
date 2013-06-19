package com.pdvsa.psp.modbus.processimage;

public class SimpleRegister extends SynchronizedAbstractRegister implements	Register {
	
	public SimpleRegister() {
		register = null;
	}
	
	public SimpleRegister(byte b1, byte b2) {
		register[0] = b1;
		register[1] = b2;
	}
	
	public SimpleRegister(int value) {
		setValue(value);
	}

}
