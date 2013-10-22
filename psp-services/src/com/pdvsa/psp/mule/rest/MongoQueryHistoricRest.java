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


import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.converter.DateFormatParam;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.PageLogResponseImpl;
import com.pdvsa.psp.model.xml.PageOpcInfoRegisterResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;
import com.pdvsa.psp.mule.rest.exception.BadRequestException;

@Path("/")
public class MongoQueryHistoricRest {

	private MongoTemplate mongoTemplate;

	@GET
	@Produces("text/xml")
	@Path("/consultar")
	public PageResponse queryHistoricData(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta,  @QueryParam(value = "pais") String pais,	@QueryParam(value = "region") String region, @QueryParam(value = "tanque") String tanque,@QueryParam(value="variable") String variable, @QueryParam(value = "localidad") String localidad, @QueryParam("pagina") Integer pagina, @QueryParam("tamano") Integer tamano) throws BadRequestException {

		List items = new ArrayList();
		Query qry = new Query();
		Query qryCount = new Query();
		
		if(desde == null || hasta == null){
			throw new BadRequestException("Debe proporcionar un Rango de Fechas Validas!");
		}
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		if(desdeFomatted.getValue().after(hastaFomatted.getValue())){
			throw new BadRequestException("Debe proporcionar un Rango de Fechas Validas. El Valor DESDE no puede ser mayor que el valor HASTA");
		}
	
		List<Criteria> criterias = new ArrayList<Criteria>();
		
		criterias.add(Criteria.where("timestamp").gte(desdeFomatted.getValue()).lte(hastaFomatted.getValue()));
		
		if(variable == null || variable.trim().length() == 0){
			throw new BadRequestException("Debe especificar una Variable en la Consulta!");
		}else{
			criterias.add(Criteria.where("variable").regex(variable));
		}
		
		if(pais != null && pais.trim().length() > 0){
			criterias.add(Criteria.where("pais").regex(pais));
		}
		
		if(region != null && region.trim().length() > 0){
			criterias.add(Criteria.where("region").regex(region));
		}
		
		if(localidad != null && localidad.trim().length() > 0){
			criterias.add(Criteria.where("localidad").regex(localidad));
		}
		
		if(tanque != null && tanque.trim().length() > 0){
			criterias.add(Criteria.where("tanque").regex(tanque));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
		qryCount.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		long cant = getMongoTemplate().count(qryCount, "opcInfoRegisterHistoric");
		
		
		qry.with(new Sort(new Order(Direction.ASC, "timestamp")));
		
		if(pagina == null || tamano == null){
			throw new BadRequestException("Debe especificar el numero de pagina y/o cantidad de elementos por pagina.");
		}
		
		qry.with(new PageRequest(pagina, tamano));
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
				
		PageResponse response = new PageOpcInfoRegisterResponseImpl(items, pagina, tamano, cant);
		
		return response;

	}
	
	@GET
	@Produces("text/xml")
	@Path("/consultarV2")
	public PageImpl<OpcInfoRegisterMongo> findHistoricOpcData2(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta,  @QueryParam(value = "pais") String pais,	@QueryParam(value = "region") String region, @QueryParam(value = "tanque") String tanque,@QueryParam(value="variable") String variable, @QueryParam(value = "localidad") String localidad, @QueryParam("pagina") Integer pagina, @QueryParam("tamano") Integer tamano) throws BadRequestException {

		List items = new ArrayList();
		Query qry = new Query();
		Query qryCount = new Query();
		
		if(desde == null || hasta == null){
			throw new BadRequestException("Debe proporcionar un Rango de Fechas Validas!");
		}
		
		
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		if(desdeFomatted.getValue().after(hastaFomatted.getValue())){
			throw new BadRequestException("Debe proporcionar un Rango de Fechas Validas. El Valor DESDE no puede ser mayor que el valor HASTA");
		}
	
		List<Criteria> criterias = new ArrayList<Criteria>();
		
		
		
		
		criterias.add(Criteria.where("timestamp").gte(desdeFomatted.getValue()).lte(hastaFomatted.getValue()));
		
		if(variable == null || variable.trim().length() == 0){
			throw new BadRequestException("Debe especificar una Variable en la Consulta!");
		}else{
			criterias.add(Criteria.where("variable").regex(variable));
		}
		
		
		
		if(pais != null && pais.trim().length() > 0){
			criterias.add(Criteria.where("pais").regex(pais));
		}
		
		if(region != null && region.trim().length() > 0){
			criterias.add(Criteria.where("region").regex(region));
		}
		
		if(localidad != null && localidad.trim().length() > 0){
			criterias.add(Criteria.where("localidad").regex(localidad));
		}
		
		if(tanque != null && tanque.trim().length() > 0){
			criterias.add(Criteria.where("tanque").regex(tanque));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));		
		qryCount.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		long cant = getMongoTemplate().count(qryCount, "opcInfoRegisterHistoric");
		
		
		qry.with(new Sort(new Order(Direction.ASC, "timestamp")));
		
		if(pagina == null || tamano == null){
			throw new BadRequestException("Debe especificar el numero de pagina y/o cantidad de elementos por pagina.");
		}
		
		qry.with(new PageRequest(pagina, tamano));
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
				
		PageRequest page = new PageRequest(pagina, tamano);
		
		PageImpl<OpcInfoRegisterMongo> response = new PageImpl<OpcInfoRegisterMongo>(items,page, cant);
		
		return response;

	}
	
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
