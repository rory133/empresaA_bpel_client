package org.proyecto.empresaA_bpel_client.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "listaProductos_aSeleccionados")
public class ListaProductos_ASeleccionados {
	private List<Producto_ASeleccionado> dataProductoASeleccionado;

	public List<Producto_ASeleccionado> getDataProductoASeleccionado() {
		return dataProductoASeleccionado;
	}

	public void setDataProductoASeleccionado(
			List<Producto_ASeleccionado> dataProductoASeleccionado) {
		this.dataProductoASeleccionado = dataProductoASeleccionado;
	}

	
	
	
}