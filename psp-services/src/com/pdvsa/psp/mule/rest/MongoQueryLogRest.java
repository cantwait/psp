package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jinterop.dcom.test.SysInfoEvents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.converter.DateFormatParam;
import com.pdvsa.psp.model.xml.MongoLogger;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.PageLogResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;

@Path("/")
public class MongoQueryLogRest {
	
	private MongoTemplate mongoTemplate;
	
	@GET
	@Produces("text/xml")
	@Path("/consultar")
	@XmlJavaTypeAdapter(PageAdapter.class)
	public PageResponse queryLogData(@QueryParam("desde") String desde, @QueryParam("hasta") String hasta, @QueryParam("evento") String evento, @QueryParam("pagina") Integer pagina, @QueryParam("tamano")Integer tamano){
		
		List<MongoLogger> items = new ArrayList<MongoLogger>();
		Query qry = new Query();
		
		Query qryCount = new Query();
		
		DateFormatParam desdeFomatted = new DateFormatParam(desde);
		DateFormatParam hastaFomatted = new DateFormatParam(hasta);
		
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("fecha").gte(desdeFomatted.getValue()));
		criterias.add(Criteria.where("fecha").lte(hastaFomatted.getValue()));
		
		if(evento != null && evento.length() > 0){
			criterias.add(Criteria.where("tipoEvento").regex(evento));
		}
		
		qry.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		qryCount.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
		
		qry.with(new Sort(new Order(Direction.ASC, "fecha")));		
		qry.with(new PageRequest(pagina, tamano));
		
		long cant = getMongoTemplate().count(qryCount, "opcErrorTransaction");
				
		items = getMongoTemplate().find(qry, MongoLogger.class, "opcErrorTransaction");
		
		PageResponse resp = new PageLogResponseImpl(items, pagina, tamano, cant);
		
		
		return resp;
		
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
