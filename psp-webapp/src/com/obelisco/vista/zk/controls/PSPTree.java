package com.obelisco.vista.zk.controls;

import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import com.obelisco.exception.ObeliscoException;
import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.model.ObjetoNulo;
import com.pdvsa.psp.model.Pais;
import com.pdvsa.psp.model.Region;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.service.ILocalidadService;
import com.pdvsa.psp.service.IPaisService;
import com.pdvsa.psp.service.IRegionService;
import com.pdvsa.psp.service.IServidorService;
import com.pdvsa.psp.service.ITanqueService;

public class PSPTree extends Tree implements AfterCompose {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271364193951273605L;

	protected void sendEvent(Object nodo) throws Exception {
		Events.postEvent("onSelectNode", this, nodo);
	}

	EventListener selectListener = new EventListener() {

		public void onEvent(Event event) throws UiException {

			try {

				Treecell cell = (Treecell) event.getTarget();
				Object value = ((Treeitem) cell.getParent().getParent())
						.getValue();

				if (value instanceof Tanque) {
					Tanque tanque = (Tanque) value;					
					sendEvent(tanque);

				} else if (value instanceof ServidorOpc) {

					ServidorOpc servidor = (ServidorOpc) value;
					crearHijosServidor(servidor);
					sendEvent(servidor);

				} else if (value instanceof Localidad) {

					Localidad localidad = (Localidad) value;
					crearHijosLocalidad(localidad);
					sendEvent(localidad);

				} else if (value instanceof Region) {

					Region region = (Region) value;
					crearHijosRegion(region);
					sendEvent(region);

				} else if (value instanceof Pais) {

					Pais pais = (Pais) value;
					crearHijosPais(pais);
					sendEvent(pais);
				} else if (value instanceof ObjetoNulo){
					sendEvent(value);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ObeliscoException(e.getMessage());

			}

		}

		

	};

	private void crearHijosServidor(ServidorOpc servidor) {
		ITanqueService tanqueService = (ITanqueService) SpringUtil.getBean("tanqueService");

		List<Tanque> tanques = tanqueService.getTanquesByServidorActivo(servidor.getId());
		Treeitem padre = this.getSelectedItem();
		crearTreeByServidor(padre, tanques);
		
	}

	private void crearTreeByServidor(Treeitem padre, List<Tanque> tanques) {
		if (tanques != null) {

			Treechildren itemsTanques = padre.getTreechildren();

			if (itemsTanques == null) {
				itemsTanques = new Treechildren();
			}
			
			itemsTanques.getChildren().clear();			
			

			for (Iterator<?> p = tanques.iterator(); p.hasNext();) {

				Tanque itemTanque = (Tanque) p.next();

				Treeitem itemHijoTanque = createItemTanque(itemTanque);

				itemHijoTanque.setParent(itemsTanques);
			}
			itemsTanques.setParent(padre);
		}
		
		
	}	
	
	public void crearTreeByPais(Treeitem itemSeleccionado,
			List<Region> itemsHijos) {

		if (itemsHijos != null) {

			Treechildren itemsRegiones = itemSeleccionado.getTreechildren();

			if (itemsRegiones == null) {
				itemsRegiones = new Treechildren();
			}
			
			itemsRegiones.getChildren().clear();			
			

			for (Iterator<?> P = itemsHijos.iterator(); P.hasNext();) {

				Region itemRegion = (Region) P.next();

				Treeitem itemHijoRegion = createItemRegion(itemRegion);

				itemHijoRegion.setParent(itemsRegiones);
			}
			itemsRegiones.setParent(itemSeleccionado);
		}
	}

	public void crearTreeByRegion(Treeitem itemSeleccionado,
			List<Localidad> itemsHijos) {

		if (itemsHijos != null) {

			Treechildren itemsLocalidades = itemSeleccionado.getTreechildren();

			if (itemsLocalidades == null) {
				itemsLocalidades = new Treechildren();
			}
			
			itemsLocalidades.getChildren().clear();			

			for (Iterator<?> P = itemsHijos.iterator(); P.hasNext();) {

				Localidad itemLocalidad = (Localidad) P.next();

				Treeitem itemHijoRegion = createItemLocalidad(itemLocalidad);

				itemHijoRegion.setParent(itemsLocalidades);
			}
			itemsLocalidades.setParent(itemSeleccionado);
		}
	}

	public void crearTreeByLocalidad(Treeitem itemSeleccionado,
			List<ServidorOpc> itemsHijos) {

		if (itemsHijos != null) {

			Treechildren itemsServidores = itemSeleccionado.getTreechildren();

			if (itemsServidores == null) {
				itemsServidores = new Treechildren();
			}

			itemsServidores.getChildren().clear();

			for (Iterator<?> P = itemsHijos.iterator(); P.hasNext();) {

				ServidorOpc itemServidor = (ServidorOpc) P.next();

				Treeitem itemHijoServidor = createItemServidor(itemServidor);

				itemHijoServidor.setParent(itemsServidores);
			}
			
			itemsServidores.setParent(itemSeleccionado);
		}
	}

	public void crearHijosPais(Pais paisSeleccionado) {

		IRegionService servicioRegion = (IRegionService) SpringUtil
				.getBean("regionService");
		List<Region> regiones = servicioRegion.getRegionByPais(paisSeleccionado
				.getId(), true);

		Treeitem padre = this.getSelectedItem();
		crearTreeByPais(padre, regiones);

	}

	public void crearHijosRegion(Region regionSeleccionado) {

		ILocalidadService servicioLocalidad = (ILocalidadService) SpringUtil
				.getBean("localidadService");

		List<Localidad> regiones = servicioLocalidad.getLocalidadesByRegion(
				regionSeleccionado.getId(), true);

		Treeitem padre = this.getSelectedItem();
		crearTreeByRegion(padre, regiones);

	}

	public void crearHijosLocalidad(Localidad localidadSeleccionado) {

		IServidorService servicioServidor = (IServidorService) SpringUtil
				.getBean("servidorService");

		List<ServidorOpc> servidores = servicioServidor
				.getServidoresByLocalidad(localidadSeleccionado.getId(), true);
		Treeitem padre = this.getSelectedItem();
		crearTreeByLocalidad(padre, servidores);

	}

	protected Treeitem createNodoMaestro() {

		Treeitem treeItem = new Treeitem();

		String srcImg = "/images/icons/home.png";

		treeItem.setValue(new ObjetoNulo());
		treeItem.setTooltiptext("PDVSA");

		Treerow treerow = new Treerow();
		treerow.setParent(treeItem);

		Treecell treecell = new Treecell("PDVSA", srcImg);

		treecell.addEventListener(Events.ON_CLICK, selectListener);

		treecell.setParent(treerow);

		return treeItem;
	}

	protected Treeitem createItemRegion(Region itemRegion) {

		Treeitem treeItem = new Treeitem();

		if (itemRegion != null) {

			String srcImgHijo = "/images/icons/clients.png";

			treeItem.setValue(itemRegion);
			treeItem.setTooltiptext(itemRegion.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(itemRegion.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	protected Treeitem createItemLocalidad(Localidad itemLocalidad) {

		Treeitem treeItem = new Treeitem();

		if (itemLocalidad != null) {

			String srcImgHijo = "/images/icons/server_client.png";

			treeItem.setValue(itemLocalidad);
			treeItem.setTooltiptext(itemLocalidad.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(itemLocalidad.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	protected Treeitem createItemServidor(ServidorOpc itemServidor) {

		Treeitem treeItem = new Treeitem();

		if (itemServidor != null) {

			String srcImgHijo = "/images/icons/server.png";

			treeItem.setValue(itemServidor);
			treeItem.setTooltiptext(itemServidor.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(itemServidor.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	protected Treeitem createItemTanque(Tanque itemTanque) {

		Treeitem treeItem = new Treeitem();

		if (itemTanque != null) {

			String srcImgHijo = "/images/icons/window.png";

			treeItem.setValue(itemTanque);
			treeItem.setTooltiptext(itemTanque.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(itemTanque.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);
			treecellchild.addEventListener(Events.ON_SELECTION, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	protected Treeitem createItemPais(Pais itemPais) {

		Treeitem treeItem = new Treeitem();

		if (itemPais != null) {

			String srcImgHijo = "/images/icons/environment.png";

			treeItem.setValue(itemPais);
			treeItem.setTooltiptext(itemPais.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(itemPais.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);
			treecellchild.addEventListener(Events.ON_SELECTION, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	public void createTree(Treechildren children, List<Pais> listPaises) {

		Treeitem item = createNodoMaestro();
		item.setParent(children);

		if (listPaises != null) {


			Treechildren itemsPais = new Treechildren();

			for (Iterator<?> P = listPaises.iterator(); P.hasNext();) {

				Pais itemPais = (Pais) P.next();

				Treeitem itemHijoPais = createItemPais(itemPais);

				itemHijoPais.setParent(itemsPais);
			}

			itemsPais.setParent(item);

		}

	}

	public Object getRootValue() {
		Object rootValue = null;
		Component root = this.getTreechildren().getFirstChild();

		if (root instanceof Treeitem) {
			Treeitem rootItem = (Treeitem) root;
			rootValue = rootItem.getValue();
		}

		return rootValue;
	}

	public void refreshTree(OperationType operacion, Object clase)
			throws Exception {

		// Se buscar en el arbol el TreeItem correspondiente al nodo que se va a
		// Renerar
		Treeitem item = this.getSelectedItem();

		if (operacion == OperationType.ELIMINAR) {
			Treeitem parent = item.getParentItem();
			parent.getTreechildren().removeChild(item);
			selectItem(parent);
			sendEvent(parent.getValue());
		} else if (operacion == OperationType.INCLUIR) {
			Treeitem newItem = new Treeitem();
			if (clase instanceof Region) {
				Region itemRegion = (Region) clase;
				newItem = createItemRegion(itemRegion);
			} else if (clase instanceof Localidad) {
				Localidad itemLocalidad = (Localidad) clase;
				newItem = createItemLocalidad(itemLocalidad);
			} else if (clase instanceof ServidorOpc) {
				ServidorOpc itemServidor = (ServidorOpc) clase;
				newItem = createItemServidor(itemServidor);
			} else if (clase instanceof Pais) {
				Pais itemPais = (Pais) clase;
				newItem = createItemPais(itemPais);
			}
			
			if (item.getTreechildren() == null) {
				Treechildren newtreechildren = new Treechildren();
				newtreechildren.setParent(item);
			}
			item.getTreechildren().appendChild(newItem);
			selectItem(newItem);
			sendEvent(clase);
		} else if (operacion == OperationType.MODIFICAR) {
			if (clase instanceof Region) {
				Region itemRegion = (Region) clase;
				item.setTooltiptext(itemRegion.getDescripcion());
				Treecell cell = (Treecell) item.getFirstChild().getFirstChild();
				cell.setLabel(itemRegion.getNombre());
			} else if (clase instanceof Localidad) {
				Localidad itemLocalidad = (Localidad) clase;
				item.setTooltiptext(itemLocalidad.getDescripcion());
				Treecell cell = (Treecell) item.getFirstChild().getFirstChild();
				cell.setLabel(itemLocalidad.getNombre());
			} else if (clase instanceof ServidorOpc) {
				ServidorOpc itemServidorOpc = (ServidorOpc) clase;
				item.setTooltiptext(itemServidorOpc.getDescripcion());
				Treecell cell = (Treecell) item.getFirstChild().getFirstChild();
				cell.setLabel(itemServidorOpc.getNombre());
			} else if (clase instanceof Tanque) {
				Tanque itemTanque = (Tanque) clase;
				item.setTooltiptext(itemTanque.getDescripcion());
			} else if (clase instanceof Pais) {
				Pais itemPais = (Pais) clase;
				item.setTooltiptext(itemPais.getDescripcion());
				Treecell cell = (Treecell) item.getFirstChild().getFirstChild();
				cell.setLabel(itemPais.getNombre());
			}
			selectItem(item);
			sendEvent(clase);
		}

	}

	public void afterCompose() {

		Treechildren children = this.getTreechildren();
		children.getItems().clear();
		final Execution exec = Executions.getCurrent();
		if (exec != null) {

			IPaisService servicioPais = (IPaisService) SpringUtil
					.getBean("paisService");

			List<Pais> paises = servicioPais.getPaisesLikeNombre("");

			createTree(children, paises);

			selectItem((Treeitem) children.getFirstChild());

		}

	}

}
