package com.pdvsa.psp.component.opc;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.dcom.da.OPCSERVERSTATUS;
import org.openscada.opc.dcom.da.impl.OPCGroupStateMgt;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.ServerStateOperation;
import com.pdvsa.psp.modbus.ModbusSlave;
import com.pdvsa.psp.modbus.processimage.ProcessImageImplementation;
import com.pdvsa.psp.modbus.processimage.SimpleInputRegister;
import com.pdvsa.psp.modbus.util.ModbusUtil;
import com.pdvsa.psp.model.Item.ITEM_TYPE;
import com.pdvsa.psp.model.ServidorOpc.ACCESS_METHOD;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.state.ConnectionState;
import com.pdvsa.psp.state.ServerState;

public class OpcServerDef extends OpcComponent implements Runnable {
    private boolean defaultActive = true;
    private int defaultUpdateRate = 1000;
    private Integer defaultTimeBias;
    private Float defaultPercentDeadband;
    private int defaultLocaleID = 0;
    private OpcConnection opcConn;
    private boolean itemCache = false;
	private int refAddressBase = 40000;
	private int sleepInterPooling = 5000;
    private ACCESS_METHOD accessMethod = ACCESS_METHOD.SYNC;
    private volatile ServerState serverState = ServerState.STOPPED;
    private ServerStatistic serverStatistic = new ServerStatistic();
	private String lastError = "";
    private volatile Thread serverThread;
    private IOpcManager opcManager;
    private Map<Long, OpcGroupDef> groupsDef = new Hashtable<Long, OpcGroupDef>();
    private ProcessImageImplementation processImage;
	private boolean byteBigEndian = true;
	private boolean wordBigEndian = true;
	private boolean dwordBigEndian = false;	

    public OpcServerDef(IOpcManager opcManager, Long id, String name, OpcConnection opcConn) {
    	super(id, name);
    	this.opcManager = opcManager;
        this.opcConn = opcConn;
    }
    
	public Float getDefaultPercentDeadband() {
		return defaultPercentDeadband;
	}

	public void setDefaultPercentDeadband(Float defaultPercentDeadband) {
		this.defaultPercentDeadband = defaultPercentDeadband;
	}

	public Integer getDefaultTimeBias() {
		return defaultTimeBias;
	}

	public void setDefaultTimeBias(Integer defaultTimeBias) {
		this.defaultTimeBias = defaultTimeBias;
	}

	public int getDefaultUpdateRate() {
		return defaultUpdateRate;
	}

	public void setDefaultUpdateRate(int defaultUpdateRate) {
		this.defaultUpdateRate = defaultUpdateRate;
	}

	public boolean isDefaultActive() {
		return defaultActive;
	}

	public void setDefaultActive(boolean defaultActive) {
		this.defaultActive = defaultActive;
	}
	
	public void setItemCache(boolean itemCache) {
		this.itemCache = itemCache;
	}

	public boolean getItemCache() {
		return itemCache;
	}
	
	public void setSleepInterPooling(int sleepInterPooling) {
		this.sleepInterPooling = sleepInterPooling;
	}

	public int getSleepInterPooling() {
		return sleepInterPooling;
	}
	
	public boolean isByteBigEndian() {
		return byteBigEndian;
	}

	public void setByteBigEndian(boolean byteBigEndian) {
		this.byteBigEndian = byteBigEndian;
	}

	public boolean isWordBigEndian() {
		return wordBigEndian;
	}

	public void setWordBigEndian(boolean wordBigEndian) {
		this.wordBigEndian = wordBigEndian;
	}

	public boolean isDwordBigEndian() {
		return dwordBigEndian;
	}

	public void setDwordBigEndian(boolean dwordBigEndian) {
		this.dwordBigEndian = dwordBigEndian;
	}	
	
	public void setRefAddressBase(Integer refAddressBase) {
		this.refAddressBase = refAddressBase;
	}

