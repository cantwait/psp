package com.pdvsa.psp.modbus.io;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {
	protected int count;
	protected int pos;
	protected int mark;
	protected byte[] buf;
	protected int readlimit = -1;

	public ByteArrayInputStream(byte[] buffer) {
		buf = buffer;
		count = buf.length;
		pos = 0;
		mark = 0;
	}
	
	public ByteArrayInputStream(byte[] buffer, int offset, int length) {
		buf = buffer;
		pos = offset;
		count = length;
	}

	public int read() throws IOException {
		if ((pos < count)) {
			return (buf[pos++] & 0xff);
		} else {
			return (-1);
		}
	}
	
	public int read(byte[] toBuf, int offset, int length) throws IOException {
		int avail = count - pos;
		if (avail <= 0) {
			return -1;
		}
		if (length > avail) {
			length = avail;
		}
		System.arraycopy(buf, pos, toBuf, offset, length);
		pos += length;
		return length;
	}

	public int read(byte[] toBuf) throws IOException {
		return read(toBuf, 0, toBuf.length);
	}

	public long skip(long n) {
		int skip = this.count - this.pos - (int) n;
		if (skip > 0) {
			pos += skip;
		}
		return skip;
	}
	
	public void close() {
		return;
	}
	
	public int available() {
		return count - pos;
	}
	
	public void mark(int limit) {
		mark = pos;
		readlimit = limit;
	}
	
	public boolean markSupported() {
		return true;
	}
	
	public void reset() throws IOException {
		if (readlimit < 0 || pos > mark + readlimit) {
			pos = mark;
			readlimit = -1;
		} else {
			mark = pos;
			readlimit = -1;
			throw new IOException("Readlimit exceeded.");
		}
	}
	
	public byte[] getBuffer() {
		return buf;
	}
	
	public int getPosition() {
		return pos;
	}
	
	public int size() {
		return count;
	}
	
}
