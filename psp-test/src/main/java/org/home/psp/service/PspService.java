package org.home.psp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.home.psp.dao.OpcInfoRegisterDAO;
import org.home.psp.data.OpcInfoRegister;
import org.home.psp.repository.OpcInfoRegisterRepository;
import org.soqqo.datagen.RandomDataGenerator;
import org.soqqo.datagen.config.GenConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class PspService implements IPspService {

	
	private OpcInfoRegisterDAO opcDataRepo;
	
	@Override
	public void saveOpcData(Collection<OpcInfoRegister> opcData) {
		System.out.println("Guardando Datos en MongoDB");
		opcDataRepo.saveEntities(generateRandomObject());

	}
	
	public List<OpcInfoRegister> generateRandomObject(){
		RandomDataGenerator rdg = new RandomDataGenerator();
		List<OpcInfoRegister> oir = new ArrayList<OpcInfoRegister>();
		oir.addAll(rdg.generateList(20, new GenConfig().randomFromStringList("tagName", "T1,T2,T3,T4,T5,T6,T7,T8,T9").randomFromStringList("regValue", "11999, 222244,112121,44444,11111222,2444444,21111111,2222222,4444444,555555,66666"), OpcInfoRegister.class));
		return oir;
	}
	
	

	public OpcInfoRegisterDAO getOpcDataRepo() {
		return opcDataRepo;
	}

	public void setOpcDataRepo(OpcInfoRegisterDAO opcDataRepo) {
		this.opcDataRepo = opcDataRepo;
	}

	@Override
	public void updateOpcData(Long stationId) {
		
		opcDataRepo.updateOne(stationId);
	}

	@Override
	public OpcInfoRegister findOneOpc(Long stationId) {
		
		return opcDataRepo.findOne(stationId);
	}

		
}
