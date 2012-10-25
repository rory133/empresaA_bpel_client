package org.proyecto.empresaA_bpel_client.model;

import java.util.List;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listaClientes")
public class ListaClientes_A {


	private List<Cliente_A> dataCliente;

	public List<Cliente_A> getDataCliente() {
		return dataCliente;
	}

	public void setDataCliente(List<Cliente_A> dataCliente) {
		this.dataCliente = dataCliente;
	}



		
		

	}