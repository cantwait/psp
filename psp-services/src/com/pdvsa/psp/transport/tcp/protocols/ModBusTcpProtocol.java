package com.pdvsa.psp.transport.tcp.protocols;

import org.mule.DefaultMuleMessage;
import org.mule.ResponseOutputStream;
import org.mule.transport.tcp.TcpProtocol;
import org.mule.transport.tcp.protocols.ProtocolStream;
//import org.mule.transport.tcp.TcpProtocol;
//import org.mule.transport.tcp.protocols.ProtocolStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pdvsa.psp.modbus.Modbus;
import com.pdvsa.psp.modbus.ModbusIOException;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.message.ExceptionResponse;
import com.pdvsa.psp.modbus.message.ModbusRequest;
import com.pdvsa.psp.modbus.message.ModbusResponse;
import com.pdvsa.psp.modbus.util.ModbusUtil;
import com.pdvsa.psp.modbus.io.BytesInputStream;


public class ModBusTcpProtocol implements TcpProtocol {
	
    private static final Log logger = LogFactory.getLog(ModBusTcpProtocol.class);
    private int maxMessageLength;
	private boolean bigEndian = true;

    public ModBusTcpProtocol()
    {
        this(Modbus.MAX_MESSAGE_LENGTH);
    }

    public ModBusTcpProtocol(int maxMessageLength)
    {
        this.setMaxMessageLength(maxMessageLength);
    }

    public int getMaxMessageLength()
    {
        return maxMessageLength;
    }

    public void setMaxMessageLength(int maxMessageLength)
    {
        this.maxMessageLength = maxMessageLength;
    }

	@Override
	public Object read(InputStream is) throws IOException {
		DataInputStream dataIn = new DataInputStream(new BufferedInputStream(is));
		ModbusRequest request = null;
		try {
			request = readRequest(dataIn);
			if (logger.isDebugEnabled()) {
				logger.debug("Request: " + request.toString());
			}
		} catch (ModbusIOException ex) {
			throw new IOException("I/O Request Exception - Fallo de lectura.", ex);
		}
		return request;
	}

	@Override
	public void write(OutputStream os, Object data) throws IOException {
		ModbusResponse response = null;
		if (data instanceof ModbusResponse) {
			response = (ModbusResponse) data;
		}
		else if (data instanceof DefaultMuleMessage) {
			DefaultMuleMessage muleMsg = (DefaultMuleMessage)data;
			if (muleMsg.getPayload() instanceof ModbusRequest) {
				ModbusRequest request = (ModbusRequest) muleMsg.getPayload();
				if (ModbusSlave.getReference().getProcessImage(request.getUnitID()) == null) {
					response = request.createExceptionResponse(Modbus.ILLEGAL_ADDRESS_EXCEPTION);
				} else {
					response = request.createResponse();
				}
			}
			else if (muleMsg.getPayload() instanceof ModbusResponse) {
				response = (ModbusResponse) muleMsg.getPayload();
			}
			else {
				response = new ExceptionResponse();
			}			
		}
		else {
			response = new ExceptionResponse();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Response: " + response.toString());
		}
		DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(os));
		try {
			response.writeTo((DataOutput) dataOut);
		} catch (Exception ex) {
			throw new IOException("I/O Response Exception - Fallo de escritura.", ex);
		}
		finally {
			dataOut.flush();
		}
	}
	
	@Override
	public ResponseOutputStream createResponse(Socket socket) throws IOException {
		return new ResponseOutputStream(socket, new ProtocolStream(this, false, socket.getOutputStream()));
	}
	
	public ModbusRequest readRequest(DataInputStream dataIn) throws ModbusIOException {
		BytesInputStream bytesIn = new BytesInputStream(maxMessageLength);
		try {
			byte[] buffer = bytesIn.getBuffer();
			// Leer los primero 6 bytes del header
			if (dataIn.read(buffer, 0, 6) == -1) {
				throw new EOFException("Mensaje MODBUS inválido (MBAP Header truncado).");
			}
			// Se extrae el tamaño de bytes del mensaje (los 2 bytes de la posición 4)
			int lenMsg = ModbusUtil.registerToShort(buffer, 4, bigEndian);
			// Se extrae los bytes restantes a partir del Unit Identifier (byte 6).
			if (dataIn.read(buffer, 6, lenMsg) == -1) {
				throw new ModbusIOException("Mensaje MODBUS inválido (MBAP Header truncado).");
			}
			bytesIn.reset(buffer, (6 + lenMsg));
			bytesIn.skip(7);
			int functionCode = bytesIn.readUnsignedByte();
			bytesIn.reset();
			ModbusRequest request = ModbusRequest.createModbusRequest(functionCode);
			request.readFrom(bytesIn);
			return request;
		} catch (EOFException eoex) {
			throw new ModbusIOException(eoex.getMessage(), true);
		} catch (SocketException sockex) {
			throw new ModbusIOException(sockex.getMessage(), true);
		} catch (Exception ex) {
			throw new ModbusIOException(ex.getMessage());
		}
	}

	public boolean isBigEndian() {
		return bigEndian;
	}

	public void setBigEndian(boolean bigEndian) {
		this.bigEndian = bigEndian;
	}

}