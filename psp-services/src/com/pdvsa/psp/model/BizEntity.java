package com.pdvsa.psp.model;

import java.io.Serializable;

public interface BizEntity extends Serializable {
	
	public boolean isNew();

	public Long getId();

	public void setId(Long id);
}