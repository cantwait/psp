package com.pdvsa.psp.modbus.message;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface ModbusMessage {
	/**
	 * @return el Identificador de la Transacción (2 bytes - 0-65535)
	 */
	public int getTransactionID();

	/**
	 * @return Identificador del Protocolo (2 bytes - 0-255)
	 */
	public int getProtocolID();

	/**
	 * @return la Longitud en bytes de los datos anexos despues del header (2 bytes - 0-65535)
	 */
	public int getDataLength();

	/**
	 * @return Identificador de Unidad (1 byte - 0-255)
	 */
	public int getUnitID();

	/**
	 * @return el Código de función MODBUS (1 byte - 0-127)
	 */
	public int getFunctionCode();


	public String toString();
	
	public int getOutputLength();

	public void writeTo(DataOutput dataOut) throws IOException;

	public void readFrom(DataInput dataIn) throws IOException;
}
