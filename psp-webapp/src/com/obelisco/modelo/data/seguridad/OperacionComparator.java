package com.obelisco.modelo.data.seguridad;

import java.util.Comparator;

public class OperacionComparator implements Comparator {

	public int compare(Object obj1, Object obj2) {
		int i1 = ((Operacion) obj1).getOrden().intValue();
		int i2 = ((Operacion) obj2).getOrden().intValue();

		return Math.abs(i1) - Math.abs(i2);
	}
}