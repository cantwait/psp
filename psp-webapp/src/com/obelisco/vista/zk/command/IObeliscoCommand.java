package com.obelisco.vista.zk.command;

import java.util.Map;

import org.zkoss.zk.ui.Component;


public interface IObeliscoCommand {

	public Object execute(Component parent) throws Exception;
	
	public Object execute() throws Exception;

}
