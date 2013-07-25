package com.obelisco.vista.zk.command;

import org.zkoss.zk.ui.Component;
import com.pdvsa.psp.model.Transaccion;

public class TransaccionCommand extends ShowCommand {

	private Transaccion transaccion;

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public void executeTransaccion(Component parent) throws Exception {
		if (transaccion != null) {
			

				if (transaccion.getArchivoZul() != null	&& !transaccion.getArchivoZul().equals("")) {
					
					setArchivoZul(transaccion.getArchivoZul());
					getParametros().put("transaccion", transaccion);
					super.execute(parent);
					return;
				}				
			
		}
		
	}

	@Override
	public Object execute() throws Exception {
		executeTransaccion(null);
		return null;
	}

	@Override
	public Object execute(Component parent) throws Exception {
		executeTransaccion(parent);
		return null;
	}

}
