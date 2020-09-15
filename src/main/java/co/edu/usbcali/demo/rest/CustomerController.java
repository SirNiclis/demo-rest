package co.edu.usbcali.demo.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/findById/{email}")
	public CustomerDTO findById(@PathVariable("email") String email) {
		
		try {
			Optional<Customer> customerOptional = customerRepository.findById(email);
			if (customerOptional.isPresent()==false) {
				return null;
			}
			
			Customer customer = customerOptional.get();
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setAddress(customer.getAddress());
			customerDTO.setEmail(customer.getEmail());
			customerDTO.setEnable(customer.getEnable());
			customerDTO.setName(customer.getName());
			customerDTO.setPhone(customer.getPhone());
			customerDTO.setToken(customer.getToken());
			
			return customerDTO;
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		
	}

}
