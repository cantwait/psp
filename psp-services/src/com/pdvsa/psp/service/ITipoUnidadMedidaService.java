package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.pdvsa.psp.model.TipoUnidadMedida;

@WebService
@Service
public interface ITipoUnidadMedidaService {

	@WebMethod
	@WebResult(name = "tipounidad")
	public TipoUnidadMedida getTipoMedidaByNombre(
			@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "tiposunidades")
	public List<TipoUnidadMedida> getTiposMedidaLikeNombre(
			@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "tipounidad")
	public TipoUnidadMedida saveTipounidad(
			@WebParam(name = "tipounidad") TipoUnidadMedida tipounidad);

	@WebMethod
	public boolean removeTipounidad(
			@WebParam(name = "idtipounidad") Long idTipounidad);

}
