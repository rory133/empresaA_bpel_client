package org.proyecto.empresaA_bpel_client.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;



@XmlRootElement
@XmlType(propOrder = {"idusuarios_a", "login_usuario_a","nombre_a","apellidos_a","dni_nif_a","email_a", "provincia_a","direccion_a","codigopostal_a","fecha_alta_a","AUTHORITY","ENABLED","password_a"})
@Entity
@Table(name = "cliente_a")
@PrimaryKeyJoinColumn(name="IDUSUARIOS_A")
public class Cliente_A  extends Usuario_A implements Serializable {
	private static final long serialVersionUID = -5527566244002296042L;
	
	@Column(name = "FECHA_ALTA_A")
	private Date fecha_alta_a;
	 

	@Size(min = 2, max = 15, message= "tienes que introducir una direccion")
	@Column(name = "DIRECCION_A")
	private String direccion_a;
	
	@Column(name = "PROVINCIA_A")
	private String provincia_a;
	
	@Pattern(regexp="[0-9]{5}", message="el codigo postal corresponde a 5 numeros") 
	@Column(name = "CODIGOPOSTAL_A")
	private String codigopostal_a;
	

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente_a")
	private Set<Carro_A> carro_a = new HashSet<Carro_A>(0);
	
	
	
	
	
	public Cliente_A() {
	}

	
	
	
	public Date getFecha_alta_a() {
		return fecha_alta_a;
	}

	public void setFecha_alta_a(Date fecha_alta_a) {
		this.fecha_alta_a = fecha_alta_a;
	}
	
	
	
	
	public String getDireccion_a() {
		return direccion_a;
	}




	public void setDireccion_a(String direccion_a) {
		this.direccion_a = direccion_a;
	}




	public String getProvincia_a() {
		return provincia_a;
	}




	public void setProvincia_a(String provincia_a) {
		this.provincia_a = provincia_a;
	}




	public String getCodigopostal_a() {
		return codigopostal_a;
	}




	public void setCodigopostal_a(String codigopostal_a) {
		this.codigopostal_a = codigopostal_a;
	}




	@XmlTransient
	public Set<Carro_A> getCarro_a() {
		return carro_a;
	}

	public void setCarro_a(Set<Carro_A> carro_a) {
		this.carro_a = carro_a;
	}

	  
	
}

