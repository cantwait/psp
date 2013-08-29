package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@XmlRootElement(name="PageResponse")
public class PageLoggerResponseImpl implements Serializable {

	private static final long serialVersionUID = -9077278474894153758L;
	private  List<MongoLogger> content = new ArrayList<MongoLogger>();
	private  Pageable pageable;
	private  Long total;
	
	public PageLoggerResponseImpl(){}
	
	public PageLoggerResponseImpl(List<MongoLogger> content){
		this(content, null, null == content ? Long.valueOf(0) : content.size());
	}

	public PageLoggerResponseImpl(List<MongoLogger> content, Pageable pageable, Long total) {
		if (null == content) {
			throw new IllegalArgumentException("Contenido no puede ser nulo!");
		}

		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
	}

	
	@XmlElementWrapper(name="List")
	@XmlElement(name="Content", type=MongoLogger.class)
	public List<MongoLogger> getContent() {
		return Collections.unmodifiableList(content);
	}

	
	@XmlElement(name="numeroPagina")
	public int getNumber() {
		return pageable == null ? 0 : pageable.getPageNumber();
	}

	
	@XmlElement(name="numeroElementos")
	public int getNumberOfElements() {
		return content.size();
	}

	
	@XmlElement(name="tamanoPagina")
	public int getSize() {
		
		return pageable == null ? 0 : pageable.getPageSize();
	}

	@XmlElement(name="total")
	public long getTotalElements() {
		return total;
	}

	@XmlElement(name="totalPaginas")
	public int getTotalPages() {
		return getSize() == 0 ? 0 : (int) Math.ceil((double) total / (double) getSize());
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

		PageLoggerResponseImpl that = (PageLoggerResponseImpl) obj;

		boolean totalEqual = this.total == that.total;
		boolean contentEqual = this.content.equals(that.content);
		boolean pageableEqual = this.pageable == null ? that.pageable == null : this.pageable.equals(that.pageable);

		return totalEqual && contentEqual && pageableEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + (int) (total ^ total >>> 32);
		result = 31 * result + (pageable == null ? 0 : pageable.hashCode());
		result = 31 * result + content.hashCode();

		return result;
	}
	
	
	

}
