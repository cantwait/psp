package com.pdvsa.psp.mule.transformer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.pdvsa.psp.model.Item.DATA_TYPE;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.service.IOpcControllerService;

public class OpcInfoRegisterToOpcInfoRegisterMongoTransformer extends AbstractTransformer{
	
	private IOpcControllerService opcControllerService;

	public IOpcControllerService getOpcControllerService() {
		return opcControllerService;
	}

	public void setOpcControllerService(IOpcControllerService opcControllerService) {
		this.opcControllerService = opcControllerService;
	}

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
			OpcItemsTransfer items = new OpcItemsTransfer();
		List<OpcInfoRegister> info = opcControllerService.getAllRegisters();
 		if(info.size() > 0){
			for (OpcInfoRegister op : info) {				
				OpcInfoRegisterMongo o = new OpcInfoRegisterMongo();
				o.setIdServidor(op.getStationId());

				o.setCalidad(op.getQuality());
				o.setValor(op.getRegValue());
				o.setVariable(op.getTagName());
				o.setNombreOPC(op.getTagOpc());
				o.setTimestamp(op.getTimestamp());				
				items.getOpcItems().add(o);
			}
		}
		
		return items;
	}
	
	
}
