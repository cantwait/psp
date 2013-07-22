package com.obelisco.vista.zk.controls;

import java.util.Iterator;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import com.obelisco.exception.ObeliscoException;
import com.obelisco.vista.zk.command.IObeliscoCommand;
import com.obelisco.vista.zk.command.TransaccionCommand;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.Transaccion.TipoTransaccion;
import com.pdvsa.psp.service.ISecurityService;

public class MainMenu extends org.zkoss.zul.Menubar implements AfterCompose {

	
	private static final long serialVersionUID = 1274138988614795486L;
	private ISecurityService securityService;

	public MainMenu() {
		super();
	}

	EventListener selectListener = new EventListener() {

		public boolean isAsap() {
			return true;
		}

		public void onEvent(Event event) throws UiException {
			try {
				Menuitem item = (Menuitem) event.getTarget();

				Object value = item.getAttribute("transaccion");

				if (value instanceof Transaccion) {

					Transaccion transaccion = (Transaccion) value;

					if (transaccion != null) {

						TransaccionCommand command = new TransaccionCommand();
						command.setTransaccion(transaccion);

						sendEvent(command);

					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ObeliscoException(e.getMessage());
			}

		}

	};

	protected void sendEvent(IObeliscoCommand command) {
		Events.postEvent("onCommand", this, command);

		Event e = new Event("onCommand", this, command);
		EventQueues.lookup("seguridad").publish(e);
	}

	private Menuitem createMenuItemTransaccion(Transaccion transaccion) {
		Menuitem menuitem = new Menuitem();

		if (transaccion != null) {

			menuitem.setAttribute("transaccion", transaccion);
			menuitem.setLabel(transaccion.getNombre());
			menuitem.setTooltiptext(transaccion.getDescripcion());			

			menuitem.addEventListener(Events.ON_CLICK, selectListener);

		}

		return menuitem;
	}

	private Menu createMenuCarpetaTransaccion(Transaccion carpeta) {
		Menu menu = null;

		if (carpeta != null) {
			menu = new Menu();
			menu.setLabel(carpeta.getNombre());
			menu.setTooltiptext(carpeta.getDescripcion());

			if (carpeta.getAux().size() > 0) {

				Menupopup popup = new Menupopup();
				popup.setParent(menu);

				for (Iterator i = carpeta.getAux().iterator(); i.hasNext();) {
					Transaccion transaccion = (Transaccion) i.next();
					if(transaccion.getTiposTransaccion().equals(TipoTransaccion.TRANSACTION)){
						Menuitem menuitem = createMenuItemTransaccion(transaccion);
						if (menuitem != null) {
							menuitem.setParent(popup);
						}
					}else{
						Menu menuhijo = createMenuCarpetaTransaccion(transaccion);
						menuhijo.setParent(popup);
					}

					
				}

			}
		}

		return menu;
	}

	private void createMainMenu(Transaccion carpeta) {
		
		if (carpeta != null) {

				
				if (carpeta.getAux().size() > 0) {

					for (Iterator i = carpeta.getAux().iterator(); i.hasNext();) {
						Transaccion hijo = (Transaccion) i.next();
						if(hijo.getTiposTransaccion().equals(TipoTransaccion.FOLDER)){
							Menu menu = createMenuCarpetaTransaccion(hijo);
							menu.setParent(this);
						}
						if(hijo.getTipoTransaccion().equals(TipoTransaccion.TRANSACTION)){
							Menuitem item = createMenuItemTransaccion(hijo);
							item.setParent(this);

						}
						
					}

				}

								

			

		}

	}

	@Override
	public void afterCompose() {

//		final Execution exec = Executions.getCurrent();
//		if (exec != null) {
//			Session session = exec.getDesktop().getSession();
//
//			ContextoOdebrecht obeCtx = (ContextoOdebrecht) session
//					.getAttribute(ContextoOdebrecht.ID_CONTEXTO_ODEBRECHT);
//			if (obeCtx != null) {
//
//				Usuario usuario = obeCtx.getUsuarioActual();
//
//				if (usuario != null) {
//					
//					System.out.println(usuario == null);
//
//					// El nodo Raiz
//					CarpetaTransaccion carpeta = usuario.getFunciones();
//
//					// Se genera el menu a partir del nodo Raiz
//					createMainMenu(carpeta);
//				}
//
//			}
//
//		}
		
		securityService = (ISecurityService) SpringUtil.getBean("securityService");
		
		Transaccion carpetaRaiz = securityService.getRootTransaction();
		
		if(carpetaRaiz != null){
			createMainMenu(carpetaRaiz);
		}
		
		

	}

	public ISecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(ISecurityService securityService) {
		this.securityService = securityService;
	}

}
