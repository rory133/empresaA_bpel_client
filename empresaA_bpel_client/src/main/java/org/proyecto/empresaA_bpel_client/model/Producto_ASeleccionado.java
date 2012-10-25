package org.proyecto.empresaA_bpel_client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="producto_aSeleccionado")
@Entity
@Table(name = "producto_aseleccionado")
public class Producto_ASeleccionado implements Serializable {
	
	private static final long serialVersionUID = -5527566206302296042L;
	
	  private Integer idproductoSeleccionado;
	  private Integer cantidad;
	  private Producto_A producto_a;
	  private Carro_A carro_a;
	  private BigDecimal subTotal;
	  
	  
	  
	  public Producto_ASeleccionado() {

		}
	  
		
	@Column(name = "CANTIDAD")
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}



	@Column(name = "SUBTOTAL")
	  public BigDecimal getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@Id
	  @Column(name = "IDPRODUCTOSELECCIONADO", unique = true, nullable = false)	 
	  @GeneratedValue
	public Integer getIdproductoSeleccionado() {
		return idproductoSeleccionado;
	}


	public void setIdproductoSeleccionado(Integer idproductoSeleccionado) {
		this.idproductoSeleccionado = idproductoSeleccionado;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idproductoA", nullable = false)
	public Producto_A getProducto_a() {
		return producto_a;
	}

	public void setProducto_a(Producto_A producto_a) {
		this.producto_a = producto_a;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCARRO_A", nullable = false)
	public Carro_A getCarro_a() {
		return carro_a;
	}


	public void setCarro_a(Carro_A carro_a) {
		this.carro_a = carro_a;
	}
  
	  
	 
}

