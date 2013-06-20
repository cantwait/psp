package com.pdvsa.psp.component.opc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openscada.opc.dcom.da.OPCSERVERSTATUS;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.springframework.beans.factory.InitializingBean;
import com.pdvsa.psp.modbus.processimage.SimpleProcessImage;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.service.IOpcAdquisitionService;
import com.pdvsa.psp.state.ServerState;

public class OpcManager implements IOpcManager, InitializingBean {
	protected final Log logger = LogFactory.getLog(OpcManager.class);
	private Long regionId;
	private IOpcAdquisitionService adquisitionService;
	private Map<Long, OpcServerDef> opcServers = new Hashtable<Long, OpcServerDef>();
	private Integer maxServerErrors = 5;
	private Boolean stopServerForMaxError = false;
	private Long serverReconnectDelay = 10000L; // Ms.
	private boolean byteBigEndian = true;
	private boolean wordBigEndian = true;
	private boolean dwordBigEndian = false;	

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getRegionId() {
		return regionId;
	}
	
	public void setAdquisitionService(IOpcAdquisitionService adquisitionService) {
		this.adquisitionService = adquisitionService;
	}

	@Override
	public IOpcAdquisitionService getAdquisitionService() {
		return adquisitionService;
	}
	
	public void setMaxServerErrors(Integer maxServerErrors) {
		this.maxServerErrors = maxServerErrors;
	}

	@Override
	public Integer getMaxServerErrors() {
		return maxServerErrors;
	}
	
	public void setStopServerForMaxError(Boolean stopServerForMaxError) {
		this.stopServerForMaxError = stopServerForMaxError;
	}

	@Override
	public Boolean getStopServerForMaxError() {
		return stopServerForMaxError;
	}	
	
	public void setServerReconnectDelay(Long serverReconnectDelay) {
		this.serverReconnectDelay = serverReconnectDelay;
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

	@Override
	public Long getServerReconnectDelay() {
		return serverReconnectDelay;
	}
	
	@Override
	public Map<Long, OpcServerDef> getOpcServers() {
		return opcServers;
	}
	
	protected void cleanupServers() {
		logger.info(String.format("Destruyendo conexiones existentes en servidores en la región nro.: %d...", regionId));
		synchronized(opcServers) {
			for(Map.Entry<Long, OpcServerDef> entry : opcServers.entrySet()) {
				OpcServerDef srv = entry.getValue();
				srv.stop(500);
			}
			opcServers.clear();
		}
	}
	
	protected OpcServerDef addServer(ServidorOpc svr) {
		final ConnectionInformation connInfo = new ConnectionInformation();
		connInfo.setHost(svr.getHost());
		connInfo.setDomain(svr.getDomain());
		connInfo.setUser(svr.getUsername());
		connInfo.setPassword(svr.getPassword());
		connInfo.setClsid(svr.getClsid());
		connInfo.setProgId(svr.getProgid());
		final OpcConnection opcConn = new OpcConnection(connInfo, svr.getNombre(), svr.getSocketTimeout());
		logger.info(String.format("Se agrego el servidor: %s a la region nro.: %d.", opcConn.toString(), regionId));
		OpcServerDef opcSrv = new OpcServerDef(this, svr.getId(), svr.getNombre(), opcConn);
		opcSrv.setItemCache(svr.getItemCache().booleanValue());
		opcSrv.setAccessMethod(svr.getAccessMethod());
		opcSrv.setSleepInterPooling(svr.getSleepInterPooling());
		opcSrv.setRefAddressBase(svr.getRefAddressBase());
		opcSrv.setProcessImage(new SimpleProcessImage());
		opcSrv.setDwordBigEndian(isDwordBigEndian());
		opcSrv.setByteBigEndian(isByteBigEndian());
		opcSrv.setWordBigEndian(isWordBigEndian());
		opcServers.put(opcSrv.getId(), opcSrv);
		return opcSrv;
	}	
	
	protected void loadOpcServers(boolean autoConnect) {
		cleanupServers();
		logger.info(String.format("Cargando servidores para la región nro.: %d...", regionId));
		try {
			OpcServerDef opcSrv = null;
			for(ServidorOpc svr : adquisitionService.getServidoresByRegion(getRegionId())) {
				opcSrv = addServer(svr);
				if (autoConnect || svr.getAutoConectar()) {
					opcSrv.start();
				}
			}
		}
		catch (Exception ex) {
			logger.error(String.format("Se produjó un error cargando servidores para la región nro.: %d.", regionId), ex);
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadOpcServers(false);
	}		
	
	@Override
	public Long getOwnerId() {
		return getRegionId();
	}	
	
	@Override
	public synchronized OpcServerDef getServer(Long serverId) {
		return opcServers.get(serverId);
	}
	
	@Override
	public synchronized ServerState getServerState(Long serverId) {
		final OpcServerDef svr = getServer(serverId);
		if (svr != null) {
			return svr.getState();
		}
		return ServerState.UNKNOWN;
	}
	
	@Override
	public synchronized boolean serverStart(Long serverId) {
		final OpcServerDef svr = getServer(serverId);
		if (svr != null) {
			return svr.start();
		}		
		return false;
	}
	
	@Override
	public synchronized boolean serverStop(Long serverId) {
		final OpcServerDef svr = getServer(serverId);
		if (svr != null) {
			return svr.stop(500);
		}
		return false;
	}
	
	@Override
	public synchronized boolean serverRestart(Long serverId) {
		final OpcServerDef svr = getServer(serverId);
		if (svr != null) {
			return svr.reconnect();
		}
		return false;
	}
	
	@Override
	public synchronized boolean addServer(Long serverId) {
		if (opcServers.containsKey(serverId))
			return true;
		ServidorOpc svr = adquisitionService.getServidor(serverId);
		if (svr != null) {
			addServer(svr);
			return true;
		}
		return false;
	}
	
	@Override
	public synchronized boolean removeServer(Long serverId) {
		if (serverStop(serverId)) {
			return (opcServers.remove(serverId) != null);
		}
		return false;
	}
	
	@Override
	public synchronized boolean reloadServers() {
		loadOpcServers(true);
		return true;
	}	

	@Override
	public synchronized List<OpcInfoRegister> getDumpRegistersByServer(Long serverId) {
		final OpcServerDef svr = getServer(serverId);
		if (svr != null) {
			return svr.getDumpRegistersByServer();
		}
		return new ArrayList<OpcInfoRegister>();
	}

	@Override
	public List<OpcInfoRegister> getAllRegisters() {
		List<OpcInfoRegister> allInformation = new ArrayList<OpcInfoRegister>();
		for(Map.Entry<Long, OpcServerDef> entry : opcServers.entrySet()) {
			OpcServerDef srv = entry.getValue();
			if(srv.getState() == ServerState.STARTED){
				allInformation.addAll(srv.getDumpRegistersByServer());
			}			
		}
		return allInformation;
	}
	
}
