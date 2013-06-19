package com.pdvsa.psp.component.opc;

import java.util.Calendar;

import com.pdvsa.psp.model.Item.DATA_TYPE;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIFlags;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.da.ItemState;

public class OpcItemDef extends OpcComponent {
	private Long hdaOwnerId;
	private DATA_TYPE dataType;
	private String tagOpc;
    private OpcGroupDef opcGroupParent;
    private int serverHandle = 0;
    private ItemState itemState = new ItemState();
	
	public OpcItemDef(OpcGroupDef opcGroupParent, Long id, String name, String tagOpc, DATA_TYPE dataType) {
		super(id, name);
		this.opcGroupParent = opcGroupParent;
		this.dataType = dataType;
		this.tagOpc = tagOpc;
		clearItemState();
	}
	
	public boolean isAttach() {
		return (serverHandle > 0);
	}	

	public void attach(int serverHandle) {
        this.serverHandle = serverHandle;
	}
	
	public Long getHdaOwnerId() {
		return hdaOwnerId;
	}

	public void setHdaOwnerId(Long hdaOwnerId) {
		this.hdaOwnerId = hdaOwnerId;
	}	
	
	public boolean allowHda() {
		return (hdaOwnerId != null);
	}	
	
	public String getTagOpc() {
		return tagOpc;
	}
	
	protected DATA_TYPE getDataType() {
		return dataType;
	}
	
	protected void setDataType(DATA_TYPE dataType) {
		this.dataType = dataType;
	}
	
	protected OpcGroupDef getGroup() {
		return opcGroupParent;
	}

	public void setServerHandle(int serverHandle) {
		this.serverHandle = serverHandle;
	}

	public int getServerHandle() {
		return serverHandle;
	}
	
	public ItemState getItemState() {
		return itemState;
	}
	
	public short getOpcDataType() {
		short result = JIVariant.VT_EMPTY;
		switch (dataType) {
			case I1:
				result = JIVariant.VT_I1;
				break;
			case I2: 
				result = JIVariant.VT_I2;
				break;
			case I4:
				result = JIVariant.VT_I4;
				break; 
			case R4: 
				result = JIVariant.VT_R4;
				break;
			case R8: 
				result = JIVariant.VT_R8;
				break;
			case CY: 
				result = JIVariant.VT_CY;
				break;
			case DATE:
				result = JIVariant.VT_DATE;
				break; 
			case BSTR:
				result = JIVariant.VT_BSTR;
				break; 
			case BOOL:
				result = JIVariant.VT_BOOL;
				break; 
			case UI1: 
				result = JIVariant.VT_UI1;
				break;
			case UI2: 
				result = JIVariant.VT_UI2;
				break;
			case UI4:
				result = JIVariant.VT_UI4;
				break;
		}
		return result;
	}
	
	public void clearItemState() {
		itemState.setErrorCode(0);
		itemState.setQuality(null);
		itemState.setValue(null);
		itemState.setTimestamp(null);
	}
	
	public void setItemState(int errorCode, short quality, JIVariant value, Calendar timestamp) {
		itemState.setErrorCode(errorCode);
		itemState.setQuality(quality);
		itemState.setValue(value);
		itemState.setTimestamp(timestamp);
		if (logger.isDebugEnabled()) {
            String preffix = String.format("Servidor: %s  Item: %s  Calidad: %d --> ", 
            		getGroup().getServer().getName(), 
            		getTagOpc(), quality);
			try {
				dumpValue(preffix, value);
			} catch (JIException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isEmptyItemState() {
		return (itemState.getErrorCode() == 0 && itemState.getQuality() == null && 
				itemState.getValue() == null && itemState.getTimestamp() == null);
	}
	
	protected void dumpValue(final String prefix, final Object value)
			throws JIException {
		if (value instanceof JIVariant) {
			final JIVariant variant = (JIVariant) value;
			if (!variant.isArray()) {
				dumpValue(prefix, variant.getObject());
			}
		} else if (value instanceof JIString) {
			final JIString string = (JIString) value;
			String strType;
			switch (string.getType()) {
				case JIFlags.FLAG_REPRESENTATION_STRING_BSTR:
					strType = "BSTR";
					break;
				case JIFlags.FLAG_REPRESENTATION_STRING_LPCTSTR:
					strType = "LPCSTR";
					break;
				case JIFlags.FLAG_REPRESENTATION_STRING_LPWSTR:
					strType = "LPWSTR";
					break;
				default:
					strType = "Desconocido";
					break;
			}
			logger.debug(prefix	+ String.format("JIString: '%s'(%s)", 
					string.getString(),	strType));
		} else if (value instanceof Double) {
			logger.debug(prefix + "Double: " + value);
		} else if (value instanceof Float) {
			logger.debug(prefix + "Float: " + value);
		} else if (value instanceof Byte) {
			logger.debug(prefix + "Byte: " + value);
		} else if (value instanceof Character) {
			logger.debug(prefix + "Character: " + value);
		} else if (value instanceof Integer) {
			logger.debug(prefix + "Integer: " + value);
		} else if (value instanceof Short) {
			logger.debug(prefix + "Short: " + value);
		} else if (value instanceof Long) {
			logger.debug(prefix + "Long: " + value);
		} else if (value instanceof Boolean) {
			logger.debug(prefix + "Boolean: " + value);
		} else {
			logger.debug(prefix	+ String.format("Tipo desconocido(%s): %s", 
					value.getClass(), value.toString()));
		}
	}

	@Override
	public String toString() {
		String result = "ERROR";
		if (itemState != null && itemState.getErrorCode() == 0) {
			try {
				result = itemState.getValue().getObject().toString();
			} catch (Exception e) {}
		}
		return result;
	}

}
