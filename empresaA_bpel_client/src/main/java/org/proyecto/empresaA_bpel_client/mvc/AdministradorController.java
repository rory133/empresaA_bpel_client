package org.proyecto.empresaA_bpel_client.mvc;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.proyecto.empresaA_bpel_client.model.Administrador_A;
import org.proyecto.empresaA_bpel_client.model.ListaAdministradores_A;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/administradores")
public class AdministradorController {
	

	
	
	@Autowired
	ServletContext context;
	
	
	private RestTemplate restTemplate = new RestTemplate();

	
	protected static Logger logger = Logger.getLogger("*en Administrador_A_AController en cliente @@@@@@*");
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, params="new")
	public ModelAndView addContact() {
		logger.info("metodo get --new-- ");
		return new ModelAndView("administrador_a/edit", "administrador_a",new Administrador_A()  );

	  }
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addAdministrador_A_form(@Valid @ModelAttribute("administrador_a")Administrador_A administrador_a, BindingResult  result)throws Exception {

		
		logger.info("inicio de addAdministrador_A_form n cliente @@@@@@ ");
		if(result.hasErrors()) {
		logger.info("addAdministrador_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
		 return new ModelAndView("administrador_a/edit", "administrador_a",new Administrador_A()).addAllObjects(result.getModel());

		}
	
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Administrador_A> entity = new HttpEntity<Administrador_A>(administrador_a,headers);

		//enviamos el resquest como POST
		
		try {
			//ResponseEntity<Cliente_A> clienteDevuelto = 
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores/administrador",
							HttpMethod.POST, entity, Administrador_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}

		return new ModelAndView("redirect:listado");
}
	
	
	
	

	
	
	@RequestMapping(value="/listado",method=RequestMethod.GET)
	public ModelAndView listadoAdministradores_A(){
		logger.info("en listadoAdministradores_A en Cliente @@@@@@*");
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		//acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Administrador_A> entity = new HttpEntity<Administrador_A>(headers);
		
		// enviamos el request como GET
		ModelAndView mav=new ModelAndView("administrador_a/listaAdministradores");
		try {
			//realizamos consulta a servidor para que nos envie todos los administradores
			ResponseEntity<ListaAdministradores_A> result = restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores", HttpMethod.GET, entity, ListaAdministradores_A.class);
				
			mav.addObject("administradores", result.getBody().getDataAdministrador());
			
					
			} catch (Exception e) {
					logger.error(e);
			}

		return mav;
	}
	
	

	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editAdministrador_A_form(String id){


	
		Administrador_A administrador_a= new Administrador_A();
		
		//obtenemos el administrador correspondiente a ese id
		// Preparamos acceptable media type
						List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
						acceptableMediaTypes.add(MediaType.APPLICATION_XML);
						
						// preparamos el header
						HttpHeaders headers = new HttpHeaders();
						headers.setAccept(acceptableMediaTypes);
						HttpEntity<Administrador_A> entity = new HttpEntity<Administrador_A>(headers);

						//enviamos el resquest como POST
						ResponseEntity<Administrador_A> result=null;
						
						try {
							
							 result=
							restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores/administrador/{id}",
											HttpMethod.GET, entity, Administrador_A.class,id);
					
							
									
							} catch (Exception e) {
									logger.error(e);
							} 

		
						administrador_a= result.getBody();
		
		
		
		
		
		
		
		
		//administrador_a= administrador_AServiceImpl.findByAdministrador_AIdAdministrador_a(id);
					
		logger.info("Administrador  editAdministrador_A_form  en Cliente @@@@@@*");
		
		
		//List<Producto_A> lista =productos_AServiceImpl.getProductos_A();
		//return new ModelAndView("producto_a/listaProductos","productos", lista);
		return new ModelAndView("administrador_a/modificar", "administrador_a",administrador_a);
	
}

	
	
	@RequestMapping(value="/modificarAdministrador_A", method = RequestMethod.POST)
	public ModelAndView modAdministrador_A_form(@Valid @ModelAttribute("administrador_a")Administrador_A administrador_a, BindingResult  result) throws Exception{
		
		if(result.hasErrors()) {
		logger.info("modAdministrador_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
		 return new ModelAndView("administrador_a/edit", "administrador_a",new Administrador_A()).addAllObjects(result.getModel());

		}
		
		logger.info("inicio de modCliente_A_form en cliente@@@@@@@");
		//comprobamos que no exista este login
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<Administrador_A> entity = new HttpEntity<Administrador_A>(headers);

		//enviamos el resquest como GET
		ResponseEntity<Administrador_A> resultado=null;
		
		try {
			
			 resultado=
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores/administradorLogin/{login}",
							HttpMethod.GET, entity, Administrador_A.class,administrador_a.getLogin_usuario_a());
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}
			
		//buscamos un usuario por el login enviado
		Usuario_A usuarioBuscado=resultado.getBody();
	
		
		
		Integer idusuarioBuscado=null;
		if (null!=usuarioBuscado){
		idusuarioBuscado=usuarioBuscado.getIdusuarios_a();
		}
		Integer idadministrador_a=administrador_a.getIdusuarios_a();
		
	
		//if (null !=usuarioBuscado){
			
		if ((null !=usuarioBuscado)&& (idusuarioBuscado==idadministrador_a)){
			result.addError(new ObjectError("loginInvalido", "Este usuario ya existe"));
			logger.info("null !=usuarioBuscado"+(null !=usuarioBuscado));

			
		}
		
		
		logger.info("inicio de modAdministrador_A_form");
		if(result.hasErrors()) {
		logger.info("modAdministrador_A_form ------tiene errores----"+result.toString());
		logger.info("errores: "+result.toString());
		 return new ModelAndView("administrador_a/edit", "administrador_a",new Administrador_A()).addAllObjects(result.getModel());

		}
	

	
		
		// Preparamos acceptable media type
		List<MediaType> acceptableMediaTypes2 = new ArrayList<MediaType>();
		acceptableMediaTypes2.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(acceptableMediaTypes2);
		HttpEntity<Administrador_A> entity2 = new HttpEntity<Administrador_A>(administrador_a, headers2);

		//enviamos el resquest como PUT

		
		try {
			
		
			restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores/administrador/{id}",
							HttpMethod.PUT, entity2, Administrador_A.class,administrador_a.getIdusuarios_a());
	
			
					
			} catch (Exception e) {
					logger.error(e);
			}
		
		
		
		
		
		

		return new ModelAndView("redirect:listado");
		
	
	
	}
	
	
	

	
	
	@RequestMapping(value="/borrar",method=RequestMethod.GET)
	public ModelAndView delAdministrador_A_form(String id){

		logger.info("en borrarADMINISTRADOR con ide con id @@@@@@ : "+id);		
		
		//obtenemos el cliente correspondiente a ese id		
		
		
		
		// Preparamos acceptable media type
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_XML);
				
				// preparamos el header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(acceptableMediaTypes);
				HttpEntity<Administrador_A> entity = new HttpEntity<Administrador_A>(headers);

				//enviamos el resquest como POST
				
				//ResponseEntity<Cliente_A> result=null;
				
				try {
					
					// result=
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/administradores/administrador/{id}",
									HttpMethod.DELETE, entity, Administrador_A.class,id);
							
			
					
							
					} catch (Exception e) {
							logger.error(e);
					} 
			
		return new ModelAndView("redirect:listado");

}
	

	

}