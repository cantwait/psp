package com.pdvsa.psp.modbus.io;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class BytesOutputStream extends ByteArrayOutputStream implements	DataOutput {
	private DataOutputStream dataOut;

	public BytesOutputStream(int size) {
		super(size);
		dataOut = new DataOutputStream(this);
	}

	public BytesOutputStream(byte[] buffer) {
		buf = buffer;
		count = 0;
		dataOut = new DataOutputStream(this);
	}
	
	public byte[] getBuffer() {
		return buf;
	}

	public void reset() {
		count = 0;
	}

	public void writeBoolean(boolean value) throws IOException {
		dataOut.writeBoolean(value);
	}

	public void writeByte(int value) throws IOException {
		dataOut.writeByte(value);
	}

	public void writeShort(int value) throws IOException {
		dataOut.writeShort(value);
	}

	public void writeChar(int value) throws IOException {
		dataOut.writeChar(value);
	}

	public void writeInt(int value) throws IOException {
		dataOut.writeInt(value);
	}

	public void writeLong(long value) throws IOException {
		dataOut.writeLong(value);
	}

	public void writeFloat(float value) throws IOException {
		dataOut.writeFloat(value);
	}

	public void writeDouble(double value) throws IOException {
		dataOut.writeDouble(value);
	}

	public void writeBytes(String s) throws IOException {
		int len = s.length();
		for (int i = 0; i < len; i++) {
			this.write((byte) s.charAt(i));
		}
	}

	public void writeChars(String s) throws IOException {
		dataOut.writeChars(s);
	}

	public void writeUTF(String str) throws IOException {
		dataOut.writeUTF(str);
	}

}