	public Integer getRefAddressBase() {
		return refAddressBase;
	}
	
	public void setAccessMethod(ACCESS_METHOD accessMethod) {
		this.accessMethod = accessMethod;
	}

	public ACCESS_METHOD getAccessMethod() {
		return accessMethod;
	}
	
	public void setServerStatistic(ServerStatistic serverStatistic) {
		this.serverStatistic = serverStatistic;
	}

	public ServerStatistic getServerStatistic() {
		return serverStatistic;
	}	
	
	public OpcConnection getOpcConn() {
		return opcConn;
	}	
	
	public ServerState getState() {
		return serverState;
	}
	
	public void setState(ServerState serverState) {
		this.serverState = serverState;
		if (serverState == ServerState.STARTING) {
			serverStatistic.markStartConnTime();
		}
		else if (serverState == ServerState.STARTED) {
			serverStatistic.markStartServerTime();
		}
		else if (serverState == ServerState.STOPPED) {
			serverStatistic.markStopServerTime();
		}
        logger.info (String.format("Status del Servidor %s: %s", getOpcConn().toString(), serverState));
	}
	
	public int getConnIntents() {
		return getOpcConn().getConnIntents();
	}
	
	public String getLastError() {
		return lastError;
	}
	
	protected void setError(String msgError,  Throwable e) {
		this.lastError = String.format("[%s] %s", new Date(), msgError);
		logger.error(msgError, e);
		++countErrors;
	}	
	
	public org.openscada.opc.dcom.da.OPCSERVERSTATUS getServerState(int timeout) throws Throwable {
		return new org.openscada.opc.lib.da.ServerStateOperation(getOpcConn().getServer()).getServerState(timeout);
	}

	public OPCSERVERSTATUS getServerState() {
		try {
			return getServerState(2500);
		} catch (Throwable e) {
			logger.info("Fallo conexion con el servidor", e);
			return null;
		}
	}
	
	public void removeGroup(OpcGroupDef group, boolean force) {
		Long groupId = group.getId();
		if (groupsDef.containsKey(groupId)) {
			if (group.isAttach()) {
				try {
					logger.info(String.format("Removiendo el grupo %s del servidor %s...", group.getName(), getName()));
					getOpcConn().getServer().removeGroup(group.getServerHandle(), force);
					logger.info(String.format("Grupo %s removido del servidor %s", group.getName(), getName()));
				} 
				catch (JIException e) {
					setError(String.format("No se logró remover el grupo %s del servidor %s (%08X: %s)", group.getName(), getName(), 
							e.getErrorCode(), getErrorMessage(e.getErrorCode())), null);
				}
				catch (Exception e) {
					setError(String.format("No se encontró una conexión válida para remover el grupo %s del servidor %s", group.getName(), getName()), null);
				}
			}
			groupsDef.remove(groupId);
		}
	}
	
	public void clearRegisters() {
		getProcessImage().clearImages();
		for(Map.Entry<Long, OpcGroupDef> entry : groupsDef.entrySet()) {
			removeGroup(entry.getValue(), true);
		}
	}	
	
	public synchronized OpcGroupDef addGroup(Long id, String name, Integer pooling) {
		if (groupsDef.containsKey(id)) {
			return groupsDef.get(id);
		} else {
			OpcGroupDef group = new OpcGroupDef(this, id, name, pooling);
			groupsDef.put(id, group);
			return group;
		}
	}
	
