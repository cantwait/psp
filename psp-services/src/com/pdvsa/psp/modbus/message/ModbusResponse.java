package com.pdvsa.psp.modbus.message;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import com.pdvsa.psp.modbus.Modbus;

public abstract class ModbusResponse extends ModbusMessageImpl {

	protected void setMessage(byte[] msg) {
		try {
			readData(new DataInputStream(new ByteArrayInputStream(msg)));
		} catch (IOException ex) {}
	}
	
	public static ModbusResponse createModbusResponse(int functionCode) {
		ModbusResponse response = null;
		switch (functionCode) {
			case Modbus.READ_MULTIPLE_REGISTERS:
				response = new ReadMultipleRegistersResponse();
				break;
			case Modbus.READ_INPUT_REGISTERS:
				response = new ReadInputRegistersResponse();
				break;
			case Modbus.READ_INPUT_DISCRETES:
				// TODO primera fase del proyecto no soporta variables DISCRETES
				response = new ExceptionResponse();
				break;				
			case Modbus.READ_COILS:
				// TODO primera fase del proyecto no soporta variables COILS
				response = new ExceptionResponse();
				break;
			case Modbus.WRITE_MULTIPLE_REGISTERS:
				// TODO primera fase del proyecto no soporta escritura en variables 
				response = new ExceptionResponse();
				break;
			case Modbus.WRITE_SINGLE_REGISTER:
				// TODO primera fase del proyecto no soporta escritura en variables 
				response = new ExceptionResponse();
				break;
			case Modbus.WRITE_COIL:
				// TODO primera fase del proyecto no soporta escritura en variables 
				response = new ExceptionResponse();
				break;
			case Modbus.WRITE_MULTIPLE_COILS:
				// TODO primera fase del proyecto no soporta escritura en variables 
				response = new ExceptionResponse();
				break;
			default:
				response = new ExceptionResponse();
				break;
		}
		return response;
	}

}
