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
import com.pdvsa.psp.model.xml.PageLoggerResponseImpl;
import com.pdvsa.psp.model.xml.PageOpcInfoResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;
import com.pdvsa.psp.mule.rest.exception.BadRequestException;

@Path("/")
public class MongoFindItemsPentahoRest {

	private MongoTemplate mongoTemplate;

	@GET
	@Produces("text/xml")
	@Path("/consultar")
	public PageResponse findHistoricOpcData(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta,  @QueryParam(value = "pais") String pais,	@QueryParam(value = "region") String region, @QueryParam(value = "tanque") String tanque,@QueryParam(value="variable") String variable, @QueryParam(value = "localidad") String localidad, @QueryParam("pagina") Integer pagina, @QueryParam("tamano") Integer tamano) throws BadRequestException {

		
		
		List items = new ArrayList();
		Query qry = new Query();
		Query qryCount = new Query();
		
		if(desde == null || hasta == null){
			throw new BadRequestException("Las fechas no pueden ser nulas!");
		}
		
		
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		if(desdeFomatted.getValue().after(hastaFomatted.getValue())){
			throw new BadRequestException("La fecha Desde no puede ser mayor que Hasta");
		}
	
		List<Criteria> criterias = new ArrayList<Criteria>();
		
		
		
		
		
		criterias.add(Criteria.where("timestamp").gt(desdeFomatted.getValue()));
		criterias.add(Criteria.where("timestamp").lte(hastaFomatted.getValue()));
		
		if(variable == null || variable.trim().length() == 0){
			throw new BadRequestException("El valor de la Variable no puede ser nulo!");
		}else{
			criterias.add(Criteria.where("tagName").regex(variable));
		}
		
		
		
		if(pais != null && pais.trim().length() > 0){
			criterias.add(Criteria.where("paisNombre").regex(pais));
		}
		
		if(region != null && region.trim().length() > 0){
			criterias.add(Criteria.where("regionNombre").regex(region));
		}
		
		if(localidad != null && localidad.trim().length() > 0){
			criterias.add(Criteria.where("localidadNombre").regex(localidad));
		}
		
		if(tanque != null && tanque.trim().length() > 0){
			criterias.add(Criteria.where("tanqueNombre").regex(tanque));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
		qryCount.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		long cant = getMongoTemplate().count(qryCount, "opcInfoRegister");
		
		
		qry.with(new Sort(new Order(Direction.ASC, "timestamp")));
		
		if(pagina == null || tamano == null){
			throw new BadRequestException("El numero de pagina y/o tamaño de elementos no pueden ser nulo!");
		}
		
		qry.with(new PageRequest(pagina, tamano));
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegister");
				
		PageResponse response = new PageOpcInfoResponseImpl(items, pagina, tamano, cant);
		
		return response;

	}
	
	
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
