package com.pdvsa.psp.mule.transformer;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;


import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class JSONArrayToOpcInfoArray extends AbstractTransformer{
	
	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		
		 Type type = new TypeToken<List<OpcInfoRegisterMongo>>(){}.getType();
         List<OpcInfoRegisterMongo> list =  gson.fromJson(src.toString(), type );
         
		return list;
	}

}