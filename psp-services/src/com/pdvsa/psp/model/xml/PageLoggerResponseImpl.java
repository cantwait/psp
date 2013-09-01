package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PageResponse")
public class PageLoggerResponseImpl implements PageResponse, Serializable {

	private static final long serialVersionUID = -9077278474894153758L;
	private  List<MongoLogger> list = new ArrayList<MongoLogger>();
	private  Long total;
	
	private Integer pageNumber;
	private Integer pageSize;
	
	
	public PageLoggerResponseImpl(){}
	
	public PageLoggerResponseImpl(List<MongoLogger> content){
		this(content, null, null, null == content ? Long.valueOf(0) : content.size());
	}

	public PageLoggerResponseImpl(List<MongoLogger> content, Integer pageNumber, Integer pageSize, Long total) {
		if (null == content) {
			throw new IllegalArgumentException("Contenido no puede ser nulo!");
		}

		this.list.addAll(content);
		this.total = total;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	
	@XmlElementWrapper(name="List")
	@XmlElement(name="Content", type=MongoLogger.class)
	public List<MongoLogger> getContent() {
		return list;
//		return Collections.unmodifiableList(list);
	}

	
	@XmlElement(name="numeroPagina", type= Integer.class)
	public Integer getNumber() {
		return pageNumber == null ? 0 : getPageNumber();
	}

	
	@XmlElement(name="numeroElementos", type= Integer.class)
	public Integer getNumberOfElements() {
		return list.size();
	}

	
	@XmlElement(name="tamanoPagina", type= Integer.class)
	public Integer getSize() {		
		return pageSize == null ? 0 : getPageSize();
	}
		

	@XmlElement(name="total", type=Long.class)
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total){
		this.total = total;
	}

	@XmlElement(name="totalPaginas", type= Integer.class)
	public Integer getTotalPages() {
		return getSize() == 0 ? 0 : (int) Math.ceil((double) total / (double) getSize());
	}
	
	@Override
	@XmlElement(name="pageNumber", type= Integer.class)
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(Integer pageNumber){
		this.pageNumber = pageNumber;
	}

	@Override
	@XmlElement(name="pageSize", type= Integer.class)
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer size){
		this.pageSize = size;
	}

	
	@Override
	public String toString() {

		String contentType = "UNKNOWN";

		if (list.size() > 0) {
			contentType = list.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result
				+ ((pageNumber == null) ? 0 : pageNumber.hashCode());
		result = prime * result
				+ ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageLoggerResponseImpl other = (PageLoggerResponseImpl) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (pageNumber == null) {
			if (other.pageNumber != null)
				return false;
		} else if (!pageNumber.equals(other.pageNumber))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
	
		
	

}
