package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.service.IItemService;

public class OpcItemRestService {
	
	@Autowired
	private IItemService itemService;
	
	public List<Item> transferItems(ArrayList<Item> items){
		List<Item> itemsOpc = new ArrayList<Item>();
		
		
		
		
		
		return itemsOpc;
	}
		
	

}
