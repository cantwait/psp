package com.obelisco.modelo.servicios.rest.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.obelisco.modelo.servicios.rest.IServiciosMongo;
import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.model.xml.PageLoggerResponse;
import com.pdvsa.psp.model.xml.PageOpcInfoResponse;


public class ServicioMongo implements IServiciosMongo{
	
	private RestTemplate restTemplate;
	private String address;
	
	@Override
	public OpcItemsTransfer getLastItemsByTanque(String nombreServidor, String tanqueNombre) {
			
		
		String url = new String(address + "/cache/consultar?nombreServidor={nombreServidor}&nombreTanque={nombreTanque}");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
	    uriVariables.put("nombreServidor", nombreServidor);
	    uriVariables.put("nombreTanque", tanqueNombre);

			
		ResponseEntity<OpcItemsTransfer> response = restTemplate.exchange(url,HttpMethod.GET, request, OpcItemsTransfer.class, uriVariables);
		
		return response.getBody();
	}
	
	@Override
	public PageOpcInfoResponse getDataPentaho(String desde, String hasta, String pais,String region, String localidad, Integer pagina, Integer cantidad) {
		
		String nombrePais = "";
		String nombreRegion = "";
		String nombreLocalidad = "";
		
		if(pais != null && pais.length() > 0){
			nombrePais = pais;
		}
		if(region != null && region.length() > 0){
			nombreRegion = region;
		}
		if(localidad != null && localidad.length() > 0){
			nombreLocalidad = localidad;
		}
		
		String url = new String(address + "/historico/consultar?desde={desde}&hasta={hasta}&pais={pais}&region={region}&localidad={localidad}&pagina={pagina}&tamano={tamano}");
		System.out.println(url.toString());
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("pais", nombrePais);
		uriVariables.put("region", nombreRegion);
		uriVariables.put("localidad", nombreLocalidad);
		uriVariables.put("desde", desde);
		uriVariables.put("hasta", hasta);
		uriVariables.put("pagina", pagina);
		uriVariables.put("cantidad", cantidad);
		
		ResponseEntity<PageOpcInfoResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, PageOpcInfoResponse.class, uriVariables);
		
		return response.getBody();
	}
	
	@Override
	public PageLoggerResponse findLogByPropertiesOnDemand(String desde, String hasta, String tipoEvento, Integer pagina, Integer tamano) {
		
		String evento = "";
		
		if(tipoEvento != null && tipoEvento.length() > 0){
			evento = tipoEvento;
		}
		
		String url = new String(address + "/log/consultar?desde={desde}&hasta={hasta}&evento={evento}&pagina={pagina}&tamano={tamano}");
		System.out.println(url.toString());
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("desde", desde);
		uriVariables.put("hasta", hasta);
		uriVariables.put("evento", tipoEvento);
		uriVariables.put("pagina", pagina);
		uriVariables.put("tamano", tamano);
		
		ResponseEntity<PageLoggerResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, PageLoggerResponse.class, uriVariables);
		
		return response.getBody();
	}

	

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	

	

	
	
	

}
