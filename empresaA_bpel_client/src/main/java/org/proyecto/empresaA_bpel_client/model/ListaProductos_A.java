package org.proyecto.empresaA_bpel_client.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "productos_a")
public class ListaProductos_A {
	private List<Producto_A> dataProducto;

	public List<Producto_A> getDataProducto() {
		return dataProducto;
	}

	public void setDataProducto(List<Producto_A> dataProducto) {
		this.dataProducto = dataProducto;
	}

	
}
