package com.pdvsa.psp.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateConverterMapper implements Converter{

	@Override
	public boolean canConvert(Class arg0) {
		
		return Date.class.isAssignableFrom(arg0);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext arg2) {
		 Date fecha = (Date) value; 
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
         writer.setValue(formatter.format(fecha));  
		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext arg1) {
		 Date date = null; 
	     try { 
	         date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(reader.getValue()); 
	     } catch (ParseException e) { 
	         throw new ConversionException(e.getMessage(), e); 
	     } 
	     return date; 
	}

}
