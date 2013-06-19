package com.obelisco.vista.zk.components;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;

import com.obelisco.modelo.data.seguridad.Operacion;
import com.obelisco.vista.zk.controls.ActionType;
import com.obelisco.vista.zk.controls.Menubar;
import com.obelisco.vista.zk.controls.OperacionHelper;
import com.obelisco.vista.zk.controls.Toolbar;
import com.obelisco.vista.zk.controls.OperationType;

public class SimpleCatalogDialog extends GenericDialog implements AfterCompose {

	@Override
	public boolean checkData() throws InterruptedException {
		// TODO Auto-generated method stub
		boolean hasSelected = false;

		if (lstbox.isMultiple()) {
			
			if (lstbox.getSelectedCount() > 1) {
				hasSelected = true;

			} else {

				if (selectedEntity != null) {
					hasSelected = true;
				}

			}

		} else {

			if (selectedEntity != null) {
				hasSelected = true;
			}

		}
		
		if (!hasSelected) showMessage("No ha seleccionado Elementos de la Lista");

		return hasSelected;

	}

	private Object selectedEntity;

	private Listbox lstbox;

	@Override
	public void afterCompose() {

		ConventionWires.wireVariables(this, this);

		// NO need to register onXxx event listeners
		// auto forward
		ConventionWires.addForwards(this, this);

	}

	@Override
	public void confirmData() throws Exception {
		// TODO Auto-generated method stub
		super.confirmData();
		// TODO: Se Obtienen los Datos seleccionados
		Collection selectionList = new ArrayList();

		if (lstbox.isMultiple()) {

			if (lstbox.getSelectedCount() > 1) {

				Set selectedListbox = lstbox.getSelectedItems();

				ListModel listModel = lstbox.getModel();

				for (Iterator i = selectedListbox.iterator(); i.hasNext();) {
					Listitem item = (Listitem) i.next();

					if (listModel != null) {

						if (listModel.getElementAt(item.getIndex()) != null) {
							Object valor = listModel.getElementAt(item
									.getIndex());
							selectionList.add(valor);
						}

					}

				}

			} else {

				if (selectedEntity != null) {
					selectionList.add(selectedEntity);
				}

			}

		} else {

			if (selectedEntity != null) {
				selectionList.add(selectedEntity);
			}

		}

		setAttribute("CATALOG_RESULT", selectionList);

	}

	public Object getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(Object selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

}