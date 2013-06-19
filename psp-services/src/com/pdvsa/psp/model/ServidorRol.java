package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="servidor_roles", schema="cs")
public class ServidorRol implements Serializable, BizEntity {
	private static final long serialVersionUID = -8060477114009950172L;
	private Long id = Long.MIN_VALUE;
	private Rol rol;
	private ServidorOpc servidorOpc;

    public ServidorRol() {
    }


	@Id
	@SequenceGenerator(name="SERVIDOR_ROLES_ID_GENERATOR", sequenceName="CS.SERVIDOR_ROLES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVIDOR_ROLES_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
	

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_rol", nullable=false)
//	@OptimisticLock(excluded = true)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	@Transient 
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}		

	public boolean equals(ServidorRol obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ServidorRol) {
			ServidorRol objEntity = (ServidorRol) obj;
			return equals(objEntity);
		}
		return false;
	}		
		
}