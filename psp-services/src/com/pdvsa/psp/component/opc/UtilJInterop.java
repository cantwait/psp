package com.pdvsa.psp.component.opc;

import java.util.Locale;
import java.util.logging.Level;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JISystem;


public class UtilJInterop {
	protected final Log logger = LogFactory.getLog(OpcManager.class);
	boolean comDisableAutoCollection = false;
	boolean comDebug = true;

	public boolean isComDisableAutoCollection() {
		return comDisableAutoCollection;
	}

	public void setComDisableAutoCollection(boolean comDisableAutoCollection) {
		this.comDisableAutoCollection = comDisableAutoCollection;
	}

	public boolean isComDebug() {
		return comDebug;
	}

	public void setComDebug(boolean comDebug) {
		this.comDebug = comDebug;
	}
	
	public UtilJInterop() {
		
	}
	
	public UtilJInterop(boolean comDebug, boolean comDisableAutoCollection) {
		this.comDebug = comDebug;
		this.comDisableAutoCollection = comDisableAutoCollection;
//		JISystem.getLogger().setLevel((comDebug) ? Level.ALL : Level.WARNING);
        JISystem.setJavaCoClassAutoCollection(!comDisableAutoCollection);
        JISystem.setLocale(Locale.ENGLISH);
        logger.info("DCOM auto collection: " + JISystem.isJavaCoClassAutoCollectionSet());
	}

}
