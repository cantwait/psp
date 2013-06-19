package org.home.psp.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class ResponseMessage {
	
	private Long stationId;
	
	public ResponseMessage(){
		
	}
	
	public ResponseMessage(Long stationId){
		this.stationId = stationId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	
	
	

}
