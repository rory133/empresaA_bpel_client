package org.proyecto.empresaA_bpel_client.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "carros_a")
public class ListaCarros_A {
	private List<Carro_A> dataCarro;

	public List<Carro_A> getDataCarro() {
		return dataCarro;
	}

	public void setDataCarro(List<Carro_A> dataCarro) {
		this.dataCarro = dataCarro;
	}
	
	
	

}