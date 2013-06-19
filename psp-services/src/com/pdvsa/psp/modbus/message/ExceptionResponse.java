package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;

public class ExceptionResponse extends ModbusResponse {
	private int exceptionCode = -1;

	public ExceptionResponse() {
		setDataLength(1);
	}

	public ExceptionResponse(int functionCode) {
		setDataLength(1);
		setFunctionCode(functionCode + Modbus.EXCEPTION_OFFSET);
	}

	public ExceptionResponse(int functionCode, int exceptionCode) {
		setDataLength(1);
		setFunctionCode(functionCode + Modbus.EXCEPTION_OFFSET);
		this.exceptionCode = exceptionCode;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void writeData(DataOutput dataOut) throws IOException {
		dataOut.writeByte(getExceptionCode());
	}

	public void readData(DataInput dataIn) throws IOException {
		exceptionCode = dataIn.readUnsignedByte();
	}

}
