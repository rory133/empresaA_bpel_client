package org.proyecto.empresaA_bpel_client.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.proyecto.empresaA_bpel_client.model.Carro_A;
import org.proyecto.empresaA_bpel_client.model.Cliente_A;
import org.proyecto.empresaA_bpel_client.model.ListaCarros_A;
import org.proyecto.empresaA_bpel_client.model.ListaProductos_A;
import org.proyecto.empresaA_bpel_client.model.ListaProductos_ASeleccionados;
import org.proyecto.empresaA_bpel_client.model.Producto_A;
import org.proyecto.empresaA_bpel_client.model.Producto_ASeleccionado;
import org.proyecto.empresaA_bpel_client.model.TarjetaCredito;
import org.proyecto.empresaA_bpel_client.model.Usuario_A;
import org.proyecto.empresaA_bpel_client.util.ListaPedidos;
import org.proyecto.empresaA_bpel_client.util.ListaProductosSeleccionados;
import org.proyecto.empresaA_bpel_client.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
@RequestMapping("/carro")
@SessionAttributes("carro_A")
public class CarroController {

	
	@Autowired
	private Carro_A carro_a;
	

	
	//@Autowired
	//private Cliente_AServiceImpl cliente_AServiceImpl;
	


	
	@Autowired
	private TarjetaCredito tarjetaCredito;
	
	
	@Autowired
	private Mail mail;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	protected static Logger logger = Logger.getLogger("*en CarroController*en CLIENTE @@@@@@@@@@");
	
	@RequestMapping(value="/sumaProducto", method = RequestMethod.GET)
	public ModelAndView sumaProducto(@RequestParam(value="cantidad")String cantidad, @RequestParam(value="idProducto")String  idProducto, HttpSession session) throws Exception{
	//	carro_AService.save(carro_a);
		logger.info("en /sumaProducto en CLIENTE @@@@@@@@@@");
		logger.info("session.getAttribute('carro_a')-al entrar: " + session.getAttribute("carro_a"));


		
		if (session.getAttribute("carro_a")==null){
			logger.info("if (carro_a.getIdcarro_a()==null)");
			
			//creamos un carro nuevo
			Carro_A carro_a =new Carro_A();
			
			//obtenemos usuario de la sesion
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			logger.info("usuario user user.getUsername() : "+user.getUsername());
			
			Usuario_A usuario= new Usuario_A();
			
			//usuario= cliente_AServiceImpl.findByCliente_A_login_usuario_a(user.getUsername());
			
			//comprobamos que no exista este login
			// Preparamos acceptable media type
			List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
			acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
			
			// preparamos el header
			HttpHeaders headers2 = new HttpHeaders();
			headers2.setAccept(acceptableMediaTypes2);
			HttpEntity<Cliente_A> entity2 = new HttpEntity<Cliente_A>(headers2);

			//enviamos el resquest como POST
			ResponseEntity<Cliente_A> resultado=null;
			
			try {
				
				 resultado=
				restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/clienteLogin/{login}",
								HttpMethod.GET, entity2, Cliente_A.class,user.getUsername());
		
				
						
				} catch (Exception e) {
						logger.error(e);
				}
			
			
			usuario=resultado.getBody();
			
			
			
			
			logger.info("login usuario obtenido:"+usuario.getLogin_usuario_a());
			
			
			
			
			Cliente_A cliente= new Cliente_A();
			cliente=(Cliente_A)usuario;
			
			//sumamos los datos del carro
			carro_a.setCliente_a(cliente);
			carro_a.setFecha_a(new Date());
			carro_a.setEnviado(false);
			carro_a.setPagado(false);
			//ponemos a cero el total
			carro_a.setTotal(new BigDecimal(0));
			logger.info("en /sumaProducto ççççç pongo a cero el carro: "+carro_a.getTotal());
			
			
			//carro_AService.save(carro_a);
			
			//Salvamos el Carro en servidor
			// ----Preparamos acceptable media type----
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_XML);
			
			// preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(acceptableMediaTypes);
			HttpEntity<Carro_A> entity = new HttpEntity<Carro_A>(carro_a,headers);

