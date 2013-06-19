package com.obelisco.vista.zk.controls;

import java.util.Collection;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zul.Imagemap;

import com.obelisco.modelo.data.seguridad.Operacion;



public class Toolbar extends org.zkoss.zul.Toolbar implements AfterCompose{

	//@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		

		ConventionWires.wireVariables(this, this);

		// NO need to register onXxx event listeners

		// auto forward
		ConventionWires.addForwards(this, this);

		createDefaultFunctions();

		
	}
	


	protected void createDefaultFunctions() {
		// TODO Auto-generated method stub
		List<Operacion> operacionesDefecto = OperacionHelper.getOperacionesDefecto();
		createFunctions(operacionesDefecto);
		
	}
	
	
	public void createFunctions( Collection<Operacion> funciones) {
		
		this.getChildren().clear();
		
		for (Operacion operacion : funciones) {
			
			FunctionImagemap item = new FunctionImagemap(operacion);
			item.setParent(this);
			item.addEventListener("onClick", funcionesListener);
			
		}
		
	}

	
	
	
	
	private void generarEvento(Operacion operacion) {
		
		Events.postEvent("onSelectFunction", this, operacion);
		
	}
	
	
	private EventListener funcionesListener = new EventListener() {



			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub

				Object t = event.getTarget();

				if (t instanceof FunctionImagemap) {
					FunctionImagemap m = (FunctionImagemap) t;

					Operacion operacion = m.getOperacion();

					generarEvento(operacion);
				} 
				
			}
			
		
	};
	
	
	

}
