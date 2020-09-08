package co.edu.usbcali.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

/*
 * El RequestMapping es un decorador, sirve para crear una ruta por
 * la cual unicamente se podra acceder a la clase
*/
@RequestMapping("/api/operaciones/")

public class OperacionesMatematicas {

	/*
	 * Mediante el uso de GetMapping se pueden entregar valores a 
	 * una funcion sin necesidad de un front, todo esta desde el back
	 */
	@GetMapping("sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer numeroUno,
						 @PathVariable("n2") Integer numeroDos) 
	{
		return new Resultado(numeroUno+numeroDos);
	}
	
	@GetMapping("restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer numeroUno,
			 			  @PathVariable("n2") Integer numeroDos)
	{
		return new Resultado(numeroUno-numeroDos);
	}
	
}
