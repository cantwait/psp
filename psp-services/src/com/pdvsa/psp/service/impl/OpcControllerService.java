package com.pdvsa.psp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.service.IOpcControllerService;
import com.pdvsa.psp.state.ServerState;
import com.pdvsa.psp.component.opc.IOpcManager;;

@WebService(serviceName="manageOpcControllerService", endpointInterface="com.pdvsa.psp.service.IOpcControllerService")
public class OpcControllerService implements IOpcControllerService {
	private List<IOpcManager> opcManagers = new ArrayList<IOpcManager>();
	
	public void setOpcManagers(List<IOpcManager> opcManagers) {
		this.opcManagers = opcManagers;
	}

	public List<IOpcManager> getOpcManagers() {
		return opcManagers;
	}
	
	private IOpcManager getOpcManager(Long serverId) {
        for (IOpcManager opcManager : opcManagers) {
        	if (opcManager.getServerState(serverId) != ServerState.UNKNOWN);
        		return opcManager;
        }
        return null;
	}
	
	private IOpcManager getOpcManagerByOwner(Long ownerId) {
        for (IOpcManager opcManager : opcManagers) {
        	if (opcManager.getOwnerId() == ownerId);
        		return opcManager;
        }
        return null;
	}	

	@Override
	public ServerState getServerState(Long serverId) {
		IOpcManager opcManager = getOpcManager(serverId);
		if (opcManager != null) {
			return opcManager.getServerState(serverId);
		}
		return ServerState.UNKNOWN;
	}

	@Override
	public boolean serverRestart(Long serverId) {
		IOpcManager opcManager = getOpcManager(serverId);
		if (opcManager != null) {
			return opcManager.serverRestart(serverId);
		}
		return false;
	}

	@Override
	public boolean serverStart(Long serverId) {
		IOpcManager opcManager = getOpcManager(serverId);
		if (opcManager != null) {
			return opcManager.serverStart(serverId);
		}
		return false;
	}

	@Override
	public boolean serverStop(Long serverId) {
		IOpcManager opcManager = getOpcManager(serverId);
		if (opcManager != null) {
			return opcManager.serverStop(serverId);
		}
		return false;
	}

	@Override
	public boolean addServer(Long ownerId, Long serverId) {
		IOpcManager opcManager = getOpcManagerByOwner(ownerId);
		if (opcManager != null) {
			return opcManager.addServer(serverId);
		}
		return false;
	}
	
	@Override
	public boolean removeServer(Long ownerId, Long serverId) {
		IOpcManager opcManager = getOpcManagerByOwner(ownerId);
		if (opcManager != null) {
			return opcManager.removeServer(serverId);
		}
		return false;
	}	
	
	@Override
	public boolean reloadServers(Long ownerId) {
		IOpcManager opcManager = getOpcManagerByOwner(ownerId);
		if (opcManager != null) {
			return opcManager.reloadServers();
		}
		return false;
	}	

	@Override
	public List<OpcInfoRegister> getDumpRegistersByServer(Long serverId) {
		IOpcManager opcManager = getOpcManager(serverId);
		if (opcManager != null) {
			return opcManager.getDumpRegistersByServer(serverId);
		}
		return new ArrayList<OpcInfoRegister>();
	}
	
	/**
	 * Retorna una lista de Objetos {@link OpcInfoRegister}  de toda la localidad sin importar
	 * el servidor.
	 * @author Rafael Cadenas
	 * @return List<OpcInfoRegister>
	 * @since 18/06/2013
	 */
	@Override
	public List<OpcInfoRegister> getAllRegisters() {
		for (IOpcManager opc : getOpcManagers()) {
			return opc.getAllRegisters();
		}
		return new ArrayList<OpcInfoRegister>();
	}

}
