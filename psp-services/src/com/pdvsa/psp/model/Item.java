package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="items", schema="cs")
@XmlRootElement(name="opcItem")
public class Item implements Serializable, BizEntity {
	
	public enum DATA_TYPE {
		I1, I2, I4, R4, R8, CY, DATE, BSTR, BOOL, UI1, UI2, UI4;
		
		public int getLengthForRegister() {
		    switch(this) {
		      case I1: 
		      case UI1: 
		      case BOOL: 
		      case I2: 
		      case UI2: return 1;
		      case I4:
		      case UI4:
		      case R4: return 2;
		      case R8: return 4;
		      default: return 0;
		    }
		}
	}
	
	public enum ITEM_TYPE {TANK, DEVICE}
	
	private static final long serialVersionUID = -6907521803457225276L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Boolean hda = false;
	private String itemOpc;
	private DATA_TYPE tipoDato = DATA_TYPE.I4;
	private ITEM_TYPE tipoItem = ITEM_TYPE.DEVICE;
	private Integer version = 0;
	private Set<GrupoItem> grupoItems = new HashSet<GrupoItem>(0);
	private UnidadMedida unidadMedida;
	private Boolean transferred = Boolean.FALSE;

    public Item() {
    }
    
    

	public Item(Boolean activo, String descripcion, String nombre, Boolean hda,
			String itemOpc, DATA_TYPE tipoDato, ITEM_TYPE tipoItem, UnidadMedida unidadMedida) {
		super();
		this.activo = activo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.hda = hda;
		this.itemOpc = itemOpc;
		this.tipoDato = tipoDato;
		this.tipoItem = tipoItem;
		this.unidadMedida = unidadMedida;
	}




	@Id
	@SequenceGenerator(name="ITEMS_ID_GENERATOR", sequenceName="CS.ITEMS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEMS_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(nullable=false)
	public Boolean isActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}	


	@Column(length=500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	@Column(nullable=false, length=50, unique=true)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(nullable=false)
	public Boolean getHda() {
		return hda;
	}
	
	public void setHda(Boolean hda) {
		this.hda = hda;
	}
	

	@Column(name="item_opc", nullable=false, length=150)
	public String getItemOpc() {
		return this.itemOpc;
	}

	public void setItemOpc(String itemOpc) {
		this.itemOpc = itemOpc;
	}
	

	@Column(name="tipo_dato", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	public DATA_TYPE getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(DATA_TYPE tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	@Column(name="tipo_item", nullable=false)
	@Enumerated(EnumType.ORDINAL)	
	public ITEM_TYPE getTipoItem() {
		return tipoItem;
	}	
	
	public void setTipoItem(ITEM_TYPE tipoItem) {
		this.tipoItem = tipoItem;
	}	


//	@Version
//	@Column(nullable=false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@OneToMany(mappedBy="item", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@XmlTransient
	public Set<GrupoItem> getGrupoItems() {
		return this.grupoItems;
	}

	public void setGrupoItems(Set<GrupoItem> grupoItems) {
		this.grupoItems = grupoItems;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_unidad_medida", nullable=false)
	public UnidadMedida getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	
	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}	
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Item obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Item) {
			Item objEntity = (Item) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	
	
	public Boolean getTransferred() {
		return transferred;
	}



	public void setTransferred(Boolean transferred) {
		this.transferred = transferred;
	}



	@Override
	public String toString() {
		return nombre;
	}

}