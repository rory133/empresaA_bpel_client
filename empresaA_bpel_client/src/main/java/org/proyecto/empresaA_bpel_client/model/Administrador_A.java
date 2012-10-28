package org.proyecto.empresaA_bpel_client.model;

import java.io.Serializable;




import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"idusuarios_a", "login_usuario_a","nombre_a","apellidos_a","dni_nif_a","email_a","AUTHORITY","ENABLED","password_a","cargo_a","matricula_a"})

@Entity
@Table(name = "administrador_a")
@PrimaryKeyJoinColumn(name="IDUSUARIOS_A")
public class Administrador_A  extends Usuario_A implements Serializable {
	private static final long serialVersionUID = -5527566244002296042L;
	
	@Size(min = 2, max = 15, message= "tienes que introducir un cargo")
	@Column(name = "CARGO_A")
	private String cargo_a;
	
	@Size(min = 2, max = 8, message= "tienes que introducir una matricula")
	@Column(name = "MATRICULA_A")
	private String matricula_a;
	
	
	public Administrador_A() {
	}


	public String getCargo_a() {
		return cargo_a;
	}


	public void setCargo_a(String cargo_a) {
		this.cargo_a = cargo_a;
	}


	public String getMatricula_a() {
		return matricula_a;
	}


	public void setMatricula_a(String matricula_a) {
		this.matricula_a = matricula_a;
	}


}

