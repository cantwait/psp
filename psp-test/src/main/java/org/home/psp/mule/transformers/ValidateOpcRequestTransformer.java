package org.home.psp.mule.transformers;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidateOpcRequestTransformer extends AbstractTransformer{

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		if(src != null && src instanceof String){			
			String xmlPayload = (String) src;			
//			URL schemaFile = new URL("http://localhost:8081/schemas/opcinforegister.xsd");
			Source schemaFile = new StreamSource(new File("C://schemas//opcinforequest.xsd"));
//	        Source xmlFile = new StreamSource(new File("C://schemas//opcrequest.xml"));
			Source xmlSource = new StreamSource(new StringReader(xmlPayload));
	        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	       

	        try{
	        	Schema schema = schemaFactory.newSchema(schemaFile);
	  	        Validator validator = schema.newValidator();
	            validator.validate(xmlSource);
	            System.out.println("Document is valid");
	        }
	        catch (SAXException e) 
	        {	        	
	            System.out.println(xmlSource.getSystemId() + " is NOT valid");
	            System.out.println("Reason: " + e.getLocalizedMessage());
	            return e;
	          
	        } catch (IOException e) {				
				e.printStackTrace();
				return e;
			}
		}
		return src;
	}

}
