package com.obelisco.modelo.servicios.rest.impl;

import java.net.URI;
import java.net.URISyntaxException;
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


public class ServicioMongo implements IServiciosMongo{
	
	private RestTemplate restTemplate;
	private String address;
	
	@Override
	public OpcInfoRegisterListResponse findDataPentaho(OpcInfoRegisterRequest req) {
		URI url;
		OpcInfoRegisterListResponse response = new OpcInfoRegisterListResponse();
		try {
			url = new URI(address + "/query-vars/find-items");
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.TEXT_XML);
		    HttpEntity<OpcInfoRegisterRequest> request= new HttpEntity<OpcInfoRegisterRequest>(req, headers);
		
		    response = restTemplate.postForObject(url, request, OpcInfoRegisterListResponse.class);		
			
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public OpcInfoRegisterListResponse findLastRecord(OpcInfoRegisterRequest req) {
		URI url;
		OpcInfoRegisterListResponse response = new OpcInfoRegisterListResponse();
		try {
			url = new URI(address + "/query-vars/find-last-record");
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.TEXT_XML);
		    HttpEntity<OpcInfoRegisterRequest> request= new HttpEntity<OpcInfoRegisterRequest>(req, headers);
		
		    response = restTemplate.postForObject(url, request, OpcInfoRegisterListResponse.class);		
			
			
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public OpcErrorResponse findErrors(OpcErrorMongoRequest req) {
		URI url;
		OpcErrorResponse response = new OpcErrorResponse();
		try {
			url = new URI(address+ "/query-errors/get-log");
		
			 HttpHeaders headers = new HttpHeaders();
		     headers.setContentType(MediaType.TEXT_XML);
		     HttpEntity<OpcErrorMongoRequest> request= new HttpEntity<OpcErrorMongoRequest>(req, headers);
		
		     response = restTemplate.postForObject(url, request, OpcErrorResponse.class);
		     
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public OpcItemsTransfer getLastItemsByTanque(String tanqueNombre) {
			
		
		String url = new String(address + "/cache/get-items-by-tank?nombre={nombre}");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_XML);
	    HttpEntity<?> request= new HttpEntity<Object>(headers);
	    
	    Map<String, Object> uriVariables = new HashMap<String, Object>();
	    uriVariables.put("nombre", tanqueNombre);

			
		ResponseEntity<OpcItemsTransfer> response = restTemplate.exchange(url,HttpMethod.GET, request, OpcItemsTransfer.class, uriVariables);
		
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
