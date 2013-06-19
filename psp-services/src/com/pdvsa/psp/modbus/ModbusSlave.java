package com.pdvsa.psp.modbus;

import java.util.Hashtable;
import java.util.Map;
import com.pdvsa.psp.modbus.processimage.DefaultProcessImageFactory;
import com.pdvsa.psp.modbus.processimage.ProcessImage;
import com.pdvsa.psp.modbus.processimage.ProcessImageFactory;

public class ModbusSlave {
	private static ModbusSlave self; // Refrencia al Singleton
	private ProcessImageFactory processImageFactory;
	private Map<Integer, ProcessImage> processImages = new Hashtable<Integer, ProcessImage>();

	static {
		self = new ModbusSlave();
	}

	private ModbusSlave() {
		processImageFactory = new DefaultProcessImageFactory();
	}

	private ModbusSlave(Integer unitID, ProcessImage processImage) {
		addProcessImage(unitID, processImage);
		self = this;
	}

	public ProcessImageFactory getProcessImageFactory() {
		return processImageFactory;
	}
	
	public void setProcessImageFactory(ProcessImageFactory factory) {
		processImageFactory = factory;
	}
	
	public synchronized ProcessImage getProcessImage(Integer unitID) {
		return processImages.get(unitID);
	}
	
	public synchronized void addProcessImage(Integer unitID, ProcessImage processImage) {
		processImages.put(unitID, processImage);
	}
	
	public static final ModbusSlave getReference() {
		return self;
	}
	
}
