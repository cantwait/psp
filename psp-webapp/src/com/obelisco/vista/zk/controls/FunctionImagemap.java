package com.obelisco.vista.zk.controls;

import java.io.IOException;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Imagemap;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Vbox;

import com.obelisco.modelo.data.seguridad.Operacion;

public class FunctionImagemap extends Button implements AfterCompose {
	
	private Operacion operacion;
	
	private void initControl() {

		setLabel(operacion.getNombre());
		setMold("os");
		setStyle("background-color:#fff000;border: 1px solid;	font-size: 9px;");

		if (operacion.getIcono().getContenido() != null && operacion.getIcono().getContenido().length > 0) {
			//TODO: Falta Colocar el Codigo Necesario para que muestre los botones en funcion a lo que esta almacenado en la base de datos
			try {
				AImage image = new AImage(operacion.getNombre(),operacion.getIcono().getContenido());
				setImageContent(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				Changed by setSrc() which is deprecated
				setImage(OperacionHelper.getOperacion(OperacionHelper.getType(operacion)).getIcono().getNombre());
			}
			
		} else {
			setImage(OperacionHelper.getOperacion(OperacionHelper.getType(operacion)).getIcono().getNombre());
		}
		
	}
	
	public FunctionImagemap(Operacion operacion) {
		super();
		this.operacion = operacion;
		initControl();
	}

	public Operacion getOperacion() {
		return operacion;
	}

	//@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		// Popup popup = new Popup();
		// Vbox vbox = new Vbox();
		// popup.appendChild(vbox);
		// popup.setWidth("300px");
		// Label label = new Label(operacion.getNombre());
		// label.setParent(vbox);
		//
		// setTooltip(popup);
	}

}
