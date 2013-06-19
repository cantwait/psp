package com.pdvsa.psp.modbus.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ByteArrayOutputStream extends OutputStream {
	protected int count;
	protected byte[] buf;
	public static final int DEFAULT_SIZE = 512;

	public ByteArrayOutputStream() {
		buf = new byte[DEFAULT_SIZE];
	}

	public ByteArrayOutputStream(int bufferSize) {
		buf = new byte[bufferSize];
	}

	public ByteArrayOutputStream(byte[] buf) {
		this.buf = buf;
	}


	public void close() {
	}

	public void reset() {
		count = 0;
	}

	public int size() {
		return count;
	}

	public byte[] toByteArray() {
		byte[] buf = new byte[count];
		System.arraycopy(this.buf, 0, buf, 0, count);
		return buf;
	}


	public String toString() {
		return new String(buf, 0, count);
	}

	public String toString(String charsetName) throws UnsupportedEncodingException {
		return new String(buf, 0, count, charsetName);
	}

	public void write(byte[] b, int off, int len) {
		ensureCapacity(count + len);
		System.arraycopy(b, off, buf, count, len);
		count += len;
	}

	public void write(int b) {
		ensureCapacity(count + 1);
		buf[count++] = (byte) b;
	}

	public void writeTo(OutputStream out) throws IOException {
		out.write(buf, 0, count);
	}

	public void write(byte[] buf) throws IOException {
		write(buf, 0, buf.length);
	}

	public final void ensureCapacity(int minCapacity) {
		if (minCapacity < buf.length) {
			return;
		} else {
			byte[] newbuf = new byte[minCapacity];
			System.arraycopy(buf, 0, newbuf, 0, count);
			buf = newbuf;
		}
	}

	public void toByteArray(byte[] b, int offset) {
		if (offset >= b.length) {
			throw new IndexOutOfBoundsException();
		}
		int len = count - offset;
		if (len > b.length) {
			System.arraycopy(buf, offset, b, offset, b.length);
		} else {
			System.arraycopy(buf, offset, b, offset, len);
		}
	}

	public byte[] getBuffer() {
		return buf;
	}

}
