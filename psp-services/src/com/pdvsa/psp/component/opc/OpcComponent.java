package com.pdvsa.psp.component.opc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class OpcComponent implements Comparable<OpcComponent>{
	protected final Log logger = LogFactory.getLog(getClass());
	private Long id;
	private String name;
	
	public OpcComponent(Long id, String name) {
		this.id = id;
		this.name = name;
	}	
	
	protected Long getId() {
		return id;
	}
	
	protected String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}

	public boolean equals(OpcComponent obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof OpcComponent) {
			OpcComponent objComponent = (OpcComponent) obj;
			return equals(objComponent);
		}
		return false;
	}
	
	@Override
	public int compareTo(OpcComponent opcComp) {
		return this.id.compareTo(opcComp.getId());
	}

	@Override
	public String toString() {
		return name;
	}
	
}
