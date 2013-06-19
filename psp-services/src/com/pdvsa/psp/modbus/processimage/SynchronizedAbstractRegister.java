package com.pdvsa.psp.modbus.processimage;

public abstract class SynchronizedAbstractRegister implements Register {
	protected byte[] register = new byte[2];

	public int getValue() {
		return ((register[0] & 0xff) << 8 | (register[1] & 0xff));
	}

	public final int toUnsignedShort() {
		return ((register[0] & 0xff) << 8 | (register[1] & 0xff));
	}

	public final synchronized void setValue(int v) {
		setValue((short) v);
	}

	public final short toShort() {
		return (short) ((register[0] << 8) | (register[1] & 0xff));
	}

	public final synchronized void setValue(short s) {
		if (register == null) {
			register = new byte[2];
		}
		register[0] = (byte) (0xff & (s >> 8));
		register[1] = (byte) (0xff & s);
	}

	public final synchronized void setValue(byte[] bytes) {
		if (bytes.length < 2) {
			throw new IllegalArgumentException();
		} else {
			register[0] = bytes[0];
			register[1] = bytes[1];
		}
	}

	public byte[] toBytes() {
		return register;
	}

}
