package com.pdvsa.psp.mule.component;

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

public class OpcInfoPollingService extends AbstractTransformer{
	
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
//		List<OpcInfoRegister> info = getObjectOpc();
 		if(info.size() > 0){
			for (OpcInfoRegister op : info) {				
				OpcInfoRegisterMongo o = new OpcInfoRegisterMongo();
				o.setIdServidor(op.getStationId());

				o.setQuality(op.getQuality());
				o.setRegValue(op.getRegValue());
				o.setTagName(op.getTagName());
				o.setTagOpc(op.getTagOpc());
				o.setTimestamp(op.getTimestamp());				
				items.getOpcItems().add(o);
			}
		}
		
		return items;
	}
	
	
	/**
	 * Generando data para pruebas.
	 * @return
	 */
	public static List<OpcInfoRegister> getObjectOpc() {	
		
		
		List<OpcInfoRegister> oir = new ArrayList<OpcInfoRegister>();
		
		DateFormat df;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date1 = "2013-06-23";
		String date2 = "2013-06-24";
		String date3 = "2013-06-25";
		
		
		Date d1;
		Date d2;
		Date d3;
		try {
			d1 = sdf.parse(date1);
			d2 = sdf.parse(date2);
			d3 = sdf.parse(date3);		
			
			OpcInfoRegister o1 = new OpcInfoRegister(Long.valueOf(20), Integer.valueOf(50), "t-1002.VU.[9]", "testSimulacion1", DATA_TYPE.R4, d1, "11000.2", Short.valueOf("10"));
			OpcInfoRegister o2 = new OpcInfoRegister(Long.valueOf(20), Integer.valueOf(10), "t-1002.VU.[10]", "testSimulacion2", DATA_TYPE.R8, d2, "3211000.2", Short.valueOf("50"));
			OpcInfoRegister o3 = new OpcInfoRegister(Long.valueOf(20), Integer.valueOf(150), "t-1002.VU.[11]", "testSimulacion3", DATA_TYPE.R4, d3, "-122231.223", Short.valueOf("160"));
			
			oir.add(o1);
			oir.add(o2);
			oir.add(o3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oir;
	}

}
