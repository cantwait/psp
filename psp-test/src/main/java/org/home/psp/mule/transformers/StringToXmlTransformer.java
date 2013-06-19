package org.home.psp.mule.transformers;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.util.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class StringToXmlTransformer extends AbstractTransformer{

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
	
	public static void main(String... strings){
		String main = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><message>1</message></response>";
		
		String xml = new String();
		int primer = StringUtils.indexOf(main, "?");
		int ultimo = StringUtils.lastIndexOf(main, "?");
		String xml2 = StringUtils.rightPad(main, ultimo);
		
		xml = StringUtils.replace(main, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
		
		System.out.println(xml);
	}

}
