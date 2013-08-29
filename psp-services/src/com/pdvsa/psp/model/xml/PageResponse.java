package com.pdvsa.psp.model.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;




@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({ OpcInfoRegisterMongo.class, MongoLogger.class }) 
public interface PageResponse {
	
	public int getNumber();	
	public int getNumberOfElements();
	public int getSize();
	public long getTotalElements();
	public int getTotalPages();

}
