package com.pdvsa.psp.component.opc;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.dcom.common.KeyedResult;
import org.openscada.opc.dcom.common.KeyedResultSet;
import org.openscada.opc.dcom.common.Result;
import org.openscada.opc.dcom.da.OPCDATASOURCE;
import org.openscada.opc.dcom.da.OPCITEMDEF;
import org.openscada.opc.dcom.da.OPCITEMRESULT;
import org.openscada.opc.dcom.da.OPCITEMSTATE;
import org.openscada.opc.dcom.da.impl.OPCAsyncIO2;
import org.openscada.opc.dcom.da.impl.OPCGroupStateMgt;
import org.openscada.opc.dcom.da.impl.OPCItemMgt;
import org.openscada.opc.dcom.da.impl.OPCSyncIO;
import com.pdvsa.psp.model.Item.DATA_TYPE;

public class OpcGroupDef extends OpcComponent {
	private OpcServerDef opcServerParent;
	private int serverHandle = 0;
	private OPCGroupStateMgt groupMgt;
	private OPCItemMgt itemMgt;
	private OPCSyncIO opcSyncIO;
	private Integer pooling = 30000;
	private Integer counter;
	private Vector<OpcItemDef> itemsDef = new Vector<OpcItemDef>();
	
	OpcGroupDef(OpcServerDef opcServerParent, Long id, String name, Integer pooling) {
		super(id, name);
		this.opcServerParent = opcServerParent;
		setPooling(pooling);
	}
	
	public int getServerHandle() {
		return serverHandle;
	}
	
	public OpcServerDef getServer() {
		return opcServerParent;
	}
	
	public void setPooling(Integer pooling) {
		this.counter = this.pooling = pooling;
	}

