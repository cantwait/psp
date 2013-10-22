package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.PageImpl;

@XmlRootElement(name="PageResponse")
public class PageOpcInfoRegisterResponseImpl implements PageResponse, Serializable {

	private static final long serialVersionUID = -9077278474894153758L;
	private  List<OpcInfoRegisterMongo> content = new ArrayList<OpcInfoRegisterMongo>();
	private  Long totalElements;
	private Integer pageNumber;
	private Integer pageSize;
	
	public PageOpcInfoRegisterResponseImpl(){}
	
	public PageOpcInfoRegisterResponseImpl(List<OpcInfoRegisterMongo> content){
		this(content, null, null, null == content ? Long.valueOf(0) : content.size());
	}

	public PageOpcInfoRegisterResponseImpl(List<OpcInfoRegisterMongo> content, Integer pageNumber, Integer pageSize, Long total) {
		if (null == content) {
			throw new IllegalArgumentException("Contenido no puede ser nulo!");
		}

		this.content.addAll(content);
		this.totalElements = total;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	
	@XmlElementWrapper(name="List")
	@XmlElement(name="content", type=OpcInfoRegisterMongo.class)
	public List<OpcInfoRegisterMongo> getContent() {
		return content;
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

		return String.format("Page %s of %d containing %s instances", getPageNumber(), getTotalElements(), contentType);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageImpl<?>)) {
			return false;
		}

		PageOpcInfoRegisterResponseImpl that = (PageOpcInfoRegisterResponseImpl) obj;

		boolean totalEqual = this.totalElements == that.totalElements;
		boolean contentEqual = this.content.equals(that.content);
		boolean pageNumberEqual = this.pageNumber == that.pageNumber;
		boolean pageSizeEqual = this.pageSize == that.pageSize;
		
		return totalEqual && contentEqual && pageNumberEqual && pageSizeEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + (int) (totalElements ^ totalElements >>> 32);
		result = 31 * result + (pageSize ^ pageSize) >>> 32;
		result = 31 * result + (pageNumber ^ pageNumber) >>> 32;
		result = 31 * result + content.hashCode();

		return result;
	}

	@Override
	@XmlElement(name="totalElements", type= Long.class)
	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	
	
	

}
