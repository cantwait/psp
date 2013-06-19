package com.pdvsa.psp.modbus.processimage;

import java.util.Vector;

@SuppressWarnings("unchecked")
public class SimpleProcessImage implements ProcessImageImplementation {
	protected Vector discretes;
	protected Vector coils;
	protected Vector inputRegisters;
	protected Vector registers;
	protected boolean locked = false;

	public SimpleProcessImage() {
		discretes = new Vector();
		coils = new Vector();
		inputRegisters = new Vector();
		registers = new Vector();
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void addDiscrete(Discrete di) {
		if (!isLocked()) {
			discretes.addElement(di);
		}
	}

	public void removeDiscrete(Discrete di) {
		if (!isLocked()) {
			discretes.removeElement(di);
		}
	}

	public void setDiscrete(int ref, Discrete di) throws IllegalAddressException {
		if (!isLocked()) {
			try {
				discretes.setElementAt(di, ref);
			} catch (IndexOutOfBoundsException ex) {
				throw new IllegalAddressException();
			}
		}
	}

	public Discrete getDiscrete(int ref) throws IllegalAddressException {
		try {
			return (Discrete) discretes.elementAt(ref);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalAddressException();
		}
	}

	public int getDiscreteCount() {
		return discretes.size();
	}

	public Discrete[] getDiscreteRange(int ref, int count) {
		if (ref < 0 || ref + count > discretes.size()) {
			throw new IllegalAddressException();
		} else {
			Discrete[] dins = new Discrete[count];
			for (int i = 0; i < dins.length; i++) {
				dins[i] = getDiscrete(ref + i);
			}
			return dins;
		}
	}

	public void addCoil(Coil coil) {
		if (!isLocked()) {
			coils.addElement(coil);
		}
	}

	public void removeCoil(Coil coil) {
		if (!isLocked()) {
			coils.removeElement(coil);
		}
	}

	public void setCoil(int ref, Coil coil)
			throws IllegalAddressException {
		if (!isLocked()) {
			try {
				coils.setElementAt(coil, ref);
			} catch (IndexOutOfBoundsException ex) {
				throw new IllegalAddressException();
			}
		}
	}

	public Coil getCoil(int ref) throws IllegalAddressException {
		try {
			return (Coil) coils.elementAt(ref);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalAddressException();
		}
	}

	public int getCoilCount() {
		return coils.size();
	}

	public Coil[] getCoilRange(int ref, int count) {
		if (ref < 0 || ref + count > coils.size()) {
			throw new IllegalAddressException();
		} else {
			Coil[] douts = new Coil[count];
			for (int i = 0; i < douts.length; i++) {
				douts[i] = getCoil(ref + i);
			}
			return douts;
		}
	}

	public void addInputRegister(InputRegister reg) {
		if (!isLocked()) {
			inputRegisters.addElement(reg);
		}
	}

	public void removeInputRegister(InputRegister reg) {
		if (!isLocked()) {
			inputRegisters.removeElement(reg);
		}
	}

	public void setInputRegister(int ref, InputRegister reg) throws IllegalAddressException {
		if (!isLocked()) {
			try {
				inputRegisters.setElementAt(reg, ref);
			} catch (IndexOutOfBoundsException ex) {
				throw new IllegalAddressException();
			}
		}
	}

	public InputRegister getInputRegister(int ref) throws IllegalAddressException {
		try {
			return (InputRegister) inputRegisters.elementAt(ref);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalAddressException();
		}
	}

	public int getInputRegisterCount() {
		return inputRegisters.size();
	}

	public InputRegister[] getInputRegisterRange(int ref, int count) {
		if (ref < 0 || ref + count > inputRegisters.size()) {
			throw new IllegalAddressException();
		} else {
			InputRegister[] iregs = new InputRegister[count];
			for (int i = 0; i < iregs.length; i++) {
				iregs[i] = getInputRegister(ref + i);
			}
			return iregs;
		}
	}

	public void addRegister(Register reg) {
		if (!isLocked()) {
			registers.addElement(reg);
		}
	}

	public void removeRegister(Register reg) {
		if (!isLocked()) {
			registers.removeElement(reg);
		}
	}

	public void setRegister(int ref, Register reg) throws IllegalAddressException {
		if (!isLocked()) {
			try {
				registers.setElementAt(reg, ref);
			} catch (IndexOutOfBoundsException ex) {
				throw new IllegalAddressException();
			}
		}
	}

	public Register getRegister(int ref) throws IllegalAddressException {
		try {
			return (Register) registers.elementAt(ref);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalAddressException();
		}
	}

	public int getRegisterCount() {
		return registers.size();
	}

	public Register[] getRegisterRange(int ref, int count) {
		if (ref < 0 || ref + count > registers.size()) {
			throw new IllegalAddressException();
		} else {
			Register[] iregs = new Register[count];
			for (int i = 0; i < iregs.length; i++) {
				iregs[i] = getRegister(ref + i);
			}
			return iregs;
		}
	}
	
	public void clearImages() {
		discretes.clear();
		coils.clear();
		inputRegisters.clear();
		registers.clear();
	}

}
