package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.PageImpl;

@XmlRootElement(name="PageResponse")
public class PageOpcInfoResponseImpl implements PageResponse, Serializable {

	private static final long serialVersionUID = -9077278474894153758L;
	private  List<OpcInfoRegisterMongo> content = new ArrayList<OpcInfoRegisterMongo>();
	private  Long total;
	
	private Integer pageNumber;
	private Integer pageSize;
	
	public PageOpcInfoResponseImpl(){}
	
	public PageOpcInfoResponseImpl(List<OpcInfoRegisterMongo> content){
		this(content, null, null, null == content ? Long.valueOf(0) : content.size());
	}

	public PageOpcInfoResponseImpl(List<OpcInfoRegisterMongo> content, Integer pageNumber, Integer pageSize, Long total) {
		if (null == content) {
			throw new IllegalArgumentException("Contenido no puede ser nulo!");
		}

		this.content.addAll(content);
		this.total = total;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	
	@XmlElementWrapper(name="List")
	@XmlElement(name="Content", type=OpcInfoRegisterMongo.class)
	public List<OpcInfoRegisterMongo> getContent() {
		return content;
//		return Collections.unmodifiableList(content);
	}

	
	@XmlElement(name="numeroPagina", type= Integer.class)
	public Integer getNumber() {
		return pageNumber == null ? 0 : getPageNumber();
	}

	
	@XmlElement(name="numeroElementos", type= Integer.class)
	public Integer getNumberOfElements() {
		return content.size();
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

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageImpl<?>)) {
			return false;
		}

		PageOpcInfoResponseImpl that = (PageOpcInfoResponseImpl) obj;

		boolean totalEqual = this.total == that.total;
		boolean contentEqual = this.content.equals(that.content);
		boolean pageNumberEqual = this.pageNumber == that.pageNumber;
		boolean pageSizeEqual = this.pageSize == that.pageSize;
		
		return totalEqual && contentEqual && pageNumberEqual && pageSizeEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + (int) (total ^ total >>> 32);
		result = 31 * result + (pageSize ^ pageSize) >>> 32;
		result = 31 * result + (pageNumber ^ pageNumber) >>> 32;
		result = 31 * result + content.hashCode();

		return result;
	}

	
	
	

}
