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
		oir.addAll(rdg.generateList(50, new GenConfig().randomFromStringList("tagName", "T1,T2,T3,T4,T5,T6,T7,T8,T9").randomFromStringList("regValue", "11999, 222244,112121,44444,11111222,2444444,21111111,2222222,4444444,555555,66666").randomFromStringList("hostModbusSlave", "10.20.8.1, 10.20.8.2, 10.20.8.3, 10.20.8.4, 10.20.8.5").randomFromStringList("portModbusSlave", "1000, 2000, 3000, 4000").randomFromStringList("reference", "1,2,3,4,5,6,7,8,9").randomFromStringList("tagOpc", "OP1, OP2, OP3, OP4, OP5").randomFromStringList("regType", "REG1, REG2,REG3,REG4,REG5").randomFromStringList("timestamp", "12/08/2012, 13/08/2013,14/08/2013").randomFromStringList("quality", "0, 50, 100, 150,200"), OpcInfoRegister.class));
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
