package com.pdvsa.psp.modbus.util;

import java.util.Vector;

public class Observable {
	@SuppressWarnings("unchecked")
	private Vector m_Observers;

	@SuppressWarnings("unchecked")
	public Observable() {
		m_Observers = new Vector(10);
	}

	public int getObserverCount() {
		synchronized (m_Observers) {
			return m_Observers.size();
		}
	}

	@SuppressWarnings("unchecked")
	public void addObserver(Observer o) {
		synchronized (m_Observers) {
			if (!m_Observers.contains(o)) {
				m_Observers.addElement(o);

			}
		}
	}

	public void removeObserver(Observer o) {
		synchronized (m_Observers) {
			m_Observers.removeElement(o);
		}
	}
	
	public void removeObservers() {
		synchronized (m_Observers) {
			m_Observers.removeAllElements();
		}
	}
	
	public void notifyObservers(Object arg) {
		synchronized (m_Observers) {
			for (int i = 0; i < m_Observers.size(); i++) {
				((Observer) m_Observers.elementAt(i)).update(this, arg);
			}
		}
	}

}
