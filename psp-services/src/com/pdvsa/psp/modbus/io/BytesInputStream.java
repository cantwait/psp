package com.pdvsa.psp.modbus.io;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

public class BytesInputStream extends ByteArrayInputStream implements DataInput {
	DataInputStream dataIn;

	public BytesInputStream(int size) {
		super(new byte[size]);
		dataIn = new DataInputStream(this);
	}

	public BytesInputStream(byte[] data) {
		super(data);
		dataIn = new DataInputStream(this);
	}

	public void reset(byte[] data) {
		pos = 0;
		mark = 0;
		buf = data;
		count = data.length;
	}

	public void reset(byte[] data, int length) {
		pos = 0;
		mark = 0;
		count = length;
		buf = data;
		readlimit = -1;
	}

	public void reset(int length) {
		pos = 0;
		count = length;
	}

	public int skip(int n) {
		mark(pos);
		pos += n;
		return n;
	}

	public byte[] getBuffer() {
		return buf;
	}

	public int getBufferLength() {
		return buf.length;
	}

	public void readFully(byte b[]) throws IOException {
		dataIn.readFully(b);
	}

	public void readFully(byte b[], int off, int len) throws IOException {
		dataIn.readFully(b, off, len);
	}

	public int skipBytes(int n) throws IOException {
		return dataIn.skipBytes(n);
	}

	public boolean readBoolean() throws IOException {
		return dataIn.readBoolean();
	}

	public byte readByte() throws IOException {
		return dataIn.readByte();
	}

	public int readUnsignedByte() throws IOException {
		return dataIn.readUnsignedByte();
	}

	public short readShort() throws IOException {
		return dataIn.readShort();
	}

	public int readUnsignedShort() throws IOException {
		return dataIn.readUnsignedShort();
	}

	public char readChar() throws IOException {
		return dataIn.readChar();
	}

	public int readInt() throws IOException {
		return dataIn.readInt();
	}

	public long readLong() throws IOException {
		return dataIn.readLong();
	}

	public float readFloat() throws IOException {
		return dataIn.readFloat();
	}

	public double readDouble() throws IOException {
		return dataIn.readDouble();
	}

	public String readLine() throws IOException {
		throw new IOException("No soportado.");
	}

	public String readUTF() throws IOException {
		return dataIn.readUTF();
	}

}

