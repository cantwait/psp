package com.pdvsa.psp.modbus;

public class ModbusIOException extends ModbusException {
	private static final long serialVersionUID = 559224369279126117L;
	private boolean eof = false; // true si es causado por el fin del stream.

	public ModbusIOException() {
	}
	
	public ModbusIOException(String message) {
		super(message);
	}

	public ModbusIOException(boolean eof) {
		this.eof = eof;
	}

	public ModbusIOException(String message, boolean eof) {
		super(message);
		this.eof = eof;
	}

	public boolean isEOF() {
		return eof;
	}

	public void setEOF(boolean eof) {
		this.eof = eof;
	}

}
