package com.pdvsa.psp.modbus.util;

import com.pdvsa.psp.modbus.Modbus;
import com.pdvsa.psp.modbus.io.BytesOutputStream;
import com.pdvsa.psp.modbus.message.ModbusMessage;
import java.io.IOException;

public final class ModbusUtil {

	private static BytesOutputStream byteOut = new BytesOutputStream(Modbus.MAX_MESSAGE_LENGTH);

	public static final String toHex(ModbusMessage msg) {
		String ret = "-1";
		try {
			synchronized (byteOut) {
				msg.writeTo(byteOut);
				ret = toHex(byteOut.getBuffer(), 0, byteOut.size());
				byteOut.reset();
			}
		} catch (IOException ex) {
		}
		return ret;
	}

	public static final String toHex(byte[] data) {
		return toHex(data, 0, data.length);
	}

	public static final String toHex(byte[] data, int off, int length) {
		StringBuffer buf = new StringBuffer(data.length * 2);
		for (int i = off; i < length; i++) {
			if (((int) data[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) data[i] & 0xff, 16));
			if (i < data.length - 1) {
				buf.append(" ");
			}
		}
		return buf.toString();
	}

	public static final byte[] toHex(int i) {
		StringBuffer buf = new StringBuffer(2);
		if (((int) i & 0xff) < 0x10) {
			buf.append("0");
		}
		buf.append(Long.toString((int) i & 0xff, 16).toUpperCase());
		return buf.toString().getBytes();
	}

	public static final int registerToUnsignedShort(byte[] bytes, boolean isByteBigEndian) {
		if (bytes.length < 2) {
			throw new ArrayIndexOutOfBoundsException(bytes.length);
		}
		int a, b;
		if (isByteBigEndian) {
			a = ((bytes[0] & 0xFF) << 8);
			b = (bytes[1] & 0xFF);
		} else {
			a = ((bytes[1] & 0xFF) << 8);
			b = (bytes[0] & 0xFF);
		}
		return (a | b);		
	}

	public static final byte[] unsignedShortToRegister(int v, boolean isByteBigEndian) {
		byte b1 = (byte) (0xff & (v >> 8));
		byte b0 = (byte) (0xff & v);
		return ((isByteBigEndian) ? new byte[]{b1, b0} : new byte[]{b0, b1});
	}

	public static final short registerToShort(byte[] bytes, boolean isByteBigEndian) {
		if (bytes.length < 2) {
			throw new ArrayIndexOutOfBoundsException(bytes.length);
		}
		short a, b;
		if (isByteBigEndian) {
			a = (short) (bytes[0] << 8);
			b = (short) (bytes[1] & 0xFF);
		} else {
			a = (short) (bytes[1] << 8);
			b = (short) (bytes[0] & 0xFF);
		}
		return (short) (a | b);			
	}
	
	public static final short registerToShort(byte[] bytes, int idx, boolean isByteBigEndian) {
		if (bytes.length <= (idx + 1)) {
			throw new ArrayIndexOutOfBoundsException(bytes.length);
		}
		short a, b;
		if (isByteBigEndian) {
			a = (short) (bytes[idx] << 8);
			b = (short) (bytes[idx + 1] & 0xFF);
		} else {
			a = (short) (bytes[idx + 1] << 8);
			b = (short) (bytes[idx] & 0xFF);
		}
		return (short) (a | b);			
	}
	
	public static final byte[] shortToRegister(short s, boolean isByteBigEndian) {
		byte b1 = (byte) (0xff & (s >> 8));
		byte b0 = (byte) (0xff & s);
		return ((isByteBigEndian) ? new byte[]{b1, b0} : new byte[]{b0, b1});		
	}
	
	public static final int registersToInt(byte[] bytes, boolean isByteBigEndian, boolean isWordBigEndian) {
	     if (bytes.length < 4) {
	          throw new ArrayIndexOutOfBoundsException(bytes.length);
	     }
	     int a, b, c, d;
	     if (isByteBigEndian){
	          a = (bytes[0] & 0xFF) << 24;
	          b = (bytes[1] & 0xFF) << 16;
	          c = (bytes[2] & 0xFF) << 8;
	          d =  bytes[3] & 0xFF;
	     } 
	     else {
	          a = (bytes[3] & 0xFF) << 24;
	          b = (bytes[2] & 0xFF) << 16;
	          c = (bytes[1] & 0xFF) << 8;
	          d =  bytes[0] & 0xFF;
	     }
	     return (isWordBigEndian ? (a | b | c | d) : (c | d | a | b));
	}
	
