package com.obelisco.vista.zk.controls;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Menupopup;

import com.pdvsa.psp.model.Operacion;

public class Menubar extends org.zkoss.zul.Menubar implements AfterCompose{
	
	
	private Menupopup funciones;


	public Menubar() {
		super();
	}

	@Override
	public void afterCompose() {
		
		
		Menupopup menu = null;

		for (int i = 10; i > 0; i--) {

			String menuName = "funciones" + String.valueOf(i);
			menu = (Menupopup) this.getFellowIfAny(	menuName);
			if (menu != null) {
				break;
			}

		}
		
		if (menu != null) {
			funciones = menu;
		} else {
			funciones = (Menupopup) this.getFellowIfAny("funciones");
		}
		
		if (funciones != null) {
			crearFuncionesDefecto();
		}

	}
	
//	TODO AQUI PODEMOS VALIDAR QUE USUARIO LE TOCA CUAL OPERACION...
	public void createFunctions( Collection<Operacion> operaciones) {
		
		funciones.getChildren().clear();
		
		for (Operacion operacion : operaciones) {			
			FunctionItem item = new FunctionItem(operacion);
			item.setParent(funciones);
			item.addEventListener("onClick", funcionesListener);
			
		}

		
	}

		
	private void crearFuncionesDefecto() {		
		List<Operacion> operacionesDefecto = OperacionHelper.getOperacionesDefecto();		
		createFunctions(operacionesDefecto);
	}
	
	private void generarEvento(Operacion operacion) {
		
		Events.postEvent("onSelectFunction", this, operacion);
		
	}
	
	
	private EventListener funcionesListener = new EventListener() {
			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub
				Object t = event.getTarget();
				if (t instanceof FunctionItem) {
					FunctionItem m = (FunctionItem) t;
					Operacion operacion = m.getOperacion();
					generarEvento(operacion);
				} 
				
			}
			
		
	};

}