			//enviamos el resquest como POST
			ResponseEntity<Carro_A> result = null;
			try {
				//ResponseEntity<Cliente_A> clienteDevuelto = 
				result	=restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
								HttpMethod.POST, entity, Carro_A.class);
		
				
						
				} catch (Exception e) {
						logger.error(e);
			}
			
			
			carro_a=result.getBody();
			
			session.setAttribute("carro_a", carro_a);
			logger.info("if (carro_a.getIdcarro_a()==null) despues    :  " +carro_a.getIdcarro_a() );
		}
		
		carro_a=(Carro_A)session.getAttribute("carro_a");
		
		
		
		
		logger.info("imprimo el id del carro: "+carro_a.getIdcarro_a());
		logger.info("imprimo la fecha del carro: "+carro_a.getFecha_a());
		//Carro_A carro_a=new Carro_A(new Date(),)
		logger.info("session.getAttributeNames().toString()"+session.getAttributeNames().toString());
		logger.info("session.getAttribute('carro_a'): " + session.getAttribute("carro_a"));
		logger.info("session Id:"+session.getId());
		logger.info("cantidad Recibida"+ cantidad);
		
		logger.info("idproducto Recibido"+ idProducto);
		//logger.info("usuario de la sesion : "+session.getAttribute("user"));
		logger.info("carro de la sesion : "+session.getAttribute("carro_a"));
		
		
		logger.info("datos carro id: "+carro_a.getIdcarro_a());
		
		
		
		

		
	
		
		Producto_A producto=new Producto_A();
		//producto=productos_AServiceImpl.findByProducto_AIdProducto_a(idProducto);
		
		//obtenemos el producto correspondiente al id del producto que se añade al carro
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Producto_A> entity = new HttpEntity<Producto_A>(headers);

				//enviamos el resquest como POST
				ResponseEntity<Producto_A> result=null;
				
				try {
					
					 result=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/producto/{id}",
									HttpMethod.GET, entity, Producto_A.class,idProducto);
			
					
							
					} catch (Exception e) {
							logger.error(e);
					} 
					
				

				producto=result.getBody();
		
				logger.info("en /sumaProducto nombre del producto_a obtenido de servidor: "+producto.getNombre_productoA());
		
		
		
		
		
		
		
				
		
		Producto_ASeleccionado producto_ASeleccionado=new Producto_ASeleccionado();
		
		producto_ASeleccionado.setProducto_a(producto);
		logger.info(" ide carro_a antes de ponerlo en producto seleccionado: "+carro_a.getIdcarro_a());
		producto_ASeleccionado.setCarro_a(carro_a);	
		//producto_ASeleccionado.setCantidad(Integer.parseInt(cantidad));
		
		
		
		logger.info("carro_a.getIdcarro_a():"+carro_a.getIdcarro_a());
		Producto_ASeleccionado producto_ASeleccionado_test=new Producto_ASeleccionado();
		logger.info("Producto_ASeleccionado producto_ASeleccionado_test=new Producto_ASeleccionado();");
		
		//producto_ASeleccionado_test=producto_ASeleccionadoService.findByProducto_ASeleccionadoIdProducto_a_y_carro_a(String.valueOf(producto.getIdproductoa()),String.valueOf( carro_a.getIdcarro_a()));
		
		
		
		
		      //obtenemos el productoSelccionado por el id del producto y el id del carro si en un modificacion de un producto previamente elegido se trata de forma distinta
				// Preparamos acceptable media type
						List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
						acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
						
						// preparamos el header
						HttpHeaders headers2 = new HttpHeaders();
						headers2.setAccept(acceptableMediaTypes2);
						HttpEntity<Producto_ASeleccionado> entity2 = new HttpEntity<Producto_ASeleccionado>(headers2);
						
						logger.info("IMPRIMO EL IDCARRO  String.valueOf( carro_a.getIdcarro_a())"+String.valueOf( carro_a.getIdcarro_a()));
						logger.info("IMPRIMO EL IDCARRO  "+ carro_a.getIdcarro_a());

						//enviamos el resquest como POST
						ResponseEntity<Producto_ASeleccionado> result2=null;
						
						/*
						Map<String, String> params = new HashMap<String, String>();
					    params.put("id", idProducto);
					    params.put("idCarro", String.valueOf( carro_a.getIdcarro_a()));
						*/
						try {
							
							 result2=
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIDProductoIdCarro/{id}/{idCarro}",
											HttpMethod.GET, entity2, Producto_ASeleccionado.class,idProducto,String.valueOf( carro_a.getIdcarro_a()));
									//HttpMethod.GET, entity2, Producto_ASeleccionado.class,params);
							
									
							} catch (Exception e) {
									logger.error(e);
							} 

						producto_ASeleccionado_test=result2.getBody();
		
		
		logger.info("DESPUES DE OBTENER producto_ASeleccionado_test=producto_ASeleccionadoService.findByProducto_ASeleccionadoIdProducto_a_y_carro_a.....");
		
		//comprobamos si ese producto ya estaba en el carro si es asi se trata de forma distinta la actualizacion de la cantidad
		if (null!=producto_ASeleccionado_test){
			//controlamos que el pedido no exceda la cantidad de existencias. aqui se tiene en cuenta los que se habian pedido antes
			if ((producto_ASeleccionado_test.getCantidad()+producto.getCantidad_existencias())<(Integer.parseInt(cantidad))){
				//si excede devolmemos la lista de productos con el mensaje
				//primero obtenemos la lista de productos
				// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
				//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<ListaProductos_A> entity3 = new HttpEntity<ListaProductos_A>(headers3);
				
				// enviamos el request como GET
				
				ResponseEntity<ListaProductos_A> result3=null;
				try {
					//realizamos consulta a servidor para que nos envie todos los clientes
				 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity3, ListaProductos_A.class);
				
					
					
							
					} catch (Exception e) {
							logger.error(e);
					}
				
				List<Producto_A> lista = result3.getBody().getDataProducto();
				//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		     	
		     	
		     	//devolvemos la vista
		     	
				ModelAndView mav= new ModelAndView("producto_a/listaProductos");
				mav.addObject("productos", lista);
				mav.addObject("errordeCantidad","no puede pedir mas cantidad que las existencias");
				mav.addObject("productoPedido",idProducto);
				return mav;
				}
			
			
		//logger.info("producto_ASeleccionado_test idproducto="+producto_ASeleccionado_test.getIdproductoa());
		logger.info("producto_ASeleccionado_test idcarro="+producto_ASeleccionado_test.getCarro_a().getIdcarro_a());
		logger.info("producto_ASeleccionado cantidad" +producto_ASeleccionado.getCantidad());
		logger.info("producto_ASeleccionado id producto" +producto_ASeleccionado.getProducto_a().getIdproductoa());
		logger.info("producto_ASeleccionado id " +producto_ASeleccionado.getIdproductoSeleccionado());
		logger.info("producto_ASeleccionado id " +producto_ASeleccionado.getCarro_a().getIdcarro_a());
		logger.info("producto_ASeleccionado_test id " +producto_ASeleccionado_test.getIdproductoSeleccionado());
		
		
		//actualizacomos el valor de existencia
		producto.setCantidad_existencias(producto.getCantidad_existencias()+producto_ASeleccionado_test.getCantidad()-Integer.parseInt(cantidad));
		
		//actualizamos la cantidad de producto_ASeleccionado
		producto_ASeleccionado.setCantidad(Integer.parseInt(cantidad));
		
		//actualizamos producto con el nuevo valor de cantidad
		
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
		acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers4 = new HttpHeaders();
		headers4.setAccept(acceptableMediaTypes4);
		HttpEntity<Producto_A> entity4 = new HttpEntity<Producto_A>(producto,headers4);

		//enviamos el resquest como POST
		
		try {
			//ResponseEntity<Cliente_A> clienteDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/admin/producto",
							HttpMethod.PUT, entity4, Producto_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		//productos_AServiceImpl.update(producto);
		
		//actualizamos el total del carro
		
		
		logger.info("en /sumaProducto ççççç valor carroTotal antes de restar el valor anterior: "+carro_a.getTotal());
		//restamos el subtotal del producto seleccionado al total del carro		producto_ASeleccionado_test
		carro_a.setTotal(carro_a.getTotal().subtract(producto_ASeleccionado_test.getSubTotal()));
		
		logger.info("en /sumaProducto ççççç valor carroTotal DESPUES de restar el valor anterior: "+carro_a.getTotal());
		
		//añadidmo valor al subtotal del producto seleccionado
		BigDecimal temp = new BigDecimal(Integer.parseInt(cantidad));
		producto_ASeleccionado.setSubTotal(producto.getPrecio_a().multiply(temp));
		logger.info("en /sumaProducto ççççç VALOR DE PRODUCTOBSELECCIONADO CUANDO SE ACTUALIZA VALOR YA QUE SE HABIA PEDIDO ANTES: "+producto_ASeleccionado.getSubTotal());
		
		//acutalizamos el nuevo valor del total en carro
		carro_a.setTotal(carro_a.getTotal().add(producto_ASeleccionado.getSubTotal()));
		//actualizacomos producto_ASeleccionado
		
		producto_ASeleccionado.setIdproductoSeleccionado(producto_ASeleccionado_test.getIdproductoSeleccionado());
		
		
		// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes5 = new ArrayList<MediaType>();
				acceptableMediaTypes5.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers5 = new HttpHeaders();
				headers5.setAccept(acceptableMediaTypes5);
				HttpEntity<Producto_ASeleccionado> entity5 = new HttpEntity<Producto_ASeleccionado>(producto_ASeleccionado,headers5);

				//enviamos el resquest como POST
				
				try {
				
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionado",
					//restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado",
									HttpMethod.PUT, entity5, Producto_ASeleccionado.class);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
		
				//producto_ASeleccionadoService.update(producto_ASeleccionado);

		
		
		}else{
			//El producto se seleccion por primera vez para este carro, controlamos que el pedido no exceda la cantidad de existencias.
			
			logger.info("producto_cantidad de existencias::::::::"+producto.getCantidad_existencias());
			logger.info("producto_cantidad pedidas::::::::"+Integer.parseInt(cantidad));
			if (producto.getCantidad_existencias()<(Integer.parseInt(cantidad))){
				//la cantidad pedida sobrepasa la cantidad de existencias, devolvemos la vista de la lista de productos con el mensaje 
				
				logger.info("la cantidad pedida supera las existencias::::::::");
				//si excede devolmemos la lista de productos con el mensaje
				//primero obtenemos la lista de productos
				// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
				//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<ListaProductos_A> entity3 = new HttpEntity<ListaProductos_A>(headers3);
				
				// enviamos el request como GET
				
				ResponseEntity<ListaProductos_A> result3=null;
				try {
					//realizamos consulta a servidor para que nos envie todos los productos
				 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity3, ListaProductos_A.class);
				
					
					
							
					} catch (Exception e) {
							logger.error(e);
					}
				
				List<Producto_A> lista = result3.getBody().getDataProducto();
				//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
				
				ModelAndView mav= new ModelAndView("producto_a/listaProductos");
				mav.addObject("productos", lista);
				mav.addObject("errordeCantidad","no puede pedir mas cantidad que las existencias");
				mav.addObject("productoPedido",idProducto);
				return mav;
				}
			

			
			
			logger.info("la cantidad pedida NO supera las existencias LAS ACTUALIZAMOS EN PRODUCTO::::::::");
			//actualizacomos el valor de existencias en el producto
			//añadimos el carro al productoSeleccionado
			producto_ASeleccionado.setCarro_a(carro_a);	
			producto.setCantidad_existencias(producto.getCantidad_existencias()-Integer.parseInt(cantidad));
			//y lo salvamos
			
			// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
				acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers4 = new HttpHeaders();
				headers4.setAccept(acceptableMediaTypes4);
				HttpEntity<Producto_A> entity4 = new HttpEntity<Producto_A>(producto,headers4);

				//enviamos el resquest como POST
				
				try {
					//ResponseEntity<Cliente_A> clienteDevuelto = 
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/admin/producto",
									HttpMethod.PUT, entity4, Producto_A.class);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
				
				//productos_AServiceImpl.update(producto);	
				
				//actualizamos la cantidad de producto_ASeleccionado
				producto_ASeleccionado.setCantidad(Integer.parseInt(cantidad));
				
				//añadidmo valor al subtotal del producto seleccionado
				BigDecimal temp = new BigDecimal(Integer.parseInt(cantidad));
				producto_ASeleccionado.setSubTotal(producto.getPrecio_a().multiply(temp));
				logger.info("en /sumaProducto ççççç VALOR DE SUTTOTAL DE PRODUCTO_ASELECCIONADO DESPUED DE AÑADIRLE SUBTOTAL POR PRIMERA VEZ: "+producto_ASeleccionado.getSubTotal());
				
				
				logger.info("SALVAMOS PRUDUCTO SELECCIONADO::::::::");
				//salvamos producto_ASeleccionado
				List<MediaType> acceptableMediaTypes5 = new ArrayList<MediaType>();
				acceptableMediaTypes5.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers5 = new HttpHeaders();
				headers5.setAccept(acceptableMediaTypes5);
				HttpEntity<Producto_ASeleccionado> entity5 = new HttpEntity<Producto_ASeleccionado>(producto_ASeleccionado,headers5);

				//enviamos el resquest como POST
				
				try {
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionado",
							//restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado",
									HttpMethod.POST, entity5, Producto_ASeleccionado.class);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
				logger.info("ACTUALIZAMOS CARRO::::::::");
				carro_a.getProducto_ASeleccionado().add(producto_ASeleccionado);
				
				//sumamos el subtotal del producto seleccionado al total del carro		
				carro_a.setTotal(carro_a.getTotal().add(producto_ASeleccionado.getSubTotal()));
		
		}
		
		//Actualizamos el carro
		
		// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
				acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers4 = new HttpHeaders();
				headers4.setAccept(acceptableMediaTypes4);
				HttpEntity<Carro_A> entity4 = new HttpEntity<Carro_A>(carro_a,headers4);

				//enviamos el resquest como PUT
				
				try {
					//ResponseEntity<Cliente_A> clienteDevuelto = 
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
									HttpMethod.PUT, entity4, Carro_A.class);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
				//carro_AService.update(carro_a); NO ESTABA
		
		
				logger.info("OBTENEMOS LA LISTA DE LOS PRODUCTOS SELECCIONADOS HASTA AHORA:::::::: ID IDCARRO ES: "+String.valueOf( carro_a.getIdcarro_a()));
	    //obtenemos la lista de los productos seleccionados hasta ahora
		//para poder indicar la cantidad seleccionada de cada uno en la vista
				// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
				//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<ListaProductos_ASeleccionados> entity3 = new HttpEntity<ListaProductos_ASeleccionados>(headers3);
				
				// enviamos el request como GET
				
				ResponseEntity<ListaProductos_ASeleccionados> result3=null;
				try {
					//realizamos consulta a servidor para que nos envie todos los productos del carro
					
				 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIdCarro/{idCarro}", 
						 HttpMethod.GET, entity3, ListaProductos_ASeleccionados.class,String.valueOf( carro_a.getIdcarro_a()));
				
					
					
							
					} catch (Exception e) {
							logger.error(e);
					}
				List<Producto_ASeleccionado> listaProductosRecibida = null;
				listaProductosRecibida = result3.getBody().getDataProductoASeleccionado();
			//	logger.info("ACABAMOS DE OBTENER LA LISTA DE LOS PRODUCTOS SELECCIONADOS HASTA AHORA (DEL CARRO):::::::: tamaño "+listaProductosRecibida.size());
				
				//List<Producto_ASeleccionado> listaProductosRecibida=producto_ASeleccionadoService.findByProducto_ASeleccionadoPorIdcarro_a(String.valueOf( carro_a.getIdcarro_a()));
		
				
		Set<ListaProductosSeleccionados> listaProductos=new HashSet<ListaProductosSeleccionados>(0);
		Iterator<Producto_ASeleccionado> itr =listaProductosRecibida.iterator();
		while (itr.hasNext()) {
			Producto_ASeleccionado element = itr.next();
			ListaProductosSeleccionados lista = new ListaProductosSeleccionados();
			lista.setCantidad(element.getCantidad());
			lista.setIdCarro(element.getCarro_a().getIdcarro_a());
			lista.setIdproducto_a(element.getProducto_a().getIdproductoa());
			lista.setIdProductoSeleccionado(element.getIdproductoSeleccionado());
			lista.setNombreProducto(element.getProducto_a().getNombre_productoA());
			lista.setPrecio_a(element.getProducto_a().getPrecio_a());
			lista.setSubTotal(element.getSubTotal());
			listaProductos.add(lista);
			
		}
		//obtenemos la lista de productos para enviarla a la vista
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes5 = new ArrayList<MediaType>();
		acceptableMediaTypes5.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers5 = new HttpHeaders();
		headers5.setAccept(acceptableMediaTypes5);
		HttpEntity<ListaProductos_A> entity5 = new HttpEntity<ListaProductos_A>(headers5);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaProductos_A> result5=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los productos
		 result5 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity5, ListaProductos_A.class);
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		List<Producto_A> lista = result5.getBody().getDataProducto();		
		//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		
		
		
		ModelAndView mav= new ModelAndView("producto_a/listaProductos");
		mav.addObject("productos", lista);
		//añadimos la lista de los seleccionados hasta ahora
		mav.addObject("productosSeleccionados",listaProductos);
		return mav;
		//return new ModelAndView("redirect:../../productos/listado");
	}
	
	
	
	
	
	
	@RequestMapping(value="/verCarro", method = RequestMethod.GET)
	public ModelAndView verCarro( HttpSession session) throws Exception{
		logger.info("en /verCarro en CLIENTE @@@@@@@@@@");
		logger.info("en /verCarro en CLIENTE @@@@@@@@@@ id Carro "+String.valueOf( carro_a.getIdcarro_a()));
		//Si aun no hay ningun carro se envia un mensaje en la vista
		if (session.getAttribute("carro_a")==null){
			
			
			logger.info("en ver Carro, e carro esta vacio");
			ModelAndView mav= new ModelAndView("producto_a/listaProductos");
			
			
			// Preparamos acceptable media type
			List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
			acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
			//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			
			// preparamos el header
			HttpHeaders headers3 = new HttpHeaders();
			headers3.setAccept(acceptableMediaTypes3);
			HttpEntity<ListaProductos_A> entity3 = new HttpEntity<ListaProductos_A>(headers3);
			
			// enviamos el request como GET
			//ModelAndView mav=new ModelAndView("producto_a/listaProductos");
			try {
				//realizamos consulta a servidor para que nos envie todos los clientes
				ResponseEntity<ListaProductos_A> result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity3, ListaProductos_A.class);
			
				mav.addObject("lista", result3.getBody().getDataProducto());
				
						
				} catch (Exception e) {
						logger.error(e);
				}
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
			
			
			
			
			mav.addObject("errorCarroVacio","¡¡¡el carro esta vacio, aun no ha seleccionado ningun producto!!!");
			//mav.addObject("productos", lista);		
			return mav;

		}
		//si hay carro se crea la vista con los productos que contiene
		//se obtienen los productos del carro
		
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
		acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setAccept(acceptableMediaTypes3);
		HttpEntity<ListaProductos_ASeleccionados> entity3 = new HttpEntity<ListaProductos_ASeleccionados>(headers3);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaProductos_ASeleccionados> result3=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los clientes
		 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIdCarro/{idCarro}", 
				 HttpMethod.GET, entity3, ListaProductos_ASeleccionados.class,String.valueOf( carro_a.getIdcarro_a()));
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		List<Producto_ASeleccionado> listaProductosRecibida = result3.getBody().getDataProductoASeleccionado();
		
		//List<Producto_ASeleccionado> listaProductosRecibida=producto_ASeleccionadoService.findByProducto_ASeleccionadoPorIdcarro_a(String.valueOf( carro_a.getIdcarro_a()));
		
		Set<ListaProductosSeleccionados> listaProductos=new HashSet<ListaProductosSeleccionados>(0);
		
		
		if (null!=listaProductosRecibida){
		//if (!listaProductosRecibida.isEmpty()){888888888
		//logger.info("tamaño lista productosSeleccionados en esteCarro"+listaProductosRecibida.size());
		
		Iterator<Producto_ASeleccionado> itr =listaProductosRecibida.iterator();
		while (itr.hasNext()) {
			Producto_ASeleccionado element = itr.next();
			ListaProductosSeleccionados lista = new ListaProductosSeleccionados();
			lista.setCantidad(element.getCantidad());
			lista.setIdCarro(element.getCarro_a().getIdcarro_a());
			lista.setIdproducto_a(element.getProducto_a().getIdproductoa());
			lista.setIdProductoSeleccionado(element.getIdproductoSeleccionado());
			lista.setNombreProducto(element.getProducto_a().getNombre_productoA());
			lista.setPrecio_a(element.getProducto_a().getPrecio_a());
			lista.setSubTotal(element.getSubTotal());
			listaProductos.add(lista);
			
		}
		
		}else listaProductos=null;
		
		
		
		//preparamos la vista
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<ListaProductos_A> entity = new HttpEntity<ListaProductos_A>(headers);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaProductos_A> result=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los clientes
		 result = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity, ListaProductos_A.class);
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		List<Producto_A> lista = result.getBody().getDataProducto();		
		//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		ModelAndView mav= new ModelAndView("carro_a/verCarroActual");
		mav.addObject("productos", lista);
		mav.addObject("idCarro", String.valueOf( carro_a.getIdcarro_a()));
		mav.addObject("productosSeleccionados",listaProductos);
		return mav;
	}
	 
	
	
	
	
	
	@RequestMapping(value="/eliminarProductoCarro", method = RequestMethod.GET)
	public ModelAndView eliminarProductoCarro(@RequestParam(value="idProductoSeleccionado")String  idProductoSeleccionado,@RequestParam(value="idProducto")String  idProducto, @RequestParam(value="cantidad")String cantidad,  @RequestParam(value="idCarro")String idCarro, HttpSession session) throws Exception{
		logger.info("en /eliminarProductoCarro en CLIENTE @@@@@@@@@@");
		
	
		
		
		
		//eliminar el producto
		Producto_ASeleccionado producto_ASeleccionado=new Producto_ASeleccionado();
		
		//lo buscamos por el id del producto y del carro
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
		acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(acceptableMediaTypes2);
		HttpEntity<Producto_ASeleccionado> entity2 = new HttpEntity<Producto_ASeleccionado>(headers2);

		//enviamos el resquest como POST
		ResponseEntity<Producto_ASeleccionado> result2=null;
		
		try {
			
			 result2=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIDProductoIdCarro/{id}/{idCarro}",
						//	HttpMethod.GET, entity2, Producto_ASeleccionado.class,idProducto,String.valueOf( carro_a.getIdcarro_a()));
					HttpMethod.GET, entity2, Producto_ASeleccionado.class,idProducto,idCarro);
			
					
			} catch (Exception e) {
					logger.error(e);
			} 
		
		producto_ASeleccionado=result2.getBody();
		//producto_ASeleccionado=producto_ASeleccionadoService.findByProducto_ASeleccionadoIdProducto_a_y_carro_a(idProducto, String.valueOf(carro_a.getIdcarro_a()));
		
		
		
		//obtenemos el carro
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes1 = new ArrayList<MediaType>();
		acceptableMediaTypes1.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers1 = new HttpHeaders();
		headers1.setAccept(acceptableMediaTypes1);
		HttpEntity<Carro_A> entity1 = new HttpEntity<Carro_A>(headers1);

		//enviamos el resquest como GET
		ResponseEntity<Carro_A> carroDevuelto=null;
		try {
			 carroDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a/{id}",
							HttpMethod.GET, entity1, Carro_A.class,idCarro);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}

		Carro_A carroTemp=carroDevuelto.getBody();
		//reducimos el total del carro el valor del producto seleccionado
		carroTemp.setTotal(carroTemp.getTotal().subtract(producto_ASeleccionado.getSubTotal()));


		//eliminamos el productoSeleccionado
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Producto_ASeleccionado> entity = new HttpEntity<Producto_ASeleccionado>(headers);


		try {
			
			// result=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionado/{id}",
							HttpMethod.DELETE, entity, Producto_ASeleccionado.class,idProducto,String.valueOf(producto_ASeleccionado.getIdproductoSeleccionado()));
					
	
			
					
			} catch (Exception e) {
					logger.error(e);
			} 
		 	//producto_ASeleccionadoService.delete(producto_ASeleccionado);
		
		//sumamos cantidad que tenia el producto seleccionado a la cantidad de existencias del producto
		
		
		Producto_A producto=new Producto_A();
		//encontramos el producto
		
		//obtenemos el producto correspondiente a ese id
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<Producto_A> entity3 = new HttpEntity<Producto_A>(headers3);

				//enviamos el resquest como POST
				ResponseEntity<Producto_A> result3=null;
				
				try {
					
					 result3=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/producto/{id}",
									HttpMethod.GET, entity3, Producto_A.class,idProducto);
			
					
							
					} catch (Exception e) {
							logger.error(e);
					}
		
		
				producto=result3.getBody();		
				//producto=productos_AServiceImpl.findByProducto_AIdProducto_a(idProducto);
				
				
		
		//actualizamos cantidad
		producto.setCantidad_existencias(producto.getCantidad_existencias()+Integer.parseInt(cantidad));
		
		//Actualizamos producto
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
		acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers4 = new HttpHeaders();
		headers4.setAccept(acceptableMediaTypes4);
		HttpEntity<Producto_A> entity4 = new HttpEntity<Producto_A>(producto,headers4);

		//enviamos el resquest como PUT
		
		try {
			 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/admin/producto",
							HttpMethod.PUT, entity4, Producto_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		
		//productos_AServiceImpl.update(producto);
		
		//Actualizamos el carro
		
				// ----Preparamos acceptable media type----
						List<MediaType> acceptableMediaTypes6 = new ArrayList<MediaType>();
						acceptableMediaTypes6.add(MediaType.APPLICATION_XML);
						
						// preparamos el header
						HttpHeaders headers6 = new HttpHeaders();
						headers6.setAccept(acceptableMediaTypes6);
						HttpEntity<Carro_A> entity6 = new HttpEntity<Carro_A>(carroTemp,headers6);

						//enviamos el resquest como PUT
						
						try {
							//ResponseEntity<Cliente_A> clienteDevuelto = 
									restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
											HttpMethod.PUT, entity6, Carro_A.class);
					
							
									
							} catch (Exception e) {
									logger.error(e);
						}
		
		
		
		
		
		
		
		
		
		//devolvemos vista a verCarroActual
		return new ModelAndView("redirect: verCarro");
	}
	
	
	
	@RequestMapping(value="/eliminarProductoCarroActual", method = RequestMethod.GET)
	public ModelAndView eliminarProductoCarroActual(@RequestParam(value="idProductoSeleccionado")String  idProductoSeleccionado,@RequestParam(value="idProducto")String  idProducto, @RequestParam(value="cantidad")String cantidad, HttpSession session) throws Exception{
		logger.info("en /eliminarProductoCarroActual en CLIENTE @@@@@@@@@@");
		
	
		
		
		carro_a=(Carro_A)session.getAttribute("carro_a");
		//eliminar el producto
		Producto_ASeleccionado producto_ASeleccionado=new Producto_ASeleccionado();
		
		//lo buscamos por el id del producto y del carro
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
		acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(acceptableMediaTypes2);
		HttpEntity<Producto_ASeleccionado> entity2 = new HttpEntity<Producto_ASeleccionado>(headers2);

		//enviamos el resquest como POST
		ResponseEntity<Producto_ASeleccionado> result2=null;
		
		try {
			
			 result2=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIDProductoIdCarro/{id}/{idCarro}",
						//	HttpMethod.GET, entity2, Producto_ASeleccionado.class,idProducto,String.valueOf( carro_a.getIdcarro_a()));
					HttpMethod.GET, entity2, Producto_ASeleccionado.class,idProducto,carro_a.getIdcarro_a());
			
					
			} catch (Exception e) {
					logger.error(e);
			} 
		
		producto_ASeleccionado=result2.getBody();
		//producto_ASeleccionado=producto_ASeleccionadoService.findByProducto_ASeleccionadoIdProducto_a_y_carro_a(idProducto, String.valueOf(carro_a.getIdcarro_a()));
		
		
		logger.info("en /eliminarProductoCarroActual en CLIENTE @@@@@@@@@@ valor del total ANTES de reducir lo que habia pedido"+carro_a.getTotal());
		//reducimos el total del carro el valor del producto seleccionado
		carro_a.setTotal(carro_a.getTotal().subtract(producto_ASeleccionado.getSubTotal()));
		logger.info("en /eliminarProductoCarroActual en CLIENTE @@@@@@@@@@ valor del total DESPUES de reducir lo que habia pedido"+carro_a.getTotal());
		
		


		//eliminamos el productoSeleccionado
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Producto_A> entity = new HttpEntity<Producto_A>(headers);


		try {
			
			// result=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionado/{id}",
							HttpMethod.DELETE, entity, Producto_ASeleccionado.class,String.valueOf(producto_ASeleccionado.getIdproductoSeleccionado()));
					
	
			
					
			} catch (Exception e) {
					logger.error(e);
			} 
		 	//producto_ASeleccionadoService.delete(producto_ASeleccionado);
		
		//sumamos cantidad que tenia el producto seleccionado a la cantidad de existencias del producto
		
		
		Producto_A producto=new Producto_A();
		//encontramos el producto
		
		//obtenemos el producto correspondiente a ese id
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<Producto_A> entity3 = new HttpEntity<Producto_A>(headers3);

				//enviamos el resquest como POST
				ResponseEntity<Producto_A> result3=null;
				
				try {
					
					 result3=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/producto/{id}",
									HttpMethod.GET, entity3, Producto_A.class,idProducto);
			
					
							
					} catch (Exception e) {
							logger.error(e);
					}
		
		
				producto=result3.getBody();		
				//producto=productos_AServiceImpl.findByProducto_AIdProducto_a(idProducto);
				
				
		
		//actualizamos cantidad
		producto.setCantidad_existencias(producto.getCantidad_existencias()+Integer.parseInt(cantidad));
		
		//Actualizamos producto
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
		acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers4 = new HttpHeaders();
		headers4.setAccept(acceptableMediaTypes4);
		HttpEntity<Producto_A> entity4 = new HttpEntity<Producto_A>(producto,headers4);

		//enviamos el resquest como PUT
		
		try {
			 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos/admin/producto",
							HttpMethod.PUT, entity4, Producto_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		
		//productos_AServiceImpl.update(producto);
		
		//Actualizamos el carro
		
				// ----Preparamos acceptable media type----
						List<MediaType> acceptableMediaTypes6 = new ArrayList<MediaType>();
						acceptableMediaTypes6.add(MediaType.APPLICATION_XML);
						
						// preparamos el header
						HttpHeaders headers6 = new HttpHeaders();
						headers6.setAccept(acceptableMediaTypes6);
						HttpEntity<Carro_A> entity6 = new HttpEntity<Carro_A>(carro_a,headers6);

						//enviamos el resquest como PUT
						
						try {
							//ResponseEntity<Cliente_A> clienteDevuelto = 
									restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
											HttpMethod.PUT, entity6, Carro_A.class);
					
							
									
							} catch (Exception e) {
									logger.error(e);
						}
		
		
		
		
		
		
		
		
		
		//devolvemos vista a verCarroActual
		return new ModelAndView("redirect: verCarro");
	}
	
	
	
	
	
	@RequestMapping(value="/verTodosLosPedidos", method = RequestMethod.GET)
	public ModelAndView verTodosLosPedidos( HttpSession session) throws Exception{
		
		logger.info("en /carro/verTodos/LosPedidos en CLIENTE @@@@@@@@@@");
		//buscamos todos los carros
		//que sea una lista ordenada
		Set<ListaPedidos> listaCarrosAMostrar=new TreeSet<ListaPedidos>();
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
		acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setAccept(acceptableMediaTypes3);
		HttpEntity<ListaCarros_A> entity3 = new HttpEntity<ListaCarros_A>(headers3);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaCarros_A> result3=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los carros
		 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carros",
				 HttpMethod.GET, entity3, ListaCarros_A.class);
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
	
		List<Carro_A> listaCarros=result3.getBody().getDataCarro();
		//List<Carro_A> listaCarros =carro_AService.findAll();
		
		
		
		//buscamos los datos de cada carro
		Iterator<Carro_A> iterCarro =listaCarros.iterator();
		
		
		
		while (iterCarro.hasNext()) {
			ListaPedidos listaCarrosPedidos = new ListaPedidos();
			Carro_A elementoCarro = iterCarro.next();
			listaCarrosPedidos.setIdCliente(elementoCarro.getCliente_a().getIdusuarios_a());
			listaCarrosPedidos.setLoginCliente(elementoCarro.getCliente_a().getLogin_usuario_a());
			listaCarrosPedidos.setPagado(elementoCarro.getPagado());
			listaCarrosPedidos.setEnviado(elementoCarro.getEnviado());
			listaCarrosPedidos.setIdCarro(elementoCarro.getIdcarro_a());
			listaCarrosPedidos.setFechaPedido(elementoCarro.getFecha_a());
			listaCarrosPedidos.setTotal(elementoCarro.getTotal());
			//logger.info("imprimo el login del usuario desde listaCarrospedidos: "+listaCarrosPedidos.getLoginCliente());
			
			//le añadimos tambien los productos seleccionados de cada carro
			// Preparamos acceptable media type
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_XML);
			//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			
			// preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(acceptableMediaTypes);
			HttpEntity<ListaProductos_ASeleccionados> entity = new HttpEntity<ListaProductos_ASeleccionados>(headers);
			
			// enviamos el request como GET
			
			ResponseEntity<ListaProductos_ASeleccionados> result=null;
			try {
				//realizamos consulta a servidor para que nos envie todos los clientes
			 result = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIdCarro/{idCarro}", 
					 HttpMethod.GET, entity, ListaProductos_ASeleccionados.class,String.valueOf(elementoCarro.getIdcarro_a()));
			
				
				
						
				} catch (Exception e) {
						logger.error(e);
				}
			
			List<Producto_ASeleccionado> listaProductosCarro= null;
			listaProductosCarro=result.getBody().getDataProductoASeleccionado();			
			//List<Producto_ASeleccionado> listaProductosCarro=producto_ASeleccionadoService.findByProducto_ASeleccionadoPorIdcarro_a(String.valueOf(elementoCarro.getIdcarro_a()));
			
			
			if(null!=listaProductosCarro){
			
			Iterator<Producto_ASeleccionado> itr =listaProductosCarro.iterator();
			Set<ListaProductosSeleccionados> listaProductos=new HashSet<ListaProductosSeleccionados>(0);
			while (itr.hasNext()) {
				Producto_ASeleccionado element = itr.next();
				ListaProductosSeleccionados lista = new ListaProductosSeleccionados();
				lista.setCantidad(element.getCantidad());
				lista.setIdCarro(element.getCarro_a().getIdcarro_a());
				lista.setIdproducto_a(element.getProducto_a().getIdproductoa());
				lista.setIdProductoSeleccionado(element.getIdproductoSeleccionado());
				lista.setNombreProducto(element.getProducto_a().getNombre_productoA());
				lista.setPrecio_a(element.getProducto_a().getPrecio_a());
				lista.setSubTotal(element.getSubTotal());
				listaProductos.add(lista);			
			}
			listaCarrosPedidos.setListaProductosSeleccionados(listaProductos);
			listaCarrosAMostrar.add(listaCarrosPedidos);
			
			}else{
				listaCarrosPedidos.setListaProductosSeleccionados(null);
				listaCarrosAMostrar.add(listaCarrosPedidos);
			}
			
		}
		
		
		ModelAndView mav= new ModelAndView("carro_a/verPedidos");
		mav.addObject("TodosLosPedidos", listaCarrosAMostrar);
		return mav;
		
	}
	
	
	
	@RequestMapping(value="/verDetallesCarro", method = RequestMethod.GET)
	public ModelAndView verDetallesCarro( @RequestParam(value="idCarro")String  idCarro) throws Exception{
		
		Set<ListaProductosSeleccionados> listaProductos=new HashSet<ListaProductosSeleccionados>(0);
		
		//obtenemos los productos seleccionados del carro
		
		
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
		acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setAccept(acceptableMediaTypes3);
		HttpEntity<ListaProductos_ASeleccionados> entity3 = new HttpEntity<ListaProductos_ASeleccionados>(headers3);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaProductos_ASeleccionados> result3=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los clientes
		 result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productoASeleccionado/producto_a_seleccionadoIdCarro/{idCarro}", 
				 HttpMethod.GET, entity3, ListaProductos_ASeleccionados.class,idCarro);
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		List<Producto_ASeleccionado> listaProductosRecibida = result3.getBody().getDataProductoASeleccionado();
		//List<Producto_ASeleccionado> listaProductosRecibida=producto_ASeleccionadoService.findByProducto_ASeleccionadoPorIdcarro_a(idCarro);
	//	if (!listaProductosRecibida.isEmpty()){
		//logger.info("tamaño lista productosSeleccionados en esteCarro"+listaProductosRecibida.size());
		if (null!=listaProductosRecibida){
		Iterator<Producto_ASeleccionado> itr =listaProductosRecibida.iterator();
		while (itr.hasNext()) {
			Producto_ASeleccionado element = itr.next();
			ListaProductosSeleccionados lista = new ListaProductosSeleccionados();
			lista.setCantidad(element.getCantidad());
			lista.setIdCarro(element.getCarro_a().getIdcarro_a());
			lista.setIdproducto_a(element.getProducto_a().getIdproductoa());
			lista.setIdProductoSeleccionado(element.getIdproductoSeleccionado());
			lista.setNombreProducto(element.getProducto_a().getNombre_productoA());
			lista.setPrecio_a(element.getProducto_a().getPrecio_a());
			lista.setSubTotal(element.getSubTotal());
			listaProductos.add(lista);
			
		}
		
		}else listaProductos=null;
		
		//obtenemos la lista de productos para enviar a la vista
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<ListaProductos_A> entity = new HttpEntity<ListaProductos_A>(headers);
		
		// enviamos el request como GET
		
		ResponseEntity<ListaProductos_A> result=null;
		try {
			//realizamos consulta a servidor para que nos envie todos los clientes
		 result = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity, ListaProductos_A.class);
		
			
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		List<Producto_A> lista = result.getBody().getDataProducto();		
		//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		
		
		ModelAndView mav= new ModelAndView("carro_a/verDetallesCarro");
		mav.addObject("idCarro", idCarro);
		mav.addObject("productos", lista);
		mav.addObject("productosSeleccionados",listaProductos);
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value="/borrarCarro", method = RequestMethod.GET)
	public ModelAndView borrarCarro( @RequestParam(value="idCarro")String  idCarro) throws Exception{
		
		
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Carro_A> entity = new HttpEntity<Carro_A>(headers);


		try {
			
			// result=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a/{id}",
							HttpMethod.DELETE, entity, Carro_A.class,idCarro);
					
	
			
					
			} catch (Exception e) {
					logger.error(e);
			} 
		
		
		
		
		//carro_a=carro_AService.findByCarro_AIdCarro_a(idCarro);
		//carro_AService.delete(carro_a);

		ModelAndView mav= new ModelAndView("redirect: verTodosLosPedidos");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value="/cambioEstadoCarroPagado", method = RequestMethod.GET)
	public ModelAndView cambioEstadoCarroPagado( @RequestParam(value="idCarro")String  idCarro) throws Exception{
		//BUSCAMOS EL CARRO
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
		acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers4 = new HttpHeaders();
		headers4.setAccept(acceptableMediaTypes4);
		HttpEntity<Carro_A> entity4 = new HttpEntity<Carro_A>(headers4);

		//enviamos el resquest como GET
		ResponseEntity<Carro_A> carroDevuelto=null;
		try {
			 carroDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a/{id}",
							HttpMethod.GET, entity4, Carro_A.class,idCarro);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		
		
		
		Carro_A carro=carroDevuelto.getBody();		
		//carro_a=carro_AService.findByCarro_AIdCarro_a(idCarro);
		if(carro.getPagado())
		  carro.setPagado(false);
		else carro.setPagado(true);
		//actualizamos el carro
		
		
		// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Carro_A> entity = new HttpEntity<Carro_A>(carro,headers);

				//enviamos el resquest como PUT
				
				try {
				 
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
									HttpMethod.PUT, entity, Carro_A.class);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
		//carro_AService.update(carro_a);


		ModelAndView mav= new ModelAndView("redirect: verTodosLosPedidos");
	

		return mav;
	}
	@RequestMapping(value="/cambioEstadoCarroEnviado", method = RequestMethod.GET)
	public ModelAndView cambioEstadoCarroEnviado( @RequestParam(value="idCarro")String  idCarro) throws Exception{
		//BUSCAMOS EL CARRO
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
		acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers4 = new HttpHeaders();
		headers4.setAccept(acceptableMediaTypes4);
		HttpEntity<Carro_A> entity4 = new HttpEntity<Carro_A>(headers4);

		//enviamos el resquest como GET
		ResponseEntity<Carro_A> carroDevuelto=null;
		try {
			 carroDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a/{id}",
							HttpMethod.GET, entity4, Carro_A.class,idCarro);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		Carro_A carro=carroDevuelto.getBody();	
		//carro_a=carro_AService.findByCarro_AIdCarro_a(idCarro);
		
		
		
		if(carro.getEnviado())
		  carro.setEnviado(false);
		else{ carro.setEnviado(true);
		String content="apreciado usuario le informamos que el pago de su pedido numero "+idCarro+" ha sido enviado, en breve recibirá información de la agencia de transportes";
		String subject="pedido: "+idCarro;		
		mail.sendMail( carro.getCliente_a().getLogin_usuario_a(), content, carro.getCliente_a().getEmail_a(), subject);
		
		}
		
		//actualizamos el carro
		
		
		// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
						
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Carro_A> entity = new HttpEntity<Carro_A>(carro,headers);

				//enviamos el resquest como PUT
						
				try {
					
						restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a",
											HttpMethod.PUT, entity, Carro_A.class);
					
							
									
							} catch (Exception e) {
									logger.error(e);
						}		
		
		
		
		
		
		//carro_AService.update(carro_a);


		ModelAndView mav= new ModelAndView("redirect: verTodosLosPedidos");
	

		return mav;
	}
	@RequestMapping(value="/pagarCarro", method = RequestMethod.GET)
	public ModelAndView pagarCarro( @RequestParam(value="idCarro")String  idCarro,@RequestParam(value="total")String  total) throws Exception{
		
		//BUSCAMOS EL CARRO
				// ----Preparamos acceptable media type----
				List<MediaType> acceptableMediaTypes4 = new ArrayList<MediaType>();
				acceptableMediaTypes4.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers4 = new HttpHeaders();
				headers4.setAccept(acceptableMediaTypes4);
				HttpEntity<Carro_A> entity4 = new HttpEntity<Carro_A>(headers4);

				//enviamos el resquest como GET
				ResponseEntity<Carro_A> carroDevuelto=null;
				try {
					 carroDevuelto = 
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/carro_a/{id}",
									HttpMethod.GET, entity4, Carro_A.class,idCarro);
			
					
							
					} catch (Exception e) {
							logger.error(e);
				}
				carro_a=carroDevuelto.getBody();
		
		//carro_a=carro_AService.findByCarro_AIdCarro_a(idCarro);



		ModelAndView mav= new ModelAndView("carro_a/datosTarjeta");
		mav.addObject("tarjetaCredito", tarjetaCredito);
		mav.addObject("Carro", carro_a);
		return mav;
	}
}

	

