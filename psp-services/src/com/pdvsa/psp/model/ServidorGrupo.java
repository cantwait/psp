package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="servidor_grupos", schema="cs")
public class ServidorGrupo implements Serializable, BizEntity {
	private static final long serialVersionUID = 3003578038760031410L;
	private Long id = Long.MIN_VALUE;
	private Integer pooling;
	private Grupo grupo;
	private ServidorOpc servidorOpc;

    public ServidorGrupo() {
    }


	@Id
	@SequenceGenerator(name="SERVIDOR_GRUPOS_ID_GENERATOR", sequenceName="CS.SERVIDOR_GRUPOS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVIDOR_GRUPOS_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(nullable=false)
	public Integer getPooling() {
		return pooling;
	}
	
	public void setPooling(Integer pooling) {
		this.pooling = pooling;
	}


	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_grupo", nullable=false)
//	@OptimisticLock(excluded = true)
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_servidor_opc", nullable=false)
//	@OptimisticLock(excluded = true)
	public ServidorOpc getServidorOpc() {
		return this.servidorOpc;
	}

	public void setServidorOpc(ServidorOpc servidorOpc) {
		this.servidorOpc = servidorOpc;
	}
	
	@Transient 
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}		

	public boolean equals(ServidorGrupo obj) {
		
		if (getId() == null || obj.getId() == null) {

			if (getGrupo() != null && obj.getGrupo() != null && getServidorOpc() != null && obj.getServidorOpc() != null) {
				if (getGrupo().equals(obj.getGrupo()) && getServidorOpc().equals(obj.getServidorOpc())) {
					return true;
				} 
			}
			
			
			return false;
		}
		
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj instanceof ServidorGrupo) {
			ServidorGrupo objEntity = (ServidorGrupo) obj;
			return equals(objEntity);
		}
		
		return false;
	}		
	
}