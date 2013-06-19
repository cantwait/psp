package com.obelisco.vista.zk.controls;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import com.obelisco.modelo.data.seguridad.Archivo;
import com.obelisco.modelo.data.seguridad.Operacion;

public class OperacionHelper {

	private static ResourceBundle funcionesBundle = ResourceBundle
			.getBundle("com.obelisco.vista.zk.controls.Funciones");

	public static com.obelisco.modelo.data.seguridad.Operacion getOperacion(
			OperationType tipo) {

		Operacion o = new Operacion();
		String texto = funcionesBundle.getString(tipo.toString() + ".texto");
		o.setNombre(texto);

		Archivo a = new Archivo();
		String srcImagen = funcionesBundle
				.getString(tipo.toString() + ".icono");
		a.setNombre(srcImagen);
		o.setIcono(a);

		if (tipo == OperationType.INCLUIR) {
			o.setCodigo("I");
			o.setOrden(new Integer(1));
		} else if (tipo == OperationType.MODIFICAR) {
			o.setCodigo("M");
			o.setOrden(new Integer(2));
		} else if (tipo == OperationType.ELIMINAR) {
			o.setCodigo("E");
			o.setOrden(new Integer(3));
		} else if (tipo == OperationType.BUSCAR) {
			o.setCodigo("B");
			o.setOrden(new Integer(4));
		} else {
			o.setCodigo("-");
			o.setOrden(new Integer(100));
		}
		return o;
	}

	public static List<Operacion> getOperacionesDefecto() {

		Vector<Operacion> funciones = new Vector<Operacion>();

		Operacion o = getOperacion(OperationType.INCLUIR);
		funciones.add(o);

		o = getOperacion(OperationType.MODIFICAR);
		funciones.add(o);

		o = getOperacion(OperationType.ELIMINAR);
		funciones.add(o);

		Collections.sort(funciones, new Comparer1());

		return funciones;
	}

	public static List<Operacion> getOperacionesCatalogo() {

		Vector<Operacion> funciones = new Vector<Operacion>();

		Operacion o = getOperacion(OperationType.INCLUIR);
		funciones.add(o);

		o = getOperacion(OperationType.ELIMINAR);
		funciones.add(o);

		Collections.sort(funciones, new Comparer1());

		return funciones;
	}

	public static OperationType getType(Operacion o) {

		OperationType tipo = OperationType.DEFINIDA_USUARIO;

		if (o.getCodigo().trim().equals("I")) {
			tipo = OperationType.INCLUIR;
		} else if (o.getCodigo().trim().equals("M")) {
			tipo = OperationType.MODIFICAR;
		} else if (o.getCodigo().trim().equals("E")) {
			tipo = OperationType.ELIMINAR;
		} else if (o.getCodigo().trim().equals("B")) {
			tipo = OperationType.BUSCAR;
		} else if (o.getCodigo().trim().equals("P")) {
			tipo = OperationType.IMPRIMIR;
		}

		return tipo;

	}

}

class Comparer1 implements Comparator {

	public int compare(Object obj1, Object obj2) {
		int i1 = ((Operacion) obj1).getOrden().intValue();
		int i2 = ((Operacion) obj2).getOrden().intValue();

		return Math.abs(i1) - Math.abs(i2);
	}
}