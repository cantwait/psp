package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.converter.DateFormatParam;
import com.pdvsa.psp.model.xml.MongoLogger;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

@Path("/")
public class MongoQueryLogRest {
	
	private MongoTemplate mongoTemplate;
	
	@GET
	@Produces("text/xml")
	@Path("/consultar")
	public OpcErrorResponse getLogByPropertiesOnDemand(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta, @QueryParam("evento") String evento, @QueryParam("pagina") Integer pagina, @QueryParam("tamano")Integer tamano){
		
		OpcErrorResponse response = new OpcErrorResponse();
		
		List<MongoLogger> items = new ArrayList<MongoLogger>();
		Query qry = new Query();
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("fecha").gt(desdeFomatted.getValue()));
		criterias.add(Criteria.where("fecha").lte(hastaFomatted.getValue()));
		
		if(evento != null && evento.length() > 0){
			criterias.add(Criteria.where("tipoEvento").regex(evento));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		qry.with(new Sort(new Order(Direction.ASC, "fecha")));
		
		qry.with(new PageRequest(pagina, tamano));
		
		items = getMongoTemplate().find(qry, MongoLogger.class, "opcErrorTransaction");
		
		if(items.size() > 0){
			response.getErrores().addAll(items);
		}
		
		return response;
		
	}
	
	@GET
	@Produces("text/plain")
	@Path("/contar")
	public String countLogByProperties(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta, @QueryParam("evento") String evento){
		Query qry = new Query();
		
		Long cantidad = new Long(Long.MIN_VALUE);
				
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		System.out.println(desdeFomatted);
		System.out.println(hastaFomatted);
		
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("fecha").gt(desdeFomatted.getValue()));
		criterias.add(Criteria.where("fecha").lte(hastaFomatted.getValue()));
		
		
		
		if(evento != null && evento.length() > 0){
			criterias.add(Criteria.where("tipoEvento").regex(evento));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
		
		cantidad = getMongoTemplate().count(qry, "opcErrorTransaction");
		
		return cantidad.toString();
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
