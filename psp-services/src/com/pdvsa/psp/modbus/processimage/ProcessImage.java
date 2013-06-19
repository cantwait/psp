package com.pdvsa.psp.modbus.processimage;

public interface ProcessImage {

	public Coil[] getCoilRange(int offset, int count) throws IllegalAddressException;

	public Coil getCoil(int ref) throws IllegalAddressException;

	public int getCoilCount();

	public Discrete[] getDiscreteRange(int offset, int count) throws IllegalAddressException;

	public Discrete getDiscrete(int ref) throws IllegalAddressException;

	public int getDiscreteCount();

	public InputRegister[] getInputRegisterRange(int offset, int count)	throws IllegalAddressException;

	public InputRegister getInputRegister(int ref) throws IllegalAddressException;

	public int getInputRegisterCount();

	public Register[] getRegisterRange(int offset, int count) throws IllegalAddressException;

	public Register getRegister(int ref) throws IllegalAddressException;

	public int getRegisterCount();

}
