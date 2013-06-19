package com.pdvsa.psp.modbus.processimage;

public interface ProcessImageImplementation extends ProcessImage {

	public void setCoil(int ref, Coil coil) throws IllegalAddressException;

	public void addCoil(Coil coil);

	public void removeCoil(Coil coil);

	public void setDiscrete(int ref, Discrete di) throws IllegalAddressException;

	public void addDiscrete(Discrete di);

	public void removeDiscrete(Discrete di);

	public void setInputRegister(int ref, InputRegister reg) throws IllegalAddressException;

	public void addInputRegister(InputRegister reg);

	public void removeInputRegister(InputRegister reg);

	public void setRegister(int ref, Register reg) throws IllegalAddressException;

	public void addRegister(Register reg);

	public void removeRegister(Register reg);
	
	public void clearImages();

	public static final byte DIG_TRUE = 1;

	public static final byte DIG_FALSE = 0;

	public static final byte DIG_INVALID = -1;

}
