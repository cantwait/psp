package com.pdvsa.psp.modbus.message;

import com.pdvsa.psp.modbus.Modbus;

public abstract class ModbusRequest extends ModbusMessageImpl {

	public abstract ModbusResponse createResponse();

	public ModbusResponse createExceptionResponse(int exCode) {
		ExceptionResponse response = new ExceptionResponse(this.getFunctionCode(), exCode);
		response.setTransactionID(this.getTransactionID());
		response.setProtocolID(this.getProtocolID());
		response.setUnitID(this.getUnitID());
		return response;
	}
	
	public static ModbusRequest createModbusRequest(int functionCode) {
		ModbusRequest request = null;
		switch (functionCode) {
		case Modbus.READ_MULTIPLE_REGISTERS:
			request = new ReadMultipleRegistersRequest();
			break;
		case Modbus.READ_INPUT_REGISTERS:
			request = new ReadInputRegistersRequest();
			break;
		case Modbus.READ_INPUT_DISCRETES:
			// TODO primera fase del proyecto no soporta variables DISCRETES
			request = new IllegalFunctionRequest(functionCode);
			break;			
		case Modbus.READ_COILS:
			// TODO primera fase del proyecto no soporta variables COILS
			request = new IllegalFunctionRequest(functionCode);
			break;
		case Modbus.WRITE_MULTIPLE_REGISTERS:
			// TODO primera fase del proyecto no soporta escritura en variables 
			request = new IllegalFunctionRequest(functionCode);
			break;
		case Modbus.WRITE_SINGLE_REGISTER:
			// TODO primera fase del proyecto no soporta escritura en variables 
			request = new IllegalFunctionRequest(functionCode);
			break;
		case Modbus.WRITE_COIL:
			// TODO primera fase del proyecto no soporta escritura en variables 
			request = new IllegalFunctionRequest(functionCode);
			break;
		case Modbus.WRITE_MULTIPLE_COILS:
			// TODO primera fase del proyecto no soporta escritura en variables 
			request = new IllegalFunctionRequest(functionCode);
			break;
		default:
			request = new IllegalFunctionRequest(functionCode);
			break;
		}
		return request;
	}

}
