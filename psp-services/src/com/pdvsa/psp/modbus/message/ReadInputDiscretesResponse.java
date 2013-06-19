package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.util.BitVector;
import com.pdvsa.psp.modbus.Modbus;

public final class ReadInputDiscretesResponse extends ModbusResponse {
	private int bitCount;
	private BitVector discretes;

	public ReadInputDiscretesResponse() {
		super();
		setFunctionCode(Modbus.READ_INPUT_DISCRETES);
	}

	public ReadInputDiscretesResponse(int bitCount) {
		super();
		setBitCount(bitCount);
	}
	
	public int getBitCount() {
		return bitCount;
	}
	
	public void setBitCount(int bitCount) {
		this.bitCount = bitCount;
		discretes = new BitVector(bitCount);
		setDataLength(discretes.byteSize() + 1);
	}

	public BitVector getDiscretes() {
		return discretes;
	}

	public boolean getDiscreteStatus(int index)	throws IndexOutOfBoundsException {
		return discretes.getBit(index);
	}
	
	public void setDiscreteStatus(int index, boolean value) throws IndexOutOfBoundsException {
		discretes.setBit(index, value);
	}

	public void writeData(DataOutput dataOut) throws IOException {
		dataOut.writeByte(discretes.byteSize());
		dataOut.write(discretes.getBytes(), 0, discretes.byteSize());
	}

	public void readData(DataInput dataIn) throws IOException {
		int count = dataIn.readUnsignedByte();
		byte[] data = new byte[count];
		for (int k = 0; k < count; k++) {
			data[k] = dataIn.readByte();
		}
		discretes = BitVector.createBitVector(data);
		setDataLength(count + 1);
	}

}
