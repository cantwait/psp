package com.pdvsa.psp.modbus.message;

import com.pdvsa.psp.modbus.processimage.InputRegister;
import com.pdvsa.psp.modbus.processimage.ProcessImageFactory;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.Modbus;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class ReadInputRegistersResponse extends ModbusResponse {
	private int byteCount;
	private InputRegister[] registers;

	public ReadInputRegistersResponse() {
		super();
		setFunctionCode(Modbus.READ_INPUT_REGISTERS);
	}
	
	public ReadInputRegistersResponse(InputRegister[] registers) {
		this();
		byteCount = registers.length * 2;
		this.registers = registers;
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
	
	public InputRegister getRegister(int index)	throws IndexOutOfBoundsException {
		if (index >= getWordCount()) {
			throw new IndexOutOfBoundsException();
		} else {
			return registers[index];
		}
	}
	
	public int getRegisterValue(int index) throws IndexOutOfBoundsException {
		if (index >= getWordCount()) {
			throw new IndexOutOfBoundsException();
		} else {
			return registers[index].toUnsignedShort();
		}
	}
	
	public InputRegister[] getRegisters() {
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
		InputRegister[] registers = new InputRegister[getWordCount()];
		ProcessImageFactory pimf = ModbusSlave.getReference().getProcessImageFactory();
		for (int k = 0; k < getWordCount(); k++) {
			registers[k] = pimf.createInputRegister(dataIn.readByte(), dataIn.readByte());
		}
		this.registers = registers;
		setDataLength(getByteCount() + 1);
	}

}