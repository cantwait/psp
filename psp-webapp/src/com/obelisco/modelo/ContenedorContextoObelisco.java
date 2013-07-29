package com.obelisco.modelo;

public class ContenedorContextoObelisco {

	private static ThreadLocal contextHolder = new ThreadLocal();

	  public static void setContexto(ContextoObelisco context) {
	      contextHolder.set(context);
	  }

	  public static ContextoObelisco getContexto() {
	      if (contextHolder.get() == null) {
	          contextHolder.set(new ContextoObeliscoImpl());
	      }

	      return (ContextoObelisco) contextHolder.get();
	  }

	  public static void borrarContexto() {
	      contextHolder.set(null);
	  }
}
