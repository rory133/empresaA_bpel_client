package org.proyecto.empresaA_bpel_client.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarios_a")
public class ListaUsuarios_A {

	private List<Usuario_A> dataUsuario;

	public List<Usuario_A> getDataUsuario() {
		return dataUsuario;
	}

	public void setDataUsuario(List<Usuario_A> dataUsuario) {
		this.dataUsuario = dataUsuario;
	}




	
}