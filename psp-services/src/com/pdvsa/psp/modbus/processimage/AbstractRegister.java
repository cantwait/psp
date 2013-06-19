package com.pdvsa.psp.modbus.processimage;

public abstract class AbstractRegister implements Register {

	protected byte[] register = new byte[2];

	public int getValue() {
		return ((register[0] & 0xff) << 8 | (register[1] & 0xff));
	}

	public final int toUnsignedShort() {
		return ((register[0] & 0xff) << 8 | (register[1] & 0xff));
	}

	public final void setValue(int value) {
		register[0] = (byte) (0xff & (value >> 8));
		register[1] = (byte) (0xff & value);
	}

	public final short toShort() {
		return (short) ((register[0] << 8) | (register[1] & 0xff));
	}

	public final void setValue(short shortValue) {
		register[0] = (byte) (0xff & (shortValue >> 8));
		register[1] = (byte) (0xff & shortValue);
	}

	public byte[] toBytes() {
		return register;
	}

	public final void setValue(byte[] bytes) {
		if (bytes.length < 2) {
			throw new IllegalArgumentException();
		} else {
			register[0] = bytes[0];
			register[1] = bytes[1];
		}
	}

}
