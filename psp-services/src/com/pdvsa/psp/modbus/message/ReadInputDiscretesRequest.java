package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.processimage.Discrete;
import com.pdvsa.psp.modbus.processimage.IllegalAddressException;
import com.pdvsa.psp.modbus.processimage.ProcessImage;

public final class ReadInputDiscretesRequest extends ModbusRequest {
	private int reference;
	private int bitCount;

	public ReadInputDiscretesRequest() {
		super();
		setFunctionCode(Modbus.READ_INPUT_DISCRETES);
		setDataLength(4);
	}

	public ReadInputDiscretesRequest(int reference, int bitCount) {
		this();
		this.reference = reference;
		this.bitCount = bitCount;
	}
	
	public void setReference(int reference) {
		this.reference = reference;
	}

	public int getReference() {
		return reference;
	}
	
	public void setBitCount(int bitCount) {
		this.bitCount = bitCount;
	}

	public int getBitCount() {
		return bitCount;
	}

	public void writeData(DataOutput dataOut) throws IOException {
		dataOut.writeShort(reference);
		dataOut.writeShort(bitCount);
	}

	public void readData(DataInput dataIn) throws IOException {
		reference = dataIn.readUnsignedShort();
		bitCount = dataIn.readUnsignedShort();
	}
	
	public ModbusResponse createResponse() {
		Discrete[] digitalIn = null;
		// Leer y buscar en la tabla de mapping el rango de registros 
		ProcessImage pi = ModbusSlave.getReference().getProcessImage(getUnitID());
		try {
			digitalIn = pi.getDiscreteRange(getReference(), getBitCount());
		} catch (IllegalAddressException iaex) {
			return createExceptionResponse(Modbus.ILLEGAL_ADDRESS_EXCEPTION);
		}
		ReadInputDiscretesResponse response = new ReadInputDiscretesResponse(digitalIn.length);
		response.setTransactionID(this.getTransactionID());
		response.setProtocolID(this.getProtocolID());
		response.setUnitID(this.getUnitID());
		response.setFunctionCode(this.getFunctionCode());
		for (int i = 0; i < digitalIn.length; i++) {
			response.setDiscreteStatus(i, digitalIn[i].isSet());
		}
		return response;
	}	
	
}
