package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;

public class IllegalFunctionRequest extends ModbusRequest {

	public IllegalFunctionRequest(int fc) {
		setFunctionCode(fc);
	}

	public ModbusResponse createResponse() {
		return this.createExceptionResponse(Modbus.ILLEGAL_FUNCTION_EXCEPTION);
	}

	public void writeData(DataOutput dataOut) throws IOException {
		throw new RuntimeException();
	}

	public void readData(DataInput dataIn) throws IOException {
		int length = getDataLength();
		for (int i = 0; i < length; i++) {
			dataIn.readByte();
		}
	}

}
