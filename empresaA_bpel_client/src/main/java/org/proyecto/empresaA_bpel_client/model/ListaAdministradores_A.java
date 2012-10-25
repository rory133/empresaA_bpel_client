package org.proyecto.empresaA_bpel_client.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "listaAdministradores")
public class ListaAdministradores_A {
	private List<Administrador_A> dataAdministrador;

	public List<Administrador_A> getDataAdministrador() {
		return dataAdministrador;
	}

	public void setDataAdministrador(List<Administrador_A> dataAdministrador) {
		this.dataAdministrador = dataAdministrador;
	}



	

}
