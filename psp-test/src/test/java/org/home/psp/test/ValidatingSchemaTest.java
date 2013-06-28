package org.home.psp.test;


import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidatingSchemaTest
{
    public static void main(String [] args) throws Exception
    {
        Source schemaFile = new StreamSource(new File("C://schemas//opcinforequest.xsd"));
        Source xmlFile = new StreamSource(new File("C://schemas//opcrequest.xml"));

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        try{
            validator.validate(xmlFile);
            System.out.println("Document is valid");
        }
        catch (SAXException e) 
        {
            System.out.println("Document is NOT valid");
            System.out.println("Reason: " + e.getLocalizedMessage());
        }
    }
}