	protected boolean attachGroup(OpcGroupDef grpDef) {
		boolean result = true;
		if (!grpDef.isAttach()) {
			OPCGroupStateMgt groupMgt = null;
			try {
				groupMgt = getOpcConn().getServer().addGroup(grpDef.getName(),
						defaultActive, defaultUpdateRate, 0, defaultTimeBias,
						defaultPercentDeadband, defaultLocaleID);
				grpDef.attach(groupMgt);
			} 
	        catch(Exception e) {
	        	result = false;
	        	if (groupMgt != null) {
	        		try {
	        			getOpcConn().getServer().removeGroup(groupMgt, true);
					} catch (JIException e1) {
						e1.printStackTrace();
					}
	        	}
	        	String baseError = String.format("No se logró asignar el grupo %s al servidor %s. ", grpDef.getName(), getName());
	        	if (e instanceof UnknownHostException) {
	        		setError(baseError + "Host desconocido. ", e );
		        }
	        	else if (e instanceof IllegalArgumentException) {
		        	setError(baseError, e);
		        }
		        else if (e instanceof JIException) {
		        	JIException JIexc = (JIException) e;
		        	if (JIexc.getErrorCode() == 0xC004000C)
		        		setError(baseError + "Grupo ya se encuentra asignado al servidor. ", e );
		        	else
		        		setError(String.format("%s (%08X: %s)", 
		        				baseError, JIexc.getErrorCode(), getErrorMessage(JIexc.getErrorCode())), null);
		        }
		        else
		        	setError(baseError + "Error desconocido. ", e);
	        }			
		}
		return result;
	}
	
	public synchronized void attachGroups() throws InterruptedException {
		if (getOpcConn().getState() == ConnectionState.CONNECTED) {
			for(Map.Entry<Long, OpcGroupDef> entry : groupsDef.entrySet()) {
				ifInterruptedStop();
				OpcGroupDef grpDef = entry.getValue(); 
				if (attachGroup(grpDef)) {
					try {
						grpDef.attachItems();
					} catch (JIException e) {
						setError(String.format("Error asociando los items al grupo %s. (%08X: %s)", 
								grpDef.getName(), e.getErrorCode(), getErrorMessage(e.getErrorCode())), null);
					}
				}
			}
		}
	}
	
	public String getErrorMessage(int errorCode) {
		return getOpcConn().getErrorMessage(errorCode);
	}
	
	private void ifInterruptedStop() throws InterruptedException {
		Thread.yield(); 
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
	}
	  
