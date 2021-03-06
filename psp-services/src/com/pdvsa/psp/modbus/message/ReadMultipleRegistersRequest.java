package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.processimage.IllegalAddressException;
import com.pdvsa.psp.modbus.processimage.ProcessImage;
import com.pdvsa.psp.modbus.processimage.Register;

public final class ReadMultipleRegistersRequest extends ModbusRequest {
	private int reference;
	private int wordCount;

	public ReadMultipleRegistersRequest() {
		super();
		setFunctionCode(Modbus.READ_MULTIPLE_REGISTERS);
		// Se establece el tamaño del area de datos en 4 bytes ya que el Unit ID y el Function Code se descartan
		setDataLength(4);
	}

	public ReadMultipleRegistersRequest(int reference, int wordCount) {
		this();
		this.reference = reference;
		this.wordCount = wordCount;
	}
	
	public void setReference(int reference) {
		this.reference = reference;
	}
	
	public int getReference() {
		return reference;
	}
	
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public int getWordCount() {
		return wordCount;
	}	
	
	public void readData(DataInput dataIn) throws IOException {
		reference = dataIn.readUnsignedShort();
		wordCount = dataIn.readUnsignedShort();
	}	
	
	public void writeData(DataOutput dataOut) throws IOException {
		dataOut.writeShort(reference);
		dataOut.writeShort(wordCount);
	}	

	public ModbusResponse createResponse() {
		Register[] regs = null;
		// Leer y buscar en la tabla de mapping el rango de registros 
		ProcessImage pi = ModbusSlave.getReference().getProcessImage(getUnitID());
		try {
			regs = pi.getRegisterRange(getReference(), getWordCount());
		} catch (IllegalAddressException iaex) {
			return createExceptionResponse(Modbus.ILLEGAL_ADDRESS_EXCEPTION);
		}
		ReadMultipleRegistersResponse response = new ReadMultipleRegistersResponse(regs);
		response.setTransactionID(this.getTransactionID());
		response.setProtocolID(this.getProtocolID());
		response.setUnitID(this.getUnitID());
		response.setFunctionCode(this.getFunctionCode());
		return response;
	}
	
}
