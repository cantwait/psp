package com.pdvsa.psp.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

public class DateFormatParam extends AbstractParam<Date>{
	
	 private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public DateFormatParam(String param) throws WebApplicationException {
		super(param);
	}

	@Override
	protected Date parse(String param) throws Throwable {
		return SDF.parse(param);
	}

}
