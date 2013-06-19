package org.home.psp.mule.transformers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.home.psp.data.OpcInfoRegister;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class JSONArrayToOpcInfoArray extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		
		Gson gson = new Gson();
		
		
		 Type type = new TypeToken<List<OpcInfoRegister>>(){}.getType();
         List<OpcInfoRegister> list =  gson.fromJson(src.toString(), type );
         
//        for (OpcInfoRegister opcInfoRegister : list) {
//			System.out.println(" ID: " + opcInfoRegister.getStationId()+ " TagValue: " + opcInfoRegister.getRegValue());
//		}	
		
		return list;
	}
}
