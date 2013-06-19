package com.pdvsa.psp.component.opc;

import java.util.List;
import java.util.Map;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.service.IOpcAdquisitionService;
import com.pdvsa.psp.state.ServerState;

public interface IOpcManager {
	
	public Long getOwnerId();
	
	public IOpcAdquisitionService getAdquisitionService() ;
	
	public Integer getMaxServerErrors();
	
	public Boolean getStopServerForMaxError();
	
	public Long getServerReconnectDelay();
	
	public Map<Long, OpcServerDef> getOpcServers();
	
	public OpcServerDef getServer(Long serverId);
	
	public ServerState getServerState(Long serverId);
	
	public boolean serverStart(Long serverId);
	
	public boolean serverStop(Long serverId);
	
	public boolean serverRestart(Long serverId);
	
	public boolean addServer(Long serverId);
	
	public boolean removeServer(Long serverId);
	
	public boolean reloadServers();
	
	public List<OpcInfoRegister> getDumpRegistersByServer(Long serverId);
	
	public List<OpcInfoRegister> getAllRegisters();
	
}
