package com.pdvsa.psp.mule.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.converter.DateFormatParam;
import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.PageLoggerResponse;
import com.pdvsa.psp.model.xml.PageOpcInfoResponse;

@Path("/")
public class MongoFindItemsPentahoRest {

	private MongoTemplate mongoTemplate;

	@GET
	@Produces("text/xml")
	@Path("/consultar")
	public PageOpcInfoResponse findHistoricOpcData(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta,  @QueryParam(value = "pais") String pais,	@QueryParam(value = "region") String region, @QueryParam(value = "localidad") String localidad, @QueryParam("pagina") Integer pagina, @QueryParam("tamano") Integer tamano) {

		
		
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
		Query qry = new Query();
		Query qryCount = new Query();
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
	
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("timestamp").gt(desdeFomatted.getValue()));
		criterias.add(Criteria.where("timestamp").lte(hastaFomatted.getValue()));
		
		if(pais != null && pais.length() > 0){
			criterias.add(Criteria.where("paisNombre").regex(pais));
		}
		
		if(region != null && region.length() > 0){
			criterias.add(Criteria.where("regionNombre").regex(region));
		}
		
		if(localidad != null && localidad.length() > 0){
			criterias.add(Criteria.where("localidadNombre").regex(localidad));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
		qryCount.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		long cant = getMongoTemplate().count(qryCount, "opcInfoRegisterHistoric");
		
		
		qry.with(new Sort(new Order(Direction.ASC, "timestamp")));
		
		qry.with(new PageRequest(pagina, tamano));
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
		
		PageOpcInfoResponse response = new PageOpcInfoResponse(items, new PageRequest(pagina, tamano), cant);
		
		return response;

	}
	
	
//	@GET
//	@Produces("text/plain")
//	@Path("/contar")
//	public String countHistoricData(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta,  @QueryParam(value = "pais") String pais,	@QueryParam(value = "region") String region, @QueryParam(value = "localidad") String localidad) {
//	
//		Query qry = new Query();
//		
//		Long cantidad = new Long(Long.MIN_VALUE);
//				
//		DateFormatParam desdeFomatted = new DateFormatParam(desde);
//		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
//		
//		System.out.println(desdeFomatted);
//		System.out.println(hastaFomatted);
//		
//		List<Criteria> criterias = new ArrayList<Criteria>();
//		criterias.add(Criteria.where("timestamp").gt(desdeFomatted.getValue()));
//		criterias.add(Criteria.where("timestamp").lte(hastaFomatted.getValue()));
//		
//		if(pais != null && pais.length() > 0){
//			criterias.add(Criteria.where("paisNombre").regex(pais));
//		}
//		
//		if(region != null && region.length() > 0){
//			criterias.add(Criteria.where("regionNombre").regex(region));
//		}
//		
//		if(localidad != null && localidad.length() > 0){
//			criterias.add(Criteria.where("localidadNombre").regex(localidad));
//		}
//		
//		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
//		
//		cantidad = getMongoTemplate().count(qry, "opcInfoRegisterHistoric");
//		
//		
//		
//		System.out.println(cantidad);
//				
//		return cantidad.toString();
//
//	}
	
	
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
