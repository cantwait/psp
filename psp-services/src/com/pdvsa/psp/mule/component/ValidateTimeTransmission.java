package com.pdvsa.psp.mule.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableDateTime;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class ValidateTimeTransmission {
	
	private String tiempoMinutos;
	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache;
	
	
	public List<OpcInfoRegisterMongo> validateTimeBetween(){
		List<OpcInfoRegisterMongo> registrosConTiempoSuperado = new ArrayList<OpcInfoRegisterMongo>();
		
		OpcInfoRegisterMongo opc = null;
		OUTTER:
		for(Entry<String, Map<String, List<OpcInfoRegisterMongo>>> outtermap : getMapCache().entrySet()){
			
			for(Entry<String, List<OpcInfoRegisterMongo>> innermap : outtermap.getValue().entrySet()){
				opc = innermap.getValue().size() > 0 ? innermap.getValue().get(0) : null;
				if(opc != null){
					try {
						Integer tiempo = calculateDifference(opc.getTimestamp());
						if(tiempo > Integer.valueOf(getTiempoMinutos())){
							registrosConTiempoSuperado.add(opc);
						}
					} catch (ParseException e) {						
						e.printStackTrace();
					}
				}
				
				continue OUTTER;
				
			}
		}
		
		
		return registrosConTiempoSuperado;
	}
	
	private Integer calculateDifference(Date date) throws ParseException{
		Integer difference = new Integer(0);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(Calendar.getInstance().getTime());
		
		Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        
        calendar1.setTime(date);
        
        Date currentTime = dateFormat.parse(dateStr);
        
        ReadableDateTime dateTime1 = new DateTime(calendar1.getTime());
        
        ReadableDateTime dateTime2 = new DateTime(calendar2.getTime());
        
        calendar2.setTime(currentTime);
        
        DurationFieldType[] args = new DurationFieldType[]{DurationFieldType.minutes()};
        
        PeriodType type = PeriodType.forFields(args);
        
        difference = new Period(dateTime1, dateTime2, type).getMinutes();
        
		
		return difference;
	}
	

	public String getTiempoMinutos() {
		return tiempoMinutos;
	}

	public void setTiempoMinutos(String tiempoMinutos) {
		this.tiempoMinutos = tiempoMinutos;
	}

	public Map<String, Map<String, List<OpcInfoRegisterMongo>>> getMapCache() {
		return mapCache;
	}

	public void setMapCache(
			Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache) {
		this.mapCache = mapCache;
	}
	
	
	
	
	

}
