package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.OpcInfoRegister;

@WebService
@Service
public interface IItemService {
	
	@WebMethod 
	@WebResult(name="item") 
	public Item getItemById(@WebParam(name="idItem") Long idItem);	
	
	@WebMethod 
	@WebResult(name="item") 
	public Item getItemByNombre(@WebParam(name="nombre") String nombre);
	
	@WebMethod 
	@WebResult(name="items")
	public List<Item> getItemsLikeNombre(@WebParam(name="value") String value);
	
	@WebMethod 
	@WebResult(name="items")
	public List<Item> getItemsByGrupo(@WebParam(name="idGrupo") Long idGrupo);	
	
	@WebMethod 
	public boolean removeItem(@WebParam(name="idItem") Long idItem);
	
	@WebMethod 
	@WebResult(name="item") 
	public Item saveItem(@WebParam(name="item") Item item);
	
	@WebMethod
	@WebResult(name="informacion-opc")
	public List<OpcInfoRegister> findMapItemsByRegion(@WebParam(name="idRegion") Long idRegion);
	
	@WebMethod
	@WebResult(name="items")
	public List<Item> saveItems(List<Item> items);
	
	@WebMethod
	@WebResult(name="items")
	public List<Item> findUntransferredItems();
	
	@WebMethod
	@WebResult(name="item")
	public Item updateItemStatus(Item item);
	
	@WebMethod
	@WebResult(name="item")
	public Item deleteLogically(Item item);
	
}
