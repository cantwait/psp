package com.obelisco.vista.zk.controls;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zul.Window;

import com.obelisco.vista.zk.command.IObeliscoCommand;

public class TargetWindow extends Window implements AfterCompose {

	private static final long serialVersionUID = 4400286462074393928L;

	EventListener listenerSeguridad = new EventListener() {
		 
		public void onEvent(Event event) {
			try {

				Object o = event;
				if (o instanceof Event) {
					Event e = (Event) o;

					Object data = e.getData();
					if (data instanceof IObeliscoCommand) {

						IObeliscoCommand command = (IObeliscoCommand) data;
						
						if (!isVisible())
							setVisible(true);
						command.execute(TargetWindow.this);
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	EventListener listenerContenido = new EventListener() {
		public void onEvent(Event event) {

			try {

				Object o = event.getData();

				if (o instanceof Event) {

					Event e = (Event) o;

					Object data = e.getData();

					if (data instanceof IObeliscoCommand) {

						IObeliscoCommand command = (IObeliscoCommand) data;
						command.execute(TargetWindow.this);

					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	public void afterCompose() {
 
		ConventionWires.wireVariables(this, this);
		ConventionWires.addForwards(this, this);
		EventQueues.lookup("seguridad").subscribe(listenerSeguridad);
		EventQueues.lookup("contenido").subscribe(listenerContenido);

	}

}
