package com.pdvsa.psp.modbus.processimage;

public interface InputRegister {

	public int getValue();

	public int toUnsignedShort();

	public short toShort();

	public byte[] toBytes();

}
