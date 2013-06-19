package com.pdvsa.psp.modbus.processimage;

public class DefaultProcessImageFactory implements ProcessImageFactory {
	public ProcessImageImplementation createProcessImageImplementation() {
		return new SimpleProcessImage();
	}
	
	public Discrete createDiscrete() {
		return new SimpleDiscrete();
	}
	
	public Discrete createDiscrete(boolean state) {
		return new SimpleDiscrete(state);
	}
	
	public Coil createCoil() {
		return new SimpleCoil();
	}
	
	public Coil createCoil(boolean b) {
		return new SimpleCoil(b);
	}
	
	public InputRegister createInputRegister() {
		return new SimpleInputRegister();
	}
	
	public InputRegister createInputRegister(byte b1, byte b2) {
		return new SimpleInputRegister(b1, b2);
	}
	
	public Register createRegister() {
		return new SimpleRegister();
	}
	
	public Register createRegister(byte b1, byte b2) {
		return new SimpleRegister(b1, b2);
	}
	
}
