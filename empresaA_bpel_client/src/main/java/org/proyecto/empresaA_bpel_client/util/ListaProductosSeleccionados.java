package org.proyecto.empresaA_bpel_client.util;

import java.math.BigDecimal;

public class ListaProductosSeleccionados {
	
	String nombreProducto;
	Integer idCarro;
	Integer cantidad;
	Integer idProductoSeleccionado;
	Integer idproducto_a;
	BigDecimal precio_a;
	BigDecimal subTotal;
	
	
	
	
	
	

	public ListaProductosSeleccionados() {

	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Integer getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdProductoSeleccionado() {
		return idProductoSeleccionado;
	}
	public void setIdProductoSeleccionado(Integer idProductoSeleccionado) {
		this.idProductoSeleccionado = idProductoSeleccionado;
	}
	public Integer getIdproducto_a() {
		return idproducto_a;
	}
	public void setIdproducto_a(Integer idproducto_a) {
		this.idproducto_a = idproducto_a;
	}
	public BigDecimal getPrecio_a() {
		return precio_a;
	}
	public void setPrecio_a(BigDecimal precio_a) {
		this.precio_a = precio_a;
	}
	

	
}
