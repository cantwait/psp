package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;
import com.pdvsa.psp.modbus.util.ModbusUtil;

public abstract class ModbusMessageImpl implements ModbusMessage {
	private int transactionID = Modbus.DEFAULT_TRANSACTION_ID;
	private int protocolID = Modbus.DEFAULT_PROTOCOL_ID;
	private int dataLength;
	private int unitID = Modbus.DEFAULT_UNIT_ID;
	private int functionCode;

	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getProtocolID() {
		return protocolID;
	}

	public void setProtocolID(int protocolID) {
		this.protocolID = protocolID;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		// debe ser inferior a 255
		this.dataLength = dataLength + 2;
	}

	public int getUnitID() {
		return unitID;
	}
	
	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public int getFunctionCode() {
		return functionCode;
	}
	
	protected void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}
	
	public void writeTo(DataOutput dataOut) throws IOException {
		dataOut.writeShort(getTransactionID());
		dataOut.writeShort(getProtocolID());
		dataOut.writeShort(getDataLength());
		dataOut.writeByte(getUnitID());
		dataOut.writeByte(getFunctionCode());
		writeData(dataOut);
	}

	public abstract void writeData(DataOutput dataOut) throws IOException;

	public void readFrom(DataInput dataIn) throws IOException {
		setTransactionID(dataIn.readUnsignedShort());
		setProtocolID(dataIn.readUnsignedShort());
		this.dataLength = dataIn.readUnsignedShort();
		setUnitID(dataIn.readUnsignedByte());
		setFunctionCode(dataIn.readUnsignedByte());
		readData(dataIn);
	}

	public abstract void readData(DataInput dataIn) throws IOException;

	public int getOutputLength() {
		int l = 2 + getDataLength();
		l = l + 6;
		return l;
	}

	public String toString() {
		return ModbusUtil.toHex(this);
	}

}
