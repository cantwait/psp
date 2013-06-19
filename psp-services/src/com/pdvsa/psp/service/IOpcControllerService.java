package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.state.ServerState;

@WebService
public interface IOpcControllerService {
	
	@WebMethod
	@WebResult(name = "state")
	public ServerState getServerState(@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean serverStart(@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean serverStop(@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean serverRestart(@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean addServer(
			@WebParam(name = "ownerId") Long ownerId, 
			@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean removeServer(
			@WebParam(name = "ownerId") Long ownerId, 
			@WebParam(name = "serverId") Long serverId);
	
	@WebMethod
	public boolean reloadServers(@WebParam(name = "ownerId") Long ownerId);	
	
	@WebMethod
	@WebResult(name = "registers")
	public List<OpcInfoRegister> getDumpRegistersByServer(@WebParam(name = "serverId") Long serverId);
	
	/**
	 * Clase que retorna una lista de Objetos XML de Tipo: {@link OpcInfoRegister} de toda la localidad sin importar
	 * el servidor.
	 * 
	 * e.g:
	 * <pre>
	 * {@code 
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	 * 	<soap:Body>
	 * 		<ns2:getAllRegistersResponse xmlns:ns2="http://service.psp.pdvsa.com/">
	 * 			<registers>
	 * 				<quality>192</quality>
	 * 				<reference>40001</reference>
	 * 				<regType>R4</regType>
	 * 				<regValue>22298.0</regValue>
	 * 				<tagName>testsimulacion1</tagName>
	 * 				<tagOpc>TK-1002.DA[3].VU</tagOpc>
	 * 				<timestamp>2013-06-18T10:23:41.072-04:30</timestamp>
	 * 			</registers>
	 * 			<registers>
	 * 				<quality>192</quality>
	 * 				<reference>40015</reference>
	 * 				<regType>R8</regType>
	 * 				<regValue>98.0</regValue>
	 * 				<tagName>testsimulacion5</tagName>
	 * 				<tagOpc>TK-1002.DA[7].VU</tagOpc>
	 * 				<timestamp>2013-06-18T10:23:41.082-04:30</timestamp>
	 * 			</registers>
	 * 		</ns2:getAllRegistersResponse>
	 * 	</soap:Body>
	 * </soap:Envelope>
	 *    
	 * }
	 * 
	 * @author Rafael Cadenas
	 * @return XML
	 * @since 18/06/2013
	 */
	@WebMethod
	@WebResult(name="registers")	
	public List<OpcInfoRegister> getAllRegisters();
	
}