	protected void opcConnect() throws InterruptedException {
		int maxIntents = opcManager.getMaxServerErrors();
		int intentsConn = 0;
		String serverName = getOpcConn().toString();	
		clearRegisters();
		while(++intentsConn <= maxIntents) {
			try {
				ifInterruptedStop();
				getOpcConn().connect();
				return;
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					throw (InterruptedException)e;
				}
		        if (e instanceof IllegalArgumentException) {
		            setError(e.getMessage(), null);
		        }
		        else if (e instanceof UnknownHostException) {
		            setError("Host desconocido al momento de conectarse con el servidor " + serverName, null);
		        }
		        else if (e instanceof JIException) {
		        	JIException JIexc = (JIException) e;
		        	setError(String.format("No se logro la conexion con el servidor %s (%08X: %s)", 
		        			serverName, JIexc.getErrorCode(), getErrorMessage(JIexc.getErrorCode())), null);
		        }
		        else {
		        	setError("Error desconocido en la conexion con el servidor " + serverName, e);
		        }
				Thread.sleep(opcManager.getServerReconnectDelay());
			}
		}
	}
	
	protected void process() throws InterruptedException {
		setState(ServerState.STARTED);
		int maxErrors = opcManager.getMaxServerErrors();
		int curIntents = 0;		
		ifInterruptedStop();
		int sleepBase = getSleepInterPooling();
		while(serverThread != null) {
			synchronized (groupsDef) {
				for(Map.Entry<Long, OpcGroupDef> entry : groupsDef.entrySet()) {
					ifInterruptedStop();
					OpcGroupDef grpDef = entry.getValue(); 
					if (grpDef.getCounter() >= grpDef.getPooling()) {
						try {
							grpDef.read();
							curIntents = 0;
						} catch (JIException e) {
							setError(String.format("Error leyendo los valores de items en el grupo %s. (%08X: %s)", 
									grpDef.getName(), e.getErrorCode(), getErrorMessage(e.getErrorCode())), null);
							if (++curIntents > maxErrors) {
								return;
							}
						}
						finally {
							grpDef.setCounter(sleepBase);
						}
					}
					else {
						grpDef.addCounter(sleepBase);
					}
				}	
			}
			Thread.sleep(sleepBase);
		}
	}
	
	// TODO: Pendiente desacoplar SimpleInputRegister a traves de InputRegister
	public void updateRegister(OpcItemDef opcItem) {
		if (getProcessImage() == null) return;
		int lenReg = opcItem.getDataType().getLengthForRegister();
		if (lenReg == 0) return; 
		byte[] registers = null;
		try {
			registers = ModbusUtil.objectToRegister(opcItem.getItemState().getValue().getObject(), 
					isByteBigEndian(), isWordBigEndian(), isDwordBigEndian());
		} catch (Exception e) {}
		if ((registers == null) || (lenReg * 2 != registers.length)) {
			Arrays.fill(registers = new byte[lenReg * 2], (byte)0);
		}
		int ref = opcItem.getId().intValue() - getRefAddressBase().intValue() - 1;
		for(int i = 0; i < registers.length;) {
			getProcessImage().setInputRegister(ref++,
					new SimpleInputRegister(registers[i++], registers[i++]));
		}
	}
	
	// TODO: Pendiente desacoplar SimpleInputRegister a traves de InputRegister
	protected long generateRegister(long ref, int lenReg) {
		if (getProcessImage() != null) {
			for(int i = 1; i <= lenReg; i++) {
				getProcessImage().addInputRegister(new SimpleInputRegister(0));
			}
		}
		return (ref + lenReg);
	}
	
	protected boolean loadRegisters() throws InterruptedException {
		boolean result = true;
		logger.info("Generando registros de memoria para el servidor: " + getOpcConn().toString() + "...");
		clearRegisters();
		try {
	    	List<Tanque> tanks = opcManager.getAdquisitionService().getTanquesByServidor(getId());
	    	if (tanks.size() <= 0) {
	        	logger.warn(String.format("No se encontraron tanques registrados para el servidor: %s.", 
	        			getOpcConn().toString()), null);
	    	}
	    	ifInterruptedStop();
			long ref = getRefAddressBase() + 1;
			int lengReg = 0;
			int contGroups = 0;
			boolean hasItems = false;
	        for(ServidorGrupo grp : opcManager.getAdquisitionService().getGruposByServidor(getId())) {
	        	ifInterruptedStop();
	        	contGroups++;
	    		final String groupName = grp.getGrupo().getNombre();
	    		final OpcGroupDef groupDef = addGroup(grp.getGrupo().getId(), groupName, grp.getPooling());
	    		logger.info(String.format("Servidor: %s  Grupo: %s", getOpcConn().toString(), groupName ));
				for(Item item: opcManager.getAdquisitionService().getItemsByGrupo(grp.getGrupo().getId())) {
					if ((lengReg = item.getTipoDato().getLengthForRegister()) == 0)
						continue;
					if (item.getTipoItem() == ITEM_TYPE.TANK) {
						for(Tanque tank: tanks) {
							hasItems = true;
							String tagOpc = tank.getNombre() + "." + item.getItemOpc();
							logger.debug(String.format("Grupo: %s  Ref: %d  Item: %s", groupName, ref, tagOpc));
							OpcItemDef itemDef = groupDef.addItem(ref, item.getNombre(), tagOpc, item.getTipoDato());
							if (item.getHda()) {
								itemDef.setHdaOwnerId(tank.getId());
							}
							ref = generateRegister(ref, lengReg); 
						}
					}
					else {
						hasItems = true;
						logger.debug(String.format("Grupo: %s  Ref: %d  Item: %s", groupName, ref, item.getItemOpc()));
						OpcItemDef itemDef = groupDef.addItem(ref, item.getNombre(), item.getItemOpc(), item.getTipoDato());
						if (item.getHda()) {
							//TODO: por implementar HDA para dispositivos
							itemDef.setHdaOwnerId(null);
						}
						ref = generateRegister(ref, lengReg); 
					}
				}
	        }
	        if (contGroups <= 0) {
	        	setError(String.format("No se encontraron grupos registrados para el servidor: %s.", 
	        			getOpcConn().toString()), null);
	        	result = false;
	        }
	        if (!hasItems) {
	        	setError(String.format("No se encontraron items registrados para el servidor: %s.", 
	        			getOpcConn().toString()), null);
	        	result = false;
	        }
		}
		catch (Exception ex) {
			result = false;
			setError(String.format("Se produjó un error generando los registros de memoria para el servidor: %s.", 
					getOpcConn().toString()), ex);
		}
		return result;
	}
	
	@Override
	public void run() {
		if (serverThread == null) return;
		try {
			do {
				getOpcConn().disconnect();
				setState(ServerState.STARTING);
				opcConnect();
				if (getOpcConn().getState() != ConnectionState.CONNECTED) {
					if (opcManager.getStopServerForMaxError())
						break;
					else
						continue;
				}
				if (!loadRegisters())
					continue;
				attachGroups();
				process();
			} while(true);
		} catch (InterruptedException e) {
			logger.info("Por demanda fue detenido el servidor: " + getOpcConn().toString());
		}
		finally {
			clearRegisters();
			getOpcConn().disconnect();
			setState(ServerState.STOPPED);
		}
  	}
	
	public synchronized boolean start() {
		if (getState() != ServerState.STOPPED)
			return false;
		serverThread = new Thread(this, "OPCServer-" + getId());
		serverThread.start();
		return true;
	}
	
	public synchronized boolean stop(long sleepValue) {
		if (getState() != ServerState.STARTING && getState() != ServerState.STARTED) {
			return false;
		}
		setState(ServerState.STOPPING);
        Thread tmpThread = serverThread;
        serverThread = null;
        if (tmpThread != null) {
            tmpThread.interrupt();
        }
        if (sleepValue > 0) {
    		try {
    			Thread.sleep(sleepValue);
    		} catch (InterruptedException e) {}
        }
		return true;
	}
	
	public synchronized boolean reconnect() {
		stop(3000);
		return start();
	}

	public synchronized List<OpcInfoRegister> getDumpRegistersByServer() {
		List<OpcInfoRegister> result = new ArrayList<OpcInfoRegister>();
		OpcInfoRegister opcInfoReg = null;
		OpcGroupDef grpDef = null;
		ItemState state = null;
		synchronized (groupsDef) {
			for(Map.Entry<Long, OpcGroupDef> entryGroup : groupsDef.entrySet()) {
				grpDef = entryGroup.getValue(); 
				for(OpcItemDef itemDef : grpDef.getItemsDef()) {
					state = itemDef.getItemState();					
					opcInfoReg = new OpcInfoRegister(getId(),
							itemDef.getId().intValue(),
							itemDef.getTagOpc(),							
							itemDef.getName(),
							itemDef.getDataType(),
							(state.getTimestamp() == null) ? null : state.getTimestamp().getTime(),
							itemDef.toString(),
							state.getQuality());
					result.add(opcInfoReg);
				}
			}
		}
		return result;
	}

	public void setProcessImage(ProcessImageImplementation processImage) {
		this.processImage = processImage;
		ModbusSlave.getReference().addProcessImage(getId().intValue(), processImage);
	}

	public ProcessImageImplementation getProcessImage() {
		return processImage;
	}
	
	/********** NO MIGRAR A SUB-VERSION ************************/
	
	private volatile int countErrors = 0;
	
	public int getCountErrors() {
		return countErrors;
	}
	
	public void clearError() {
		countErrors = 0;
	}

}
