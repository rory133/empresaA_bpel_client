package org.proyecto.empresaA_bpel_client.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.proyecto.empresaA_bpel_client.model.Carro_A;
import org.proyecto.empresaA_bpel_client.model.TarjetaCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/tarjeta")
public class tarjetaController {
	
	@Autowired
	private Carro_A carro_a;
	

	

	
	private RestTemplate restTemplate = new RestTemplate();
	
	protected static Logger logger = Logger.getLogger("*en tarjetaController*");
	

	
	@RequestMapping(value="/validarTarjeta", method = RequestMethod.POST)
	public ModelAndView validarTarjeta(@Valid @ModelAttribute("tarjetaCredito")TarjetaCredito tarjetaCredito,  BindingResult  result, @RequestParam(value="idCarro")String  idCarro, HttpSession session)throws Exception{

		//carro_b=carro_BService.findByCarro_BIdCarro_b(idCarro);
		Carro_A carro= new Carro_A();
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
	
			
			 carro=carroDevuelto.getBody();		
			} catch (Exception e) {
					logger.error(e);
		}
		
		carro_a=carroDevuelto.getBody();
		
		if(result.hasErrors()) {
			logger.info("validarTarjeta:---tiene errores----"+result.toString());
				
				ModelAndView mav= new ModelAndView("carro_a/datosTarjeta");
				mav.addObject("tarjetaCredito", tarjetaCredito);
				mav.addAllObjects(result.getModel());
				mav.addObject("Carro", carro_a);
				return mav;
			}
		// ----Preparamos acceptable media type----
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		
		// preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes4);
		HttpEntity<Carro_A> entity = new HttpEntity<Carro_A>(carro,headers);

		//enviamos el resquest como PUT
		
		try {
			
					restTemplate.exchange("http://localhost:8080/empresaA_bpel_server/carro/pagarCarro/",
							HttpMethod.PUT, entity, Carro_A.class);
	
			
					
			} catch (Exception e) {
					logger.error(e);
		}
		
		
		session.removeAttribute("carro_a");
		ModelAndView mav= new ModelAndView("redirect: ../../../carro/verTodosLosPedidos");
		return mav;
		
  }
}