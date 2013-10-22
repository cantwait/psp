package com.pdvsa.psp.model.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;




@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({ PageOpcInfoRegisterResponseImpl.class, PageLogResponseImpl.class })
@XmlRootElement(name="PageResponse")
public interface PageResponse {
	
	public Long getTotalElements();	
	public Integer getPageNumber();
	public Integer getPageSize();
	
	

}
