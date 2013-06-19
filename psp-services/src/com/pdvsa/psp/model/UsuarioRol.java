package com.pdvsa.psp.model;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@org.hibernate.annotations.Immutable
@Table(name="usuario_roles", schema="public")
public class UsuarioRol implements Serializable, BizEntity {
	private static final long serialVersionUID = 1347027839550943990L;
	private Long id = Long.MIN_VALUE;
	private Usuario usuario;
	private Rol rol;

    public UsuarioRol() {
    }
    
	public UsuarioRol(Usuario usuario, Rol rol) {
		this.usuario = usuario;
		this.rol = rol;
	}	
    
	@Id
	@Column(unique=true, nullable=false, updatable=false)
	@SequenceGenerator(name="USUARIO_ROLES_ID_GENERATOR", sequenceName="PUBLIC.USUARIO_ROLES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USUARIO_ROLES_ID_GENERATOR")
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {   
        this.id = id;   
    }


    @ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", nullable=false)
//	@OptimisticLock(excluded = true)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public boolean equals(UsuarioRol obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UsuarioRol) {
			UsuarioRol objEntity = (UsuarioRol) obj;
			return equals(objEntity);
		}
		return false;
	}	
}