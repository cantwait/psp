package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Immutable
@Table(name="grupo_items", schema="cs")
public class GrupoItem implements Serializable, BizEntity {
	private static final long serialVersionUID = -4063504639495846881L;
	private Long id = Long.MIN_VALUE;
	private Grupo grupo;
	private Item item;

    public GrupoItem() {
    }
    
	public GrupoItem(Grupo grupo, Item item) {
		this.grupo = grupo;
		this.item = item;
	}    


	@Id
	@SequenceGenerator(name="GRUPO_ITEMS_ID_GENERATOR", sequenceName="CS.GRUPO_ITEMS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPO_ITEMS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    @ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_grupo", nullable=false)
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_item", nullable=false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@Transient 
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}		

	public boolean equals(GrupoItem obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof GrupoItem) {
			GrupoItem objEntity = (GrupoItem) obj;
			return equals(objEntity);
		}
		return false;
	}		
	
}