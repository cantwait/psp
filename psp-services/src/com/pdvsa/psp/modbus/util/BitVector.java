package com.pdvsa.psp.modbus.util;

public final class BitVector {
	private int size;
	private byte[] data;
	private boolean accessMSB = false;
	private static final int[] ODD_OFFSETS = { -1, -3, -5, -7 };
	private static final int[] STRAIGHT_OFFSETS = { 7, 5, 3, 1 };

	public BitVector(int size) {
		this.size = size;
		if ((size % 8) > 0) {
			size = (size / 8) + 1;
		} else {
			size = (size / 8);
		}
		data = new byte[size];
	}
	
	public void toggleAccess(boolean b) {
		accessMSB = !accessMSB;
	}
	
	public boolean isLSBAccess() {
		return !accessMSB;
	}
	
	public boolean isMSBAccess() {
		return accessMSB;
	}
	
	public final byte[] getBytes() {
		return data;
	}
	
	public final void setBytes(byte[] data) {
		System.arraycopy(data, 0, this.data, 0, data.length);
	}
	
	public final void setBytes(byte[] data, int size) {
		System.arraycopy(data, 0, this.data, 0, data.length);
		this.size = size;
	}
	
	public final boolean getBit(int index) throws IndexOutOfBoundsException {
		index = translateIndex(index);
		return ((data[byteIndex(index)] & (0x01 << bitIndex(index))) != 0) ? true	: false;
	}
	
	public final void setBit(int index, boolean b)	throws IndexOutOfBoundsException {
		index = translateIndex(index);
		int value = ((b) ? 1 : 0);
		int byteNum = byteIndex(index);
		int bitNum = bitIndex(index);
		data[byteNum] = (byte) ((data[byteNum] & ~(0x01 << bitNum)) | ((value & 0x01) << bitNum));
	}
	
	public final int size() {
		return size;
	}
	
	public final void forceSize(int size) {
		if (size > data.length * 8) {
			throw new IllegalArgumentException("Size exceeds byte[] store.");
		} else {
			this.size = size;
		}
	}
	
	public final int byteSize() {
		return data.length;
	}
	
	public String toString() {
		StringBuffer sbuf = new StringBuffer();
		for (int i = 0; i < size(); i++) {
			int idx = doTranslateIndex(i);
			sbuf.append(((((data[byteIndex(idx)] & (0x01 << bitIndex(idx))) != 0) ? true : false) ? '1' : '0'));
			if (((i + 1) % 8) == 0) {
				sbuf.append(" ");
			}
		}
		return sbuf.toString();
	}
	
	private final int byteIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= data.length * 8) {
			throw new IndexOutOfBoundsException();
		} else {
			return index / 8;
		}
	}
	
	private final int bitIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= data.length * 8) {
			throw new IndexOutOfBoundsException();
		} else {
			return index % 8;
		}
	}

	private final int translateIndex(int idx) {
		if (accessMSB) {
			int mod4 = idx % 4;
			int div4 = idx / 4;
			if ((div4 % 2) != 0) {
				return (idx + ODD_OFFSETS[mod4]);
			} else {
				return (idx + STRAIGHT_OFFSETS[mod4]);
			}
		} else {
			return idx;
		}
	}

	private static final int doTranslateIndex(int idx) {
		int mod4 = idx % 4;
		int div4 = idx / 4;
		if ((div4 % 2) != 0) {
			return (idx + ODD_OFFSETS[mod4]);
		} else {
			return (idx + STRAIGHT_OFFSETS[mod4]);
		}
	}
	
	public static BitVector createBitVector(byte[] data, int size) {
		BitVector bv = new BitVector(data.length * 8);
		bv.setBytes(data);
		bv.size = size;
		return bv;
	}
	
	public static BitVector createBitVector(byte[] data) {
		BitVector bv = new BitVector(data.length * 8);
		bv.setBytes(data);
		return bv;
	}

	public static void main(String[] args) {
		BitVector test = new BitVector(24);
		System.out.println(test.isLSBAccess());
		test.setBit(7, true);
		System.out.println(test.getBit(7));
		test.toggleAccess(true);
		System.out.println(test.getBit(7));

		test.toggleAccess(true);
		test.setBit(6, true);
		test.setBit(3, true);
		test.setBit(2, true);

		test.setBit(0, true);
		test.setBit(8, true);
		test.setBit(10, true);

		System.out.println(test);
		test.toggleAccess(true);
		System.out.println(test);
		test.toggleAccess(true);
		System.out.println(test);

		System.out.println(ModbusUtil.toHex(test.getBytes()));
	}

}