	public Integer getPooling() {
		return pooling;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Integer getCounter() {
		return counter;
	}
	
	public Vector<OpcItemDef> getItemsDef() {
		return itemsDef;
	}	
	
	public void addCounter(int counter) {
		this.counter += counter;
	}	
	
	public boolean isAttach() {
		return (serverHandle > 0);
	}
	
	public void attach(OPCGroupStateMgt groupMgt) throws IllegalArgumentException, UnknownHostException, JIException {
		this.groupMgt = groupMgt;		
		this.itemMgt = groupMgt.getItemManagement();
		this.opcSyncIO = groupMgt.getSyncIO();
		this.serverHandle = groupMgt.getState().getServerHandle();		
	}
	
	public synchronized OPCAsyncIO2 getAsyncIO20() {
		return groupMgt.getAsyncIO2();
	}	
	
	public boolean isActive() throws JIException {
		return groupMgt.getState().isActive();
	}	
	
	public void setActive(boolean state) throws JIException {
		groupMgt.setState(null, state, null, null, null, null);
	}

	public void remove() throws JIException {
		opcServerParent.removeGroup(this, true);
	}
	
	/**
	 * Se debería validar primero antes de asociar (attach) un Item al grupo (esto según establece la especificación OPC)
	 */
	public synchronized Map<String, Result<OPCITEMRESULT>> validateItems(String... items) throws JIException {
		OPCITEMDEF[] defs = new OPCITEMDEF[items.length];
		for (int i = 0; i < items.length; i++) {
			defs[i] = new OPCITEMDEF();
			defs[i].setItemID(items[i]);
		}
		KeyedResultSet<OPCITEMDEF, OPCITEMRESULT> result = itemMgt.validate(defs);
		Map<String, Result<OPCITEMRESULT>> resultMap = new HashMap<String, Result<OPCITEMRESULT>>();
		for (KeyedResult<OPCITEMDEF, OPCITEMRESULT> resultEntry : result) {
			resultMap.put(resultEntry.getKey().getItemID(),
					new Result<OPCITEMRESULT>(resultEntry.getValue(),
					resultEntry.getErrorCode()));
		}
		return resultMap;
	}	
	
	public synchronized OpcItemDef addItem(Long id, String name, String tagOpc, DATA_TYPE dataType) {
		OpcItemDef item = new OpcItemDef(this, id, name, tagOpc, dataType);
		itemsDef.add(item);
		return item;
	}
	
	protected OPCITEMDEF[] getArrayItemsWithoutAttach() {
		List<OPCITEMDEF> listItems = new ArrayList<OPCITEMDEF>();
		short itemIdx = 0;
		for(OpcItemDef itemDef : itemsDef) {
			if (!itemDef.isAttach()) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Asociando item: %s al grupo %s", itemDef.getTagOpc(), getName()));
				}
				OPCITEMDEF opcItem = new OPCITEMDEF();
				opcItem.setItemID(itemDef.getTagOpc());
				opcItem.setActive(true);
				opcItem.setRequestedDataType(itemDef.getOpcDataType());
				opcItem.setClientHandle(itemDef.getId().intValue());
				opcItem.setReserved(itemIdx);
				listItems.add(opcItem); 
			}
			itemIdx++;
		}
		OPCITEMDEF result[] = new OPCITEMDEF[listItems.size()];
		result = listItems.toArray(result);
		return result;
	}
	
	public synchronized void attachItems() throws  JIException {
		OPCITEMDEF[] arrayItems = getArrayItemsWithoutAttach();
		if (arrayItems.length > 0) {
			KeyedResultSet<OPCITEMDEF, OPCITEMRESULT> result;
			result = itemMgt.add(arrayItems);
			for (KeyedResult<OPCITEMDEF, OPCITEMRESULT> entry : result) {
				if (entry.getErrorCode() == 0) {
					short itemIdx = entry.getKey().getReserved();
					if (itemIdx >= 0 && itemIdx < itemsDef.size()) {
						OpcItemDef itemDef = itemsDef.elementAt(itemIdx);
						itemDef.attach(entry.getValue().getServerHandle());
						continue;
					}
					entry.setErrorCode(0x80004003);
				} 
				logger.error(String.format("Error asociando el item %s al grupo %s. (%08X: %s)", 
							entry.getKey().getItemID(), getName(), entry.getErrorCode(), getServer().getErrorMessage(entry.getErrorCode())));
			}						
		}
	}
	
	protected Integer[] getServerHandles() {
		List<Integer> listHandles = new ArrayList<Integer>();
		for(OpcItemDef itemDef : itemsDef) {
			if (itemDef.isAttach() && itemDef.getGroup() == this)
				listHandles.add(itemDef.getServerHandle());
		}
		Integer result[] = new Integer[listHandles.size()];
		result = listHandles.toArray(result);
		return result;
	}
	
	protected OpcItemDef getItemByServerHandle(Integer serverHandle) {
		for(OpcItemDef itemDef : itemsDef) {
			if (itemDef.getServerHandle() == serverHandle.intValue())
				return itemDef;
		}
		return null;
	}
	
	public synchronized void read() throws JIException {
		boolean itemCache = getServer().getItemCache();
		Integer[] handles = getServerHandles();
		KeyedResultSet<Integer, OPCITEMSTATE> states;
		try {
			states = opcSyncIO.read(
					itemCache ? OPCDATASOURCE.OPC_DS_CACHE : OPCDATASOURCE.OPC_DS_DEVICE, handles);
			for (KeyedResult<Integer, OPCITEMSTATE> entry : states) {
				OpcItemDef itemDef = getItemByServerHandle(entry.getKey());
				if (itemDef != null) {
					itemDef.setItemState(entry.getErrorCode(), entry.getValue().getQuality(), 
							entry.getValue().getValue(), entry.getValue().getTimestamp().asCalendar());
					opcServerParent.updateRegister(itemDef);
				}
			}
		} catch (JIException e) {
			for(Integer handle : handles) {
				OpcItemDef itemDef = getItemByServerHandle(handle);
				if (itemDef != null) {
					itemDef.clearItemState();
					opcServerParent.updateRegister(itemDef);
				}
			}
			throw e;
		}
	}

}
