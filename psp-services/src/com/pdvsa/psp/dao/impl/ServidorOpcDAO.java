package com.pdvsa.psp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IServidorOpcDAO;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.Usuario;

@Repository
public class ServidorOpcDAO extends BaseDAO<ServidorOpc, Long> implements IServidorOpcDAO {
	
	@Override
	public ServidorOpc getNewServidor() {
		return new ServidorOpc();
	}
	
	@Override
	public ServidorOpc findByHost(String host) {
		Search s = new Search(); 
		s.addFilterEqual("host", host);
		return searchUnique(s);
	}

	@Override
	public ServidorOpc findByNombre(String nombre) {
		Search s = new Search(); 
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}
	
	@Override
	public List<ServidorOpc> findLikeNombre(String value) {
		Search s = new Search(); 
		s.addFilterILike("nombre", value);
		return search(s);
	}
	
	@Override
	public List<ServidorOpc> findAll(Boolean activo) {
		if (activo == null) {
			return findAll();
		}
		Search s = new Search(); 
		s.addFilterEqual("activo", activo);
		s.addSortAsc("id");
		return search(s);
	}	

	@Override
	public List<ServidorOpc> findByRegion(Long idRegion, Boolean activo) {
		Search s = new Search(); 
		s.addFilterEqual("localidad.region.id", idRegion);
		if (activo != null) {
			s.addFilterEqual("activo", activo);
		}
		s.addSortAsc("id");
		return search(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findUsuarios(Long idServidor, Boolean activo) {
		String sql = "SELECT d FROM ServidorOpc a JOIN a.servidorRoles b JOIN b.rol.usuarioRoles c JOIN c.usuario d WHERE a.id=?";
		if (activo != null) {
			sql = sql.concat(" AND d.activo=?");
		}		
		Query query = em().createQuery(sql);
		query.setParameter(1, idServidor);
		if (activo != null) {
			query.setParameter(2, activo);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tanque> findTanques(Long idServidor, Boolean activo) {
		String sql = "SELECT a FROM Tanque a JOIN a.servidorOpc b WHERE b.id=?";
		if (activo != null) {
			sql = sql.concat(" AND a.activo=?");
		}
		sql = sql.concat(" ORDER BY a.id");
		Query query = em().createQuery(sql);
		query.setParameter(1, idServidor);
		if (activo != null) {
			query.setParameter(2, activo);
		}
		return query.getResultList();
	}

	@Override
	public List<ServidorOpc> findServidoresByLocalidad(Long localidad,
			Boolean activo) {
		Search s = new Search();
		s.addFilterEqual("localidad.id", localidad);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}

	@Override
	public HashMap<String, Object> findValuesByServerId(Long id) {
		
		HashMap<String,Object> valores = new HashMap<String,Object>();
		
		String qry = "select l.id as id_localidad, l.nombre as nombre_localidad, r.id as id_region, r.nombre as nombre_region, p.id as pais_id, p.nombre as pais_nombre, so.id as servidor_id, so.nombre as servidor_nombre, pro.nombre as producto_nombre from public.paises p  left join public.regiones r on p.id = r.id_pais   left join public.localidades l on r.id = l.id_region   left join cs.servidores_opc so on so.id_localidad = l.id left join cs.tanques ta on ta.id_servidor_opc = so.id left join cs.productos pro on pro.id = ta.id_producto  where so.id = :servidorId";
		
		Query sqlQry = em().createNativeQuery(qry).setParameter("servidorId", id);
		
		Object[] o = (Object[]) sqlQry.getSingleResult();
		
		valores.put("localidadId", o[0]);
		valores.put("localidadNombre", o[1]);
		valores.put("regionId", o[2]);
		valores.put("regionNombre", o[3]);
		valores.put("paisId", o[4]);
		valores.put("paisNombre", o[5]);
		valores.put("servidorId", o[6]);
		valores.put("servidorNombre", o[7]);
		valores.put("productoNombre", o[8]);
		
		
		return valores;
	}


}
