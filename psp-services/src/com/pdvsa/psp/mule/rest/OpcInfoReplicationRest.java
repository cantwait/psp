package com.pdvsa.psp.mule.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdvsa.psp.model.Grupo;
import com.pdvsa.psp.model.GrupoItem;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.UnidadMedida;
import com.pdvsa.psp.model.xml.GrupoTransferencia;
import com.pdvsa.psp.model.xml.ServidorTransferencia;
import com.pdvsa.psp.service.IGrupoService;
import com.pdvsa.psp.service.IItemService;
import com.pdvsa.psp.service.IServidorService;
import com.pdvsa.psp.service.IUnidadMedidaService;

@Path("/")
public class OpcInfoReplicationRest {

	private IItemService itemService;
	private IUnidadMedidaService unidadService;
	private IGrupoService grupoService;
	private IServidorService servidorService;

	@POST
	@Path("/variable-opc")
	@Consumes("text/xml")
	@Produces("text/xml")
	public Item transferItems(Item iRef) {

		Item item = getItemService().getItemByNombre(iRef.getNombre());

		if (item == null) {
			Item nuevoItem = generarNuevoItem(iRef);

		} else {
			Item actualizarItem = actualizarItem(item, iRef);
		}

		return iRef;
	}

	@POST
	@Path("/grupo-opc")
	@Consumes("text/xml")
	@Produces("text/xml")
	public GrupoTransferencia transferGroups(GrupoTransferencia gRef) {

		Grupo grupo = getGrupoService().getGrupoByNombre(
				gRef.getGrupo().getNombre());

		if (grupo == null) {
			Grupo nuevoGrupo = generarNuevoGrupo(gRef);
		} else {
			Grupo actualizarGrupo = actualizarGrupo(grupo, gRef);
		}
		return gRef;
	}

	@POST
	@Path("/servidor-opc")
	@Consumes("text/xml")
	@Produces("text/xml")
	public ServidorTransferencia transferServers(ServidorTransferencia sRef) {
		ServidorOpc servidor = getServidorService().getServidorByNombre(
				sRef.getServidor().getNombre());
		System.out.println("NOMBRE SERVIDOR: " + sRef.getServidor().getNombre()
				+ " CANTIDAD DE TANQUES: " + sRef.getTanques().size()
				+ " CANTIDAD DE GRUPOS X SERVIDOR: "
				+ sRef.getServidorGrupo().size());
		return sRef;
	}

	private Grupo actualizarGrupo(Grupo grupo, GrupoTransferencia gRef) {

		grupo.setNombre(gRef.getGrupo().getNombre());
		grupo.setDescripcion(gRef.getGrupo().getDescripcion());
		grupo.setActivo(gRef.getGrupo().getActivo());
		grupo.setPooling(gRef.getGrupo().getPooling());

		if (gRef.getGrupoItems().size() > 0) {
			grupo.getGrupoItems().clear();
			grupo.getGrupoItems().addAll(gRef.getGrupoItems());
		}

		return grupo;
	}

	private Grupo generarNuevoGrupo(GrupoTransferencia gRef) {
		Grupo grupoNuevo = new Grupo();
		grupoNuevo.setNombre(gRef.getGrupo().getNombre());
		grupoNuevo.setDescripcion(gRef.getGrupo().getDescripcion());
		grupoNuevo.setActivo(gRef.getGrupo().getActivo());
		grupoNuevo.setPooling(gRef.getGrupo().getPooling());
		if (gRef.getGrupoItems().size() > 0) {
			grupoNuevo.getGrupoItems().addAll(gRef.getGrupoItems());
		}

		return grupoNuevo;
	}

	private Item generarNuevoItem(Item itemRef) {

		Item itemNuevo = new Item();
		itemNuevo.setNombre(itemRef.getNombre());
		itemNuevo.setDescripcion(itemRef.getDescripcion());
		itemNuevo.setHda(itemRef.getHda());
		itemNuevo.setActivo(itemRef.isActivo());
		itemNuevo.setItemOpc(itemRef.getItemOpc());
		itemNuevo.setTipoDato(itemRef.getTipoDato());
		itemNuevo.setTipoItem(itemRef.getTipoItem());
		itemNuevo.setVersion(itemRef.getVersion());

		if (itemRef.getUnidadMedida() != null) {
			UnidadMedida um = getUnidadService().getUnidadMedidaByNombre(
					itemRef.getUnidadMedida().getNombre());
			if (um != null) {
				itemNuevo.setUnidadMedida(um);
			}
		}

		return itemNuevo;
	}

	private Item actualizarItem(Item itemLocal, Item itemRef) {

		itemLocal.setNombre(itemRef.getNombre());
		itemLocal.setDescripcion(itemRef.getDescripcion());
		itemLocal.setHda(itemRef.getHda());
		itemLocal.setActivo(itemRef.isActivo());
		itemLocal.setItemOpc(itemRef.getItemOpc());
		itemLocal.setTipoDato(itemRef.getTipoDato());
		itemLocal.setTipoItem(itemRef.getTipoItem());
		itemLocal.setVersion(itemRef.getVersion());

		if (itemRef.getUnidadMedida() != null) {
			UnidadMedida um = getUnidadService().getUnidadMedidaByNombre(
					itemRef.getUnidadMedida().getNombre());
			if (um != null) {
				itemLocal.setUnidadMedida(um);
			}
		}

		return itemLocal;
	}

	public IItemService getItemService() {
		return itemService;
	}

	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public IUnidadMedidaService getUnidadService() {
		return unidadService;
	}

	public void setUnidadService(IUnidadMedidaService unidadService) {
		this.unidadService = unidadService;
	}

	public IGrupoService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(IGrupoService grupoService) {
		this.grupoService = grupoService;
	}

	public IServidorService getServidorService() {
		return servidorService;
	}

	public void setServidorService(IServidorService servidorService) {
		this.servidorService = servidorService;
	}

}
