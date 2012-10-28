package org.proyecto.empresaA_bpel_client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.proyecto.empresaA_bpel_client.model.Producto_ASeleccionado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement(name="carro_a")
@Entity
@Table(name = "carro_a")
public class Carro_A implements Serializable{
	
	private static final long serialVersionUID = -5527547205002296042L;

	
	private Integer idcarro_a;
	private Date fecha_a;
	private Set<Producto_ASeleccionado> producto_ASeleccionado = new HashSet<Producto_ASeleccionado>(0);
	private Cliente_A cliente_a;
	private Boolean pagado;
	private Boolean enviado;
	private BigDecimal total;
	



	public Carro_A() {
	}


	public Carro_A(Date fecha_a) {
		super();
		this.fecha_a = fecha_a;
	}
	
	


	public Carro_A(Date fecha_a, Cliente_A cliente_a) {
		super();
		this.fecha_a = fecha_a;
		this.cliente_a = cliente_a;
	}


	public Carro_A( Set<Producto_ASeleccionado> producto_ASeleccionado) {
		super();
		this.producto_ASeleccionado = producto_ASeleccionado;
		this.fecha_a=new Date();
	}


	
	@Id
	@GeneratedValue
	@Column(name = "IDCARRO_A")
	public Integer getIdcarro_a() {
		return idcarro_a;
	}

	
	public void setIdcarro_a(Integer idcarro_a) {
		this.idcarro_a = idcarro_a;
	}


	@Column(name = "FECHA")
	public Date getFecha_a() {
		return fecha_a;
	}

	
	
	public void setFecha_a(Date fecha_a) {
		this.fecha_a = fecha_a;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carro_a")
	public Set<Producto_ASeleccionado> getProducto_ASeleccionado() {
		return producto_ASeleccionado;
	}


	
	public void setProducto_ASeleccionado(
			Set<Producto_ASeleccionado> producto_ASeleccionado) {
		this.producto_ASeleccionado = producto_ASeleccionado;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente", nullable = false)
	public Cliente_A getCliente_a() {
		return cliente_a;
	}


	public void setCliente_a(Cliente_A cliente_a) {
		this.cliente_a = cliente_a;
	}
	
	
	@Column(name = "PAGADO")
	public Boolean getPagado() {
		return pagado;
	}


	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	
	@Column(name = "ENVIADO")
	public Boolean getEnviado() {
		return enviado;
	}


	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}
	
	@Column(name = "TOTAL")
	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