	public static final byte[] intToRegisters(int v, 
			boolean isByteBigEndian, boolean isWordBigEndian) {
		byte[] registers;
		byte b3 = (byte) (0xff & (v >> 24));
		byte b2 = (byte) (0xff & (v >> 16));
		byte b1 = (byte) (0xff & (v >> 8));
		byte b0 = (byte) (0xff & v);
		if (isByteBigEndian) {
			registers = (isWordBigEndian) ? new byte[]{b3, b2, b1, b0} : new byte[]{b1, b0, b3, b2};
		} 
		else {
			registers = (isWordBigEndian) ? new byte[]{b0, b1, b2, b3} : new byte[]{b2, b3, b0, b1};
		}		
		return registers;
	}

	public static final long registersToLong(byte[] bytes,
			boolean isByteBigEndian, boolean isWordBigEndian, boolean isDwordBigEndian) {
		if (bytes.length < 8) {
			throw new ArrayIndexOutOfBoundsException(bytes.length);
		}
		long a, b, c, d, e, f, g, h;
		if (isByteBigEndian) {
			a = (long) (bytes[0] & 0xFF) << 56;
			b = (long) (bytes[1] & 0xFF) << 48;
			c = (long) (bytes[2] & 0xFF) << 40;
			d = (long) (bytes[3] & 0xFF) << 32;
			e = (long) (bytes[4] & 0xFF) << 24;
			f = (long) (bytes[5] & 0xFF) << 16;
			g = (long) (bytes[6] & 0xFF) << 8;
			h = (long) (bytes[7] & 0xFF);
		} else {
			a = (long) (bytes[7] & 0xFF) << 56;
			b = (long) (bytes[6] & 0xFF) << 48;
			c = (long) (bytes[5] & 0xFF) << 40;
			d = (long) (bytes[4] & 0xFF) << 32;
			e = (long) (bytes[3] & 0xFF) << 24;
			f = (long) (bytes[2] & 0xFF) << 16;
			g = (long) (bytes[1] & 0xFF) << 8;
			h = (long) (bytes[0] & 0xFF);
		}
		long result = 0;
		if (isWordBigEndian) {
			result = isDwordBigEndian 
				? a | b | c | d | e | f | g | h
				: e | f | g | h | a | b | c | d;
		}
		else {
			result = isDwordBigEndian
				? c | d | a | b | g | h | e | f
				: g | h | e | f | c | d | a | b;
		}
		return result;
	}

	public static final byte[] longToRegisters(long v, 
			boolean isByteBigEndian, boolean isWordBigEndian, boolean isDwordBigEndian) {
		byte[] registers;
		byte b7 = (byte) (0xff & (v >> 56));
		byte b6 = (byte) (0xff & (v >> 48));
		byte b5 = (byte) (0xff & (v >> 40));
		byte b4 = (byte) (0xff & (v >> 32));
		byte b3 = (byte) (0xff & (v >> 24));
		byte b2 = (byte) (0xff & (v >> 16));
		byte b1 = (byte) (0xff & (v >> 8));
		byte b0 = (byte) (0xff & v);
		if (isByteBigEndian) {
			if (isWordBigEndian) {
				registers = isDwordBigEndian 
						? new byte[] {b7, b6, b5, b4, b3, b2, b1, b0}
						: new byte[] {b3, b2, b1, b0, b7, b6, b5, b4};
			}
			else {
				registers = isDwordBigEndian
						? new byte[] {b5, b4, b7, b6, b1, b0, b3, b2}
						: new byte[] {b1, b0, b3, b2, b5, b4, b7, b6};
			}
		} else {
			if (isWordBigEndian) {
				registers = isDwordBigEndian
						? new byte[] {b0, b1, b2, b3, b4, b5, b6, b7}
						: new byte[] {b4, b5, b6, b7, b0, b1, b2, b3};
			}
			else {
				registers = isDwordBigEndian
						? new byte[] {b2, b3, b0, b1, b6, b7, b4, b5}
						: new byte[] {b6, b7, b4, b5, b2, b3, b0, b1};
			}
		}
		return registers;
	}
	
	public static final float registersToFloat(byte[] bytes,
			boolean isByteBigEndian, boolean isWordBigEndian) {
		int value = registersToInt(bytes, isByteBigEndian, isWordBigEndian);
		return Float.intBitsToFloat(value);
	}

	public static final byte[] floatToRegisters(float f, 
			boolean isByteBigEndian, boolean isWordBigEndian) {
		return intToRegisters(Float.floatToIntBits(f), isByteBigEndian, isWordBigEndian);
	}
	
	public static final double registersToDouble(byte[] bytes,
			boolean isByteBigEndian, boolean isWordBigEndian, boolean isDwordBigEndian) {
		long value = registersToLong(bytes, isByteBigEndian, isWordBigEndian, isDwordBigEndian);
		return Double.longBitsToDouble(value);
	}

