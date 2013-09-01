package com.obelisco.vista.zk.controls;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
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

import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Transaccion.TipoTransaccion;
import com.pdvsa.psp.service.ISecurityService;

public class AllFunctionsTree extends Tree implements AfterCompose {

	private ISecurityService securityService;

	protected void sendEvent(Object nodo) {

		if (nodo instanceof Transaccion) {
			Transaccion transaccion = (Transaccion) nodo;
			if (transaccion.getTipoTransaccion().equals(TipoTransaccion.FOLDER)
					|| transaccion.getTipoTransaccion().equals(
							TipoTransaccion.ROOT)) {
				Treeitem item = this.getSelectedItem();

				List<Transaccion> nodosHijos = null;

				nodosHijos = securityService.getChildremByFather(transaccion);
				if (nodosHijos != null && nodosHijos.size() > 0) {
					Collections.sort(nodosHijos, new Comparator<Transaccion>() {

						@Override
						public int compare(Transaccion o1, Transaccion o2) {
							int i1 = ((Transaccion) o1).getCodigo().intValue();
							int i2 = ((Transaccion) o2).getCodigo().intValue();

							return Math.abs(i1) - Math.abs(i2);
						}

					});

					createChildrem(item, nodosHijos);

				}

			}
		}

		Events.postEvent("onSelectNode", this, nodo);
	}

	protected void createChildrem(Treeitem item, Collection<Transaccion> nodos) {

		if (nodos != null && nodos.size() > 0) {
			Treechildren itemsHijos = item.getTreechildren();

			if (itemsHijos == null) {
				itemsHijos = new Treechildren();
			}
			itemsHijos.getChildren().clear();
			for (Iterator i = nodos.iterator(); i.hasNext();) {

				Transaccion nodoHijo = (Transaccion) i.next();
				Treeitem itemHijo = createBranch(nodoHijo);
				itemHijo.setParent(itemsHijos);

			}

			itemsHijos.setParent(item);
		}

	}
	
	protected Treeitem createBranch(Transaccion nodo) {
		Treeitem treeItem = null;
		if (nodo != null) {

			treeItem = createLeave(nodo);

		}

		return treeItem;

	}
	
	protected Treeitem createLeave(Transaccion nodo) {
		Treeitem treeItem = new Treeitem();

		if (nodo != null) {

			treeItem.setValue(nodo);
			Treerow treerow = createRow(nodo);
			treerow.setParent(treeItem);

		}

		return treeItem;
	}
	
	protected Treerow createRow(Transaccion nodo) {
		Treerow treerow = new Treerow();
		Treecell treecell = null;
		treecell = new Treecell(nodo.getNombre());	
		treecell.setTooltiptext(nodo.getDescripcion());
		treecell.addEventListener(Events.ON_CLICK, selectListener);
		treecell.setParent(treerow);
		return treerow;
	}

	EventListener selectListener = new EventListener() {

		public boolean isAsap() {
			return true;
		}

		public void onEvent(Event event) throws UiException {

			try {

				Treecell cell = (Treecell) event.getTarget();

				Object value = ((Treeitem) cell.getParent().getParent())
						.getValue();

				if (value instanceof Transaccion) {

					Transaccion transaccion = (Transaccion) value;
					sendEvent(transaccion);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	};

	protected Treeitem createNodoCarpeta(Transaccion carpeta) {

		Treeitem treeItem = new Treeitem();

		if (carpeta != null) {

			String srcImg = "/images/icons/folder_window.png";

			treeItem.setValue(carpeta);
			treeItem.setTooltiptext(carpeta.getDescripcion());

			Treerow treerow = new Treerow();
			treerow.setParent(treeItem);

			Treecell treecell = new Treecell(carpeta.getNombre(), srcImg);

			treecell.addEventListener(Events.ON_CLICK, selectListener);

			treecell.setParent(treerow);
		}

		return treeItem;
	}

	protected Treeitem createNodoTransaccion(Transaccion transaccion) {

		Treeitem treeItem = new Treeitem();

		if (transaccion != null) {

			String srcImgHijo = "/images/icons/window.png";

			treeItem.setValue(transaccion);
			treeItem.setTooltiptext(transaccion.getDescripcion());

			Treerow treerowchild = new Treerow();
			treerowchild.setParent(treeItem);

			Treecell treecellchild = new Treecell(transaccion.getNombre(),
					srcImgHijo);

			treecellchild.addEventListener(Events.ON_CLICK, selectListener);

			treecellchild.setParent(treerowchild);

		}

		return treeItem;

	}

	public void createTree(Treechildren treeChildren, Transaccion carpeta) {
		Treeitem branch = createBranch(carpeta);
		branch.setParent(treeChildren);

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

	public void refreshTree(OperationType operacion, Object nodo) {

		// Se buscar en el arbol el TreeItem correspondiente al nodo que se va a
		// Renerar
		Treeitem item = this.getSelectedItem();

		if (operacion == OperationType.ELIMINAR) {
			Treeitem parent = item.getParentItem();
			parent.getTreechildren().removeChild(item);
			selectItem(parent);
			sendEvent(parent.getValue());
		} else if (operacion == OperationType.INCLUIR) {

		} else if (operacion == OperationType.MODIFICAR) {

			Transaccion transaccion = (Transaccion) nodo;
			item.setTooltiptext(transaccion.getDescripcion());
			Treecell cell = (Treecell) item.getFirstChild().getFirstChild();
			cell.setLabel(transaccion.getNombre());

			selectItem(item);
			sendEvent(nodo);
		}

	}

	public void afterCompose() {
		Treechildren children = this.getTreechildren();
		if (children == null) {
			children = new Treechildren();
			children.setParent(this);
		} else {
			children.getChildren().clear();
		}

		securityService = (ISecurityService) SpringUtil.getBean("securityService");

		Transaccion carpeta = securityService.getRootTransaction();
		createTree(children, carpeta);
		

	}

}
