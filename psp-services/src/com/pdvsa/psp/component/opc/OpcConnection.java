package com.pdvsa.psp.component.opc;

import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIClsid;
import org.jinterop.dcom.core.JIComServer;
import org.jinterop.dcom.core.JIProgId;
import org.jinterop.dcom.core.JISession;
import org.openscada.opc.dcom.da.impl.OPCServer;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.ErrorMessageResolver;
import com.pdvsa.psp.state.ConnectionState;

public class OpcConnection {
	protected final Log logger = LogFactory.getLog(OpcConnection.class);
	private ConnectionInformation connInfo;
	private JISession jiSession;
	private JIComServer jiComServer;
	private OPCServer opcServer;
	private ErrorMessageResolver errorMsgResolver;
	private int socketTimeout = 5000;
	private int defaultLocaleID = 0;
	private volatile ConnectionState connState = ConnectionState.DISCONNECTED;
	private volatile int connIntents = 0;
	private volatile int lastConnIntents = 0;
	private String serverName;

	public OpcConnection(ConnectionInformation connInfo, String serverName,
			int socketTimeout) {
		super();
		this.connInfo = connInfo;
		this.serverName = serverName;
		this.socketTimeout = socketTimeout;
	}

	public ConnectionInformation getConnInfo() {
		return connInfo;
	}

	public void setConnInfo(ConnectionInformation connInfo) {
		this.connInfo = connInfo;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public int getDefaultLocaleID() {
		return defaultLocaleID;
	}

	public void setDefaultLocaleID(int defaultLocaleID) {
		this.defaultLocaleID = defaultLocaleID;
	}

	public JISession getSession() {
		return this.jiSession;
	}

	public JIComServer getComServer() {
		return this.jiComServer;
	}

	public OPCServer getServer() {
		return this.opcServer;
	}

	protected synchronized void setState(ConnectionState connState) {
		this.connState = connState;
		if (connState == ConnectionState.CONNECTED) {
			lastConnIntents = connIntents;
			connIntents = 0;
		}
		logger.info(String.format(
				"Status de la conexion DCOM al Servidor %s: %s", toString(),
				connState));
	}

	public ConnectionState getState() {
		return connState;
	}

	public int getConnIntents() {
		return (connState == ConnectionState.CONNECTED) ? lastConnIntents
				: connIntents;
	}

	public synchronized void incrementIntents() {
		connIntents++;
	}

	public synchronized String getErrorMessage(int errorCode) {
		if (errorMsgResolver == null)
			return String.format("Error desconocido (%08X)", errorCode);
		String message = errorMsgResolver.getMessage(errorCode);
		if (message != null)
			return message;
		return String.format("Error desconocido (%08X)", errorCode);
	}

	public void disconnect() {
		if (jiSession != null) {
			setState(ConnectionState.DISCONNECTING);
			final JISession destructSession = jiSession;
			final String serverName = toString();
			Thread destructor = new Thread(new Runnable() {
				public void run() {
					long ts = System.currentTimeMillis();
					try {
						logger.info(String.format(
								"Finalizando sesion DCOM del servidor %s...",
								serverName));
						JISession.destroySession(destructSession);
						long te = System.currentTimeMillis() - ts;
						logger.info(String.format(
								"Fin de sesion DCOM (%d ms.) del servidor %s",
								te, serverName));
					} catch (Throwable e) {
						logger.error(
								"No se logro finalizar la sesion DCOM del servidor "
										+ serverName, e);
					}
				}
			}, "OPCSessionDestroy-" + connInfo.getHost());
			destructor.setDaemon(true);
			destructor.start();
			setState(ConnectionState.DISCONNECTED);
		}
		errorMsgResolver = null;
		jiSession = null;
		jiComServer = null;
		opcServer = null;
	}

	public void connect() throws IllegalArgumentException,
			UnknownHostException, JIException {
		if (getState() == ConnectionState.CONNECTED) {
			return;
		}
		setState(ConnectionState.CONNECTING);
		try {
			final String domain = (connInfo.getDomain() != null) ? connInfo.getDomain() : "";
			final String password = (connInfo.getPassword() != null) ? connInfo.getPassword() : "";
			logger.info(String.format("Conectando con el servidor %s(%d)...",toString(), connIntents + 1));
			if (!StringUtils.isEmpty(connInfo.getClsid())) {
				jiSession = JISession.createSession(domain, connInfo.getUser(),	password);
				jiSession.setGlobalSocketTimeout(socketTimeout);
				jiComServer = new JIComServer(JIClsid.valueOf(connInfo.getClsid()), connInfo.getHost(), jiSession);
			} else if (!StringUtils.isEmpty(connInfo.getProgId())) {
				jiSession = JISession.createSession(domain, connInfo.getUser(),	password);
				jiSession.setGlobalSocketTimeout(socketTimeout);
				jiComServer = new JIComServer(JIProgId.valueOf(connInfo.getClsid()), connInfo.getHost(), jiSession);
			} else {
				throw new IllegalArgumentException(
						"La conexion con el servidor " + toString()
								+ " requiere un CLSID o PROGID válido");
			}
			opcServer = new OPCServer(jiComServer.createInstance());
			errorMsgResolver = new ErrorMessageResolver(opcServer.getCommon(),
					defaultLocaleID);
			logger.info("Se establecio la conexion con el servidor "
					+ toString());
			setState(ConnectionState.CONNECTED);
		} catch (Exception e) {
			incrementIntents();
			disconnect();
			if (e instanceof UnknownHostException) {
				throw (UnknownHostException) e;
			}
			if (e instanceof IllegalArgumentException) {
				throw (IllegalArgumentException) e;
			}
			if (e instanceof JIException) {
				throw (JIException) e;
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public String toString() {
		return " [Servidor: " + serverName + " (" + getConnInfo().getHost()
				+ ")]";
	}

}
