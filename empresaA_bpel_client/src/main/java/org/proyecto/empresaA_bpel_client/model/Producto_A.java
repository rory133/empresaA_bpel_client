package org.proyecto.empresaA_bpel_client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;



@XmlRootElement(name="producto_a")
@Entity
@Table(name = "producto_a")
public class Producto_A implements Serializable{
	
	private static final long serialVersionUID = -5527547244002296042L;
	
	  @Id
	  @Column(name = "IDPRODUCTOA", unique = true, nullable = false)	 
	  @GeneratedValue
	  private Integer idproductoa;
	  
        
	  
	  
	  @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto_a")
	  private Set<Producto_ASeleccionado> productos_a_seleccionados=new HashSet<Producto_ASeleccionado>(0);

	  
	  @Size(min = 1, max = 45, message= "hay que indicar un codigo de producto")
	  @NotNull(message = "Whoooops")
	  @Column(name = "NOMBRE_PRODUCTOA")
	  private String nombre_productoA;
	  
	  
	 

	 
	
	  @NotNull(message = "hay que indicar una cantidad de existencias")
	  @Column (name = "CANTIDAD_EXISTENCIAS_A")
	  private Integer cantidad_existencias;
	  
 
	  @NotNull(message = "hay que indicar una longitud")
	  @Digits(integer=10, fraction=2,  message= "la longitud es un numero con dos decimales")
	  @Column (name = "LONGITUD_A")
	  private BigDecimal longitud_a;
	  

	  @NotNull(message = "hay que indicar un diametro")
	  @Digits(integer=10, fraction=2,  message= "el diametro es un numero con dos decimales")
	  @Column (name = "DIAMETRO_A")	 
	  private BigDecimal diametro_a;
	  
	  @NotNull(message = "hay que indicar un precio")
	  @Digits(integer=10, fraction=2,  message= "el precio es un numero con dos decimales")
	  @NumberFormat(style=Style.CURRENCY)
	  @Column (name = "PRECIO_A")
	  private BigDecimal precio_a;
	  




	public Producto_A() {
		
	}

	public Producto_A(String nombre_productoA, Integer cantidad_existencias,
			BigDecimal longitud_a, BigDecimal diametro_a, BigDecimal precio_a) {
		super();
		this.nombre_productoA = nombre_productoA;
		this.cantidad_existencias = cantidad_existencias;
		this.longitud_a = longitud_a;
		this.diametro_a = diametro_a;
		this.precio_a = precio_a;
	}
	public void imprimir(){
		System.out.print(this.getIdproductoa());
		System.out.print(" : ");		
		System.out.print(this.getNombre_productoA());
		System.out.print(" : ");
		System.out.print(this.getCantidad_existencias());
		System.out.print(" : ");
		System.out.print(this.getPrecio_a());
		System.out.println("en producto");
	}

	public Integer getIdproductoa() {
		return idproductoa;
	}



	public void setIdproductoa(Integer idproductoa) {
		this.idproductoa = idproductoa;
	}



	public String getNombre_productoA() {
		return nombre_productoA;
	}

	public void setNombre_productoA(String nombre_productoA) {
		this.nombre_productoA = nombre_productoA;
	}



	public Integer getCantidad_existencias() {
		return cantidad_existencias;
	}



	public void setCantidad_existencias(Integer cantidad_existencias) {
		this.cantidad_existencias = cantidad_existencias;
	}


	public BigDecimal getLongitud_a() {
		return longitud_a;
	}

	public void setLongitud_a(BigDecimal longitud_a) {
		this.longitud_a = longitud_a;
	}


	public BigDecimal getDiametro_a() {
		return diametro_a;
	}


	public void setDiametro_a(BigDecimal diametro_a) {
		this.diametro_a = diametro_a;
	}

	public BigDecimal getPrecio_a() {
		return precio_a;
	}


	public void setPrecio_a(BigDecimal precio_a) {
		this.precio_a = precio_a;
	}


	@XmlTransient
	public Set<Producto_ASeleccionado> getProductos_a_seleccionados() {
		return productos_a_seleccionados;
	}



	public void setProductos_a_seleccionados(
			Set<Producto_ASeleccionado> productos_a_seleccionados) {
		this.productos_a_seleccionados = productos_a_seleccionados;
	}
	
	  

}