	public static final byte[] doubleToRegisters(double d, 
			boolean isByteBigEndian, boolean isWordBigEndian, boolean isDwordBigEndian) {
		return longToRegisters(Double.doubleToLongBits(d), isByteBigEndian, isWordBigEndian, isDwordBigEndian);
	}
	
    public static final byte[] objectToRegister(Object value, 
    		boolean isByteBigEndian, boolean isWordBigEndian, boolean isDwordBigEndian)
    {
        if (value instanceof Double) {
        	return doubleToRegisters(((Double)value).doubleValue(), 
        			isByteBigEndian, isWordBigEndian, isDwordBigEndian);
        }
        else if (value instanceof Float) {
        	return floatToRegisters(((Float)value).floatValue(),
        			isByteBigEndian, isWordBigEndian);
        }
        else if (value instanceof Byte) {
        	return shortToRegister(((Byte)value).shortValue(), isByteBigEndian);
        }
        else if (value instanceof Integer) {
        	return intToRegisters(((Integer)value).intValue(),
        			isByteBigEndian, isWordBigEndian);
        }
        else if (value instanceof Short) {
        	return shortToRegister(((Short)value).shortValue(), isByteBigEndian);
        }
        else if (value instanceof Long) {
        	return longToRegisters(((Long)value).longValue(), 
        			isByteBigEndian, isWordBigEndian, isDwordBigEndian);
        }
        else if (value instanceof Boolean) {
        	return shortToRegister((short)(((Boolean)value) ? 1 : 0), isByteBigEndian);
        }
        return null;
    }	

	public static final int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	public static final byte lowByte(int wd) {
		return (new Integer(0xff & wd).byteValue());
	}
	
	public static final byte hiByte(int wd) {
		return (new Integer(0xff & (wd >> 8)).byteValue());
	}
	
	public static final int makeWord(int hibyte, int lowbyte) {
		int hi = 0xFF & hibyte;
		int low = 0xFF & lowbyte;
		return ((hi << 8) | low);
	}

	public static final int[] calculateCRC(byte[] data, int offset, int len) {
		int[] crc = { 0xFF, 0xFF };
		int nextByte = 0;
		int uIndex; 
		for (int i = offset; i < len && i < data.length; i++) {
			nextByte = 0xFF & ((int) data[i]);
			uIndex = crc[0] ^ nextByte;
			crc[0] = crc[1] ^ auchCRCHi[uIndex];
			crc[1] = auchCRCLo[uIndex];
		}
		return crc;
	}

	public static final int calculateLRC(byte[] data, int off, int len) {
		int lrc = 0;
		for (int i = off; i < len; i++) {
			lrc += (int) data[i] & 0xff;
		}
		lrc = (lrc ^ 0xff) + 1; 
		return (int) ((byte) lrc) & 0xff;
	}

	/* Tabla de valores CRC para el high-order */
	private final static short[] auchCRCHi = { 0x00, 0xC1, 0x81, 0x40, 0x01,
			0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
			0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
			0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
			0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00,
			0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
			0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
			0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
			0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00,
			0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
			0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
			0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
			0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00,
			0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
			0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
			0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
			0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00,
			0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
			0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80,
			0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
			0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
			0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40,
			0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
			0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40 };

	/* Tabla de valores CRC para el low-order */
	private final static short[] auchCRCLo = { 0x00, 0xC0, 0xC1, 0x01, 0xC3,
			0x03, 0x02, 0xC2, 0xC6, 0x06, 0x07, 0xC7, 0x05, 0xC5, 0xC4, 0x04,
			0xCC, 0x0C, 0x0D, 0xCD, 0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB,
			0x0B, 0xC9, 0x09, 0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB,
			0xDA, 0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC, 0x14,
			0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3,
			0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2,
			0x32, 0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34, 0xF4, 0x3C, 0xFC,
			0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A, 0x3B, 0xFB, 0x39,
			0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B, 0x2A, 0xEA,
			0xEE, 0x2E, 0x2F, 0xEF, 0x2D, 0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25,
			0xE5, 0x27, 0xE7, 0xE6, 0x26, 0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21,
			0x20, 0xE0, 0xA0, 0x60, 0x61, 0xA1, 0x63, 0xA3, 0xA2, 0x62, 0x66,
			0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD, 0x6D,
			0xAF, 0x6F, 0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8,
			0x68, 0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA, 0xBE, 0x7E,
			0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75, 0xB5, 0x77,
			0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0,
			0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57,
			0x97, 0x55, 0x95, 0x94, 0x54, 0x9C, 0x5C, 0x5D, 0x9D, 0x5F, 0x9F,
			0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59, 0x58, 0x98, 0x88,
			0x48, 0x49, 0x89, 0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F,
			0x8D, 0x4D, 0x4C, 0x8C, 0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46,
			0x86, 0x82, 0x42, 0x43, 0x83, 0x41, 0x81, 0x80, 0x40 };

}
