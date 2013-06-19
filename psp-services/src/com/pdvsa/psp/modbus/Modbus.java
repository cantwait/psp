package com.pdvsa.psp.modbus;

public interface Modbus {

	/**
	 * Define el código de función para la clase 0 para leer multiple registros.
	 */
	public static final int READ_MULTIPLE_REGISTERS = 3;

	/**
	 * Define el código de función para la clase 0 para escribir multiple
	 * registros.
	 */
	public static final int WRITE_MULTIPLE_REGISTERS = 16;

	/**
	 * Define el código de función para la clase 1 para leer coils.
	 */
	public static final int READ_COILS = 1;

	/**
	 * Define el código de función para la clase 1 para lectura de entrada
	 * discretas.
	 */
	public static final int READ_INPUT_DISCRETES = 2;

	/**
	 * Define el código de función para la clase 1 para lectura de entrada de
	 * registros
	 */
	public static final int READ_INPUT_REGISTERS = 4;

	/**
	 * Define el código de función para la clase 1 para escribir coil.
	 */
	public static final int WRITE_COIL = 5;

	/**
	 * Defines un código de función standard para escribir multiples coil.
	 */
	public static final int WRITE_MULTIPLE_COILS = 15;

	/**
	 * Define el código de función para la clase 1 para escribir registros
	 * unicos.
	 */
	public static final int WRITE_SINGLE_REGISTER = 6;

	/**
	 * Define el byte que representa el estado ON del coil.
	 */
	public static final int COIL_ON = (byte) 255;

	/**
	 * Define el byte que representa el estado OFF del coil.
	 */
	public static final int COIL_OFF = 0;

	/**
	 * Define el word (bigendian) que representa el estado ON del coil
	 */
	public static final byte[] COIL_ON_BYTES = { (byte) COIL_ON,
			(byte) COIL_OFF };

	/**
	 * Define el word (bigendian) que representa el estado OFF del coil
	 */
	public static final byte[] COIL_OFF_BYTES = { (byte) COIL_OFF,
			(byte) COIL_OFF };

	/**
	 * Define el número máximo de bits en múltiples lectura/escritura de entrada
	 * discretas o coils.
	 */
	public static final int MAX_BITS = 2000;

	/**
	 * Define el offset para una excepción de slave Modbus que se agrega al
	 * código de la función, para marcar una excepción.
	 */
	public static final int EXCEPTION_OFFSET = 128; // 0x80 (bit mas
													// significativo activo)

	public static final int ILLEGAL_FUNCTION_EXCEPTION = 1;

	public static final int ILLEGAL_ADDRESS_EXCEPTION = 2;

	public static final int ILLEGAL_VALUE_EXCEPTION = 3;

	public static final int DEFAULT_PORT = 502;

	public static final int MAX_MESSAGE_LENGTH = 260;

	public static final int DEFAULT_TRANSACTION_ID = 0;

	public static final int DEFAULT_PROTOCOL_ID = 0;

	public static final int DEFAULT_UNIT_ID = 0;

}
