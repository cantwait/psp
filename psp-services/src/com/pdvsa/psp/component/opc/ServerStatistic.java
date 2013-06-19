package com.pdvsa.psp.component.opc;

import java.util.Date;

public final class ServerStatistic {
	private Date startConnTime;
	private Date endConnTime;
	private Date startServerTime;
	private Date stopServerTime;
	
	public ServerStatistic() {
		stopServerTime = new Date();
	}
	
	public void markStartConnTime() {
		startConnTime = new Date();
		endConnTime = startServerTime = stopServerTime = null;
	}
	
	public void markStartServerTime() {
		endConnTime = startServerTime = new Date();
		stopServerTime = null;
	}
	
	public void markStopServerTime() {
		startServerTime = null;
		stopServerTime = new Date();
		if (endConnTime == null) {
			endConnTime = stopServerTime;
		}
	}
	
	public Date getStartConnTime() {
		return startConnTime;
	}
	
	public Date getStartServerTime() {
		return startServerTime;
	}
	
	public Date getStopServerTime() {
		return stopServerTime;
	}
	
	public long getRunningTime() {
		if (startConnTime != null) {
			Date curTime = new Date();
			return curTime.getTime() - startConnTime.getTime();
		}
		return 0;
	}
	
}
