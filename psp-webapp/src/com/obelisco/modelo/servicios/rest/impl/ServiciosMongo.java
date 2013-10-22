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
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.model.xml.PageLogResponseImpl;
import com.pdvsa.psp.model.xml.PageOpcInfoRegisterResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;


public class ServiciosMongo implements IServiciosMongo{
	
	private RestTemplate restTemplate;
	private String address;
	
	@Override
	public OpcItemsTransfer getLastItemsByTanque(String servidor, String tanque) {
			
		
		String url = new String(address + "/cache/consultar?servidor={servidor}&tanque={tanque}");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
	    uriVariables.put("servidor", servidor);
	    uriVariables.put("tanque", tanque);

			
		ResponseEntity<OpcItemsTransfer> response = restTemplate.exchange(url,HttpMethod.GET, request, OpcItemsTransfer.class, uriVariables);
		
		return response.getBody();
	}
	
	@Override
	public PageOpcInfoRegisterResponseImpl queryItems(String desde, String hasta, String pais,String region, String localidad, String variable, Integer pagina, Integer cantidad) {
		
				
		
		
		String url = new String(address + "/historico/consultar?desde={desde}&hasta={hasta}&pais={pais}&region={region}&localidad={localidad}&pagina={pagina}&tamano={tamano}");
		System.out.println(url.toString());
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
	    
	    if(pais != null && pais.length() > 0){
			uriVariables.put("pais", pais);

		}
		if(region != null && region.length() > 0){
			uriVariables.put("region", region);
		}
		if(localidad != null && localidad.length() > 0){
			uriVariables.put("localidad", localidad);
		}
		
		
		uriVariables.put("variable", variable);
		uriVariables.put("desde", desde);
		uriVariables.put("hasta", hasta);
		uriVariables.put("pagina", pagina);
		uriVariables.put("cantidad", cantidad);
		
		ResponseEntity<PageOpcInfoRegisterResponseImpl> response = restTemplate.exchange(url, HttpMethod.GET, request, PageOpcInfoRegisterResponseImpl.class, uriVariables);
		
		return response.getBody();
	}
	
	@Override
	public PageLogResponseImpl queryLog(String desde, String hasta, String tipoEvento, Integer pagina, Integer tamano) {
		
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
		
		ResponseEntity<PageLogResponseImpl> response = restTemplate.exchange(url, HttpMethod.GET, request, PageLogResponseImpl .class, uriVariables);
		
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
