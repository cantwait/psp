package com.pdvsa.psp.modbus.processimage;

public interface ProcessImageFactory {

	public ProcessImageImplementation createProcessImageImplementation();

	public Discrete createDiscrete();

	public Discrete createDiscrete(boolean state);

	public Coil createCoil();

	public Coil createCoil(boolean b);

	public InputRegister createInputRegister();

	public InputRegister createInputRegister(byte b1, byte b2);

	public Register createRegister();

	public Register createRegister(byte b1, byte b2);

}