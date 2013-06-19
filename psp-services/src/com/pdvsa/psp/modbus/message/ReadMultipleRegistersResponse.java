package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.pdvsa.psp.modbus.processimage.ProcessImageFactory;
import com.pdvsa.psp.modbus.processimage.Register;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.Modbus;

public final class ReadMultipleRegistersResponse extends ModbusResponse {
	private int byteCount;
	private Register[] registers;

	public ReadMultipleRegistersResponse() {
		super();
		setFunctionCode(Modbus.READ_MULTIPLE_REGISTERS);
	}

	public ReadMultipleRegistersResponse(Register[] registers) {
		super();
		this.registers = registers;
		byteCount = registers.length * 2;
		setFunctionCode(Modbus.READ_MULTIPLE_REGISTERS);
		// Se establece el tamaño del area de datos, dependiendo de la cantidad de registros, el Unit ID y el Function Code se descartan
		setDataLength(byteCount + 1);
	}

	public int getByteCount() {
		return byteCount;
	}

	public int getWordCount() {
		return byteCount / 2;
	}

	private void setByteCount(int byteCount) {
		this.byteCount = byteCount;
	}

	public int getRegisterValue(int index) throws IndexOutOfBoundsException {
		return registers[index].toUnsignedShort();
	}

	public Register getRegister(int index) throws IndexOutOfBoundsException {
		if (index >= getWordCount()) {
			throw new IndexOutOfBoundsException();
		} else {
			return registers[index];
		}
	}

	public Register[] getRegisters() {
		return registers;
	}

	public void writeData(DataOutput dataOut) throws IOException {
		dataOut.writeByte(byteCount);
		for (int k = 0; k < getWordCount(); k++) {
			dataOut.write(registers[k].toBytes());
		}
	}

	public void readData(DataInput dataIn) throws IOException {
		setByteCount(dataIn.readUnsignedByte());
		registers = new Register[getWordCount()];
		ProcessImageFactory pimf = ModbusSlave.getReference().getProcessImageFactory();
		for (int k = 0; k < getWordCount(); k++) {
			registers[k] = pimf.createRegister(dataIn.readByte(), dataIn.readByte());
		}
		setDataLength(getByteCount() + 1);
	}

}
