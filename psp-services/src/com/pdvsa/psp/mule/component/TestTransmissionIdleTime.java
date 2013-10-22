package com.pdvsa.psp.mule.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableDateTime;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class TestTransmissionIdleTime {
	
	private String intervalTime;
	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> cache;
	
	private Map<String,String> notificacionesEnviadas = null;
	private Map<String, Date> controlTransmision = null;	
	
	 
//	public List<OpcInfoRegisterMongo> validateTimeBetween(){
	public List<String> validateTimeBetween(){
	
		List<String> registrosConTiempoSuperado = new ArrayList<String>();

		for (Entry<String, Date> entry : controlTransmision.entrySet()) {
			try {
				if (entry.getValue() != null) {
					Integer tiempo = calculateDifference(entry.getValue());
					if (tiempo > Integer.valueOf(getIntervalTime())) {
						if (notificacionesEnviadas != null) {
							if (!notificacionesEnviadas.containsKey(entry.getKey())) {
								registrosConTiempoSuperado.add(entry.getKey());
								notificacionesEnviadas.put(entry.getKey(),entry.getKey());
							}
						} else {
							registrosConTiempoSuperado.add(entry.getKey());
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
//		List<OpcInfoRegisterMongo> registrosConTiempoSuperado = new ArrayList<OpcInfoRegisterMongo>();
//		 
//		OpcInfoRegisterMongo opc = null;
//		
//		OUTTER:
//			
//		for(Entry<String, Map<String, List<OpcInfoRegisterMongo>>> outtermap : getCache().entrySet()){
//			
//			for(Entry<String, List<OpcInfoRegisterMongo>> innermap : outtermap.getValue().entrySet()){
//				opc = innermap.getValue().size() > 0 ? innermap.getValue().get(0) : null;
//				if(opc != null){
//					try {
//						if (opc.getTimestamp() != null) {
//							Integer tiempo = calculateDifference(opc.getTimestamp());
//							if(tiempo > Integer.valueOf(getIntervalTime())){
//								if (notificacionesEnviadas != null) {
//									if (!notificacionesEnviadas.containsKey(opc.getLocalidad())) {
//										registrosConTiempoSuperado.add(opc);
//										notificacionesEnviadas.put(opc.getLocalidad(), opc);
//									}
//								} else {
//									registrosConTiempoSuperado.add(opc);
//								}
//							}
//						}
//					} catch (ParseException e) {						
//						e.printStackTrace();
//					}
//				}
//				
//				continue OUTTER;
//			}
//		}
		
		
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
	

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String tiempoMinutos) {
		this.intervalTime = tiempoMinutos;
	}

	public Map<String, Map<String, List<OpcInfoRegisterMongo>>> getCache() {
		return cache;
	}

	public void setCache(
			Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache) {
		this.cache = mapCache;
	}

	public Map<String, String> getNotificacionesEnviadas() {
		return notificacionesEnviadas;
	}

	public void setNotificacionesEnviadas(
			Map<String, String> notificacionesEnviadas) {
		this.notificacionesEnviadas = notificacionesEnviadas;
	}

	public Map<String, Date> getControlTransmision() {
		return controlTransmision;
	}

	public void setControlTransmision(Map<String, Date> controlTransmision) {
		this.controlTransmision = controlTransmision;
	}
	
	
	
	
	

}
