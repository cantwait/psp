package com.pdvsa.psp.model.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;




@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({ PageOpcInfoResponseImpl.class, PageLoggerResponseImpl.class })
@XmlRootElement(name="PageResponse")
public interface PageResponse {
	
	public Integer getNumber();	
	public Integer getNumberOfElements();
	public Integer getSize();
	public Long getTotal();
	public Integer getTotalPages();	
	public Integer getPageNumber();
	public Integer getPageSize();
	
	
	public void setTotal(Long total);
	public void setPageNumber(Integer pageNumber);
	public void setPageSize(Integer size);
	

}
