package org.home.psp.service;

import java.util.Collection;

import org.home.psp.data.OpcInfoRegister;

public interface IPspService {
	
	public void saveOpcData(Collection<OpcInfoRegister> opcData);
	public void updateOpcData(Long stationId);
	public OpcInfoRegister findOneOpc(Long stationId);
	

}
