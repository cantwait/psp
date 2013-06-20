package com.pdvsa.psp.mule.transformer;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.util.StringUtils;

public class CleanStringXMLTransformer extends AbstractTransformer{
	
	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		
		if(src instanceof String){
			
			String xml = new String();			
			xml = StringUtils.replace(src.toString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
			
			return xml;
		
		}
		return src;
	}

}
