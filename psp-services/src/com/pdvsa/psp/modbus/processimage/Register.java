package com.pdvsa.psp.modbus.processimage;

public interface Register extends InputRegister {

	public void setValue(int value);

	public void setValue(short shortValue);

	public void setValue(byte[] bytes);

}
