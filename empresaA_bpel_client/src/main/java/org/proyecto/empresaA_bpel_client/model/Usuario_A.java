package org.proyecto.empresaA_bpel_client.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;




@XmlTransient
@Entity
@Table(name = "usuario_a")
@Inheritance(strategy=InheritanceType.JOINED)
 public class Usuario_A implements Serializable {
	
	private static final long serialVersionUID = -5527566244002296042L;
	
  public Usuario_A() {

	}


  
  @Id
  @Column(name = "IDUSUARIOS_A")
  @GeneratedValue
  private Integer idusuarios_a;
  
  
  @Pattern(regexp="[0-9]{8}[A-Za-z]?", message="formato incorrecto del DNI o NIF") 
  @Column(name = "DNI_NIF_A")
  private String dni_nif_a;
  
  
  
  @NotNull
  @Size(min = 4, max = 15, message= "el login tiene que tener entre 4 y 15 caracteres")
  @Column(name = "LOGIN_USUARIO_A")
  private String login_usuario_a;
  
  
  @NotNull
  @Size(min = 4, max = 15, message= "el password tiene que tener entre 4 y 10 caracteres")
  @Column(name = "PASSWORD_A")
  private String password_a;
  
  @Size(min = 2, max = 45, message= "el nombre tiene que tener al menos dos caracteres")
  @NotNull ( message= "el nombre de cliente no puede estar vacio")
  @Column(name = "NOMBRE_A")
  private String nombre_a;
  
  @Size(min = 2, max = 45, message= "el apellido tiene que tener al menos dos caracteres")
  @NotNull ( message= "el apellido no puede estar vacio")
  @Column(name = "APELLIDOS_A")
  private String apellidos_a;
  
  
  @NotNull
  @Size(min = 5, max = 45, message= "se debe incluir un email")
  @Email (message="formato de email invalido")
  @Column(name = "EMAIL_A")
  private String email_a;

  
  @Column(name = "AUTHORITY")
  private String AUTHORITY;
  
  
  
  @Column(name = "ENABLED")
  private Boolean ENABLED;
  




	public Integer getIdusuarios_a() {
		return idusuarios_a;
	}
	
	@XmlElement
	public void setIdusuarios_a(Integer idusuarios_a) {
		this.idusuarios_a = idusuarios_a;
	}
	
	public String getDni_nif_a() {
		return dni_nif_a;
	}
	@XmlElement
	public void setDni_nif_a(String dni_nif_a) {
		this.dni_nif_a = dni_nif_a;
	}
	

	
	public String getLogin_usuario_a() {
		return login_usuario_a;
	}
	@XmlElement
	public void setLogin_usuario_a(String login_usuario_a) {
		this.login_usuario_a = login_usuario_a;
	}

	public String getPassword_a() {
		return password_a;
	}
	//@XmlElement
	public void setPassword_a(String password_a) {
		this.password_a = password_a;
	}

	public String getNombre_a() {
		return nombre_a;
	}

	public void setNombre_a(String nombre_a) {
		this.nombre_a = nombre_a;
	}
	
	public String getApellidos_a() {
		return apellidos_a;
	}

	public void setApellidos_a(String apellidos_a) {
		this.apellidos_a = apellidos_a;
	}
	
	public String getEmail_a() {
		return email_a;
	}

	public void setEmail_a(String email_a) {
		this.email_a = email_a;
	
	}
	
	public String getAUTHORITY() {
	return AUTHORITY;
	}
	@XmlElement
	public void setAUTHORITY(String aUTHORITY) {
		AUTHORITY = aUTHORITY;
	}

	public Boolean getENABLED() {
		return ENABLED;
	}
	@XmlElement
	public void setENABLED(Boolean eNABLED) {
		ENABLED = eNABLED;
	}


}

