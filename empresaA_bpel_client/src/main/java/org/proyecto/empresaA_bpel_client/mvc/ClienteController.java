package org.proyecto.empresaA_bpel_client.mvc;


import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import javax.servlet.ServletContext;

import javax.validation.Valid;
import org.springframework.web.client.RestTemplate;
import org.apache.log4j.Logger;
import org.proyecto.empresaA_bpel_client.model.Cliente_A;
import org.proyecto.empresaA_bpel_client.model.ListaClientes_A;
import org.proyecto.empresaA_bpel_client.model.ListaProductos_A;
import org.proyecto.empresaA_bpel_client.model.Usuario_A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/clientes")
public class ClienteController {
	

	

	
	
	@Autowired
	ServletContext context;
	

	
	private RestTemplate restTemplate = new RestTemplate();

	
	protected static Logger logger = Logger.getLogger("*en Cliente_A_AController*cliente@@@@@@@");
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, params="new")
	public ModelAndView addContact() {
		logger.info("metodo get --new  addContact--cliente@@@@@@@ ");
		return new ModelAndView("cliente_a/edit", "cliente_a",new Cliente_A()  );

	  }
	
	
	//enviamos un nuevo cliente al servidor desde aplicacion cliente	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addCliente_A_form(@Valid @ModelAttribute("cliente_a")Cliente_A cliente_a, BindingResult  result)throws Exception {

		
		logger.info("inicio de addCliente_A_form en aplicacion cliente@@@@@@@");
		
		if(result.hasErrors()) {
		logger.info("addCliente_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
	
		 return new ModelAndView("cliente_a/edit", "cliente_a",new Cliente_A()).addAllObjects(result.getModel());

		}

		
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
							HttpMethod.GET, entity2, Cliente_A.class,cliente_a.getLogin_usuario_a());
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}
			
		//buscamos un usuario por el login enviado
		Usuario_A usuarioBuscado=resultado.getBody();
		

		
		
		
		
		
		
		if (null !=usuarioBuscado)
		//if (null !=cliente_AServiceImpl.findByCliente_A_login_usuario_a(cliente_a.getLogin_usuario_a()))
		result.addError(new ObjectError("loginInvalido", "Este usuario ya existe"));
		
		
		
		
		//	result.addError(new ObjectError(result.getObjectName(), "este usuario ya existe!"));
		if(result.hasErrors()) {
		logger.info("addCliente_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
	
		 return new ModelAndView("cliente_a/edit", "cliente_a",new Cliente_A()).addAllObjects(result.getModel());

		}
	
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(cliente_a,headers);

		//enviamos el resquest como POST
		
		try {
			//ResponseEntity<Cliente_A> clienteDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/cliente",
							HttpMethod.POST, entity, Cliente_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		
		
		//devolvemos pagina inicial
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes3 = new ArrayList<MediaType>();
				acceptableMediaTypes3.add(MediaType.APPLICATION_XML);
				//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
				
				// preparamos el header
				HttpHeaders headers3 = new HttpHeaders();
				headers3.setAccept(acceptableMediaTypes3);
				HttpEntity<ListaProductos_A> entity3 = new HttpEntity<ListaProductos_A>(headers3);
				
				// enviamos el request como GET
				ModelAndView mav=new ModelAndView("producto_a/listaProductos");
				try {
					//realizamos consulta a servidor para que nos envie todos los clientes
					ResponseEntity<ListaProductos_A> result3 = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/productos", HttpMethod.GET, entity3, ListaProductos_A.class);
				
					mav.addObject("productos", result3.getBody().getDataProducto());
					
							
					} catch (Exception e) {
							logger.error(e);
					}
		return mav;
		
/*		
		List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		return new ModelAndView("producto_a/listaProductos","productos", lista);*/
		
}
	
	
	
	
	

	
	//---listamos todos los clientes----
	@RequestMapping(value="/admin/listado",method=RequestMethod.GET)
	public ModelAndView listadoClientes_A(){
		logger.info("en listadoClientes_A2 en client en Cliente @@@@@@*");
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(headers);
		
		// enviamos el request como GET
		ModelAndView mav=new ModelAndView("cliente_a/listaClientes");
		try {
			//realizamos consulta a servidor para que nos envie todos los clientes
			ResponseEntity<ListaClientes_A> result = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes", HttpMethod.GET, entity, ListaClientes_A.class);
			logger.info("en listadoClien rult tamaño result: "+result.getBody().getDataCliente().size());	
			mav.addObject("clientes", result.getBody().getDataCliente());
			
					
			} catch (Exception e) {
					logger.error(e);
			}

		return mav;
	}
	
	
	
	@RequestMapping(value="/cliente/edit",method=RequestMethod.GET)
	public ModelAndView editCliente_A_form(String id){


		logger.info("id cliente pasado a edit-modificar en Cliente @@@@@@ : "+id);
		
		
		
		//obtenemos el cliente correspondiente a ese id
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(headers);

				//enviamos el resquest como POST
				ResponseEntity<Cliente_A> result=null;
				
				try {
					
					 result=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/cliente/{id}",
									HttpMethod.GET, entity, Cliente_A.class,id);
			
					
							
					} catch (Exception e) {
							logger.error(e);
					} 

		
	//	return new ModelAndView("cliente_a/modificar", "cliente_a",result.getBody().getIdusuarios_a());
				return new ModelAndView("cliente_a/modificar", "cliente_a",result.getBody());
	
}
	@RequestMapping(value="/cliente/modificarCliente_A", method = RequestMethod.POST)
	public ModelAndView modCliente_A_form(@Valid @ModelAttribute("cliente_a")Cliente_A cliente_a, BindingResult  result) throws Exception{

		if(result.hasErrors()) {
		logger.info("modCliente_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
		 return new ModelAndView("cliente_a/edit", "cliente_a",new Cliente_A()).addAllObjects(result.getModel());

		}
		
		logger.info("inicio de modCliente_A_form en cliente@@@@@@@");
		//comprobamos que no exista este login
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(headers);

		//enviamos el resquest como GET
		ResponseEntity<Cliente_A> resultado=null;
		
		try {
			
			 resultado=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/clienteLogin/{login}",
							HttpMethod.GET, entity, Cliente_A.class,cliente_a.getLogin_usuario_a());
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}
			
		//buscamos un usuario por el login enviado
		Usuario_A usuarioBuscado=resultado.getBody();
		
		Integer idusuarioBuscado=null;
		
		if (null!=usuarioBuscado){
			//ya existia ese login asignamos si id a idusuarioBuscado
		idusuarioBuscado=usuarioBuscado.getIdusuarios_a();
		}
		//asignamos el id del usuario pasado a idcliente
		Integer idcliente_a=cliente_a.getIdusuarios_a();
		//si los dos ids coinciden y no son nulos ha usado el login de otro usuario, añadimos un error con el aviso
		if ((null !=usuarioBuscado)&& (idusuarioBuscado==idcliente_a)){
			result.addError(new ObjectError("loginInvalido", "Este usuario ya existe"));
			logger.info("null !=usuarioBuscado"+(null !=usuarioBuscado));
			logger.info("((usuarioBuscado.getIdusuarios_a())!=(cliente_a.getIdusuarios_a()))"+((usuarioBuscado.getIdusuarios_a())!=(cliente_a.getIdusuarios_a())));
			
		}
		//como hemos añadido el error se devuelve a la vista para volver a editarlo
		if(result.hasErrors()) {
		logger.info("modCliente_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
		 return new ModelAndView("cliente_a/edit", "cliente_a",new Cliente_A()).addAllObjects(result.getModel());

		}
		//si llegamos aqui podemos actualizar el cliente.
		logger.info("modCliente_A_form ");
		cliente_a.setFecha_alta_a(new Date());
		cliente_a.setAUTHORITY("ROLE_CLIENTE");
		cliente_a.setENABLED(true);
		
		
		
		
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
				acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers2 = new HttpHeaders();
				headers2.setAccept(acceptableMediaTypes2);
				HttpEntity<Cliente_A> entity2 = new HttpEntity<Cliente_A>(cliente_a, headers2);

				//enviamos el resquest como PUT
		
				
				try {
					
				
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/cliente/{id}",
									HttpMethod.PUT, entity2, Cliente_A.class,cliente_a.getIdusuarios_a());
			
					
							
					} catch (Exception e) {
							logger.error(e);
					}
					
		
		//volvemos a la vista principal
		return new ModelAndView("redirect:../../productos/listado");
	
	
	}
	
	
	@RequestMapping(value="/cliente/modificarMiCuenta_A/", method = RequestMethod.GET)
	public ModelAndView modMiCuenta_A_form(@RequestParam(value="login")String  login) throws Exception{
		
		logger.info(" en cliente /cliente/modificarMiCuenta_A/@@@@@@ con login: " + login);
		
		//obtenemos el idusuario del login pasado
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(headers);

		//enviamos el resquest como GET
		ResponseEntity<Cliente_A> result=null;
		
		try {
			
			 result=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/clienteLogin/{login}",
							HttpMethod.GET, entity,Cliente_A.class, login);
			 logger.info(" result 	restTemplate.exchange(http://localhost:8080/empresaA_bpel_server/clientes/cliente/{login} result.getBody().getLogin_usuario_a() :"+result.getBody().getLogin_usuario_a());
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		String valorId=String.valueOf( result.getBody().getIdusuarios_a());
		
		return new ModelAndView("redirect:../edit?id="+valorId);
	
	}
	
	
	@RequestMapping(value="/admin/borrar",method=RequestMethod.GET)
	public ModelAndView delCliente_A_form(String id){
		
		logger.info("en borrar CLIENTE con ide con id @@@@@@ : "+id);		
		
		
		
		
		
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Cliente_A> entity = new HttpEntity<Cliente_A>(headers);

				//enviamos el resquest como POST
				
				//ResponseEntity<Cliente_A> result=null;
				
				try {
					
					// result=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/clientes/cliente/{id}",
									HttpMethod.DELETE, entity, Cliente_A.class,id);
							
			
					
							
					} catch (Exception e) {
							logger.error(e);
					} 
				logger.info("Despues de borrar cliente borrar con ide con id @@@@@@ : ");	
		return new ModelAndView("redirect:listado");

}


}
