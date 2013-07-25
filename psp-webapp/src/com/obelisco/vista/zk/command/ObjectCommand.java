package com.obelisco.vista.zk.command;



import com.obelisco.vista.zk.controls.OperacionHelper;
import com.obelisco.vista.zk.controls.OperationType;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;

public class ObjectCommand extends StoredComponentCommand {

	private Object objeto;
	private Operacion operacion = OperacionHelper.getOperacion(OperationType.BUSCAR);
	private Short numero = 0;

	protected static String BASE_PATH = "/WEB-INF/obelisco/modules/system/objects";

	public String getObjectZulCode() {
		String nomprg = null;
		return nomprg;
	}

	@Override
	public String getNombrePrograma() {
		// TODO Auto-generated method stub
		return this.getObjectZulCode();
	}

	public Object getObjeto() {
		return objeto;
	}

	
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
		getParametros().put("aObjeto", objeto);
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
		getParametros().put("aOperacion", operacion);
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	@Override
	public String getArchivoZul() {
		// TODO Auto-generated method stub

		if (archivoZul != null) {
			return super.getArchivoZul();
		} else {

			// Resuelvo el nombre del Archivo que va a mostrarse para el objeto
			String objectZulFile = BASE_PATH + "/viewNothing.zul";

			if (objeto != null) {
				
				String className = objeto.getClass().getSimpleName();
				String numeroZulFile = "";
				if (numero > 0) {
					
					numeroZulFile = numero.toString();
				}

				if (OperacionHelper.getType(getOperacion()).equals(OperationType.INCLUIR) || OperacionHelper.getType(getOperacion()).equals(OperationType.MODIFICAR)) {

					objectZulFile = BASE_PATH + "/edit" + "/edit" + className
							+ numeroZulFile + ".zul";
					
					

				} else if (OperacionHelper.getType(getOperacion()).equals(
						OperationType.BUSCAR)) {

					objectZulFile = BASE_PATH + "/views" + "/view" + className
							+ numeroZulFile + ".zul";

				}

			}

			return objectZulFile;

		}

	}

}
