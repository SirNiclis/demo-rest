package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest//Indica que es una prueba de unidad y se va a unir con spring
@Rollback(false)//Se le esta diciendo que no haga rollback por ser test
@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {

	private final static String email = "nicolas_mayorga@yahoo.es";
	
	//Para logger siempre usar es Logger org.slf4j
	private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);
	
	//El @Autowired genera la inyeccion de dependencias
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		//Lease como "Siga si no es nulo"
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Customer customer = entityManager.find(Customer.class, email);
		
		//Lease como "Siga si es nulo"
		assertNull(customer, "El cliente con email "+email+" ya existe.");
	
		customer = new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Nicolas Mayorga");
		customer.setPhone("301 755 3704");
		customer.setToken("NSSKS9WJEN31N");
		
		entityManager.persist(customer);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Customer customer = entityManager.find(Customer.class, email);
		
		assertNotNull(customer, "El cliente con email "+email+" no existe.");

		//Utilizar SIEMPRE en vez de el SOUT
		log.info(customer.getName());;
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Customer customer = entityManager.find(Customer.class, email);
		
		assertNotNull(customer, "El cliente con email "+email+" no existe.");

		customer.setEnable("N");
		
		entityManager.merge(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Customer customer = entityManager.find(Customer.class, email);
		
		assertNotNull(customer, "El cliente con email "+email+" no existe.");

		entityManager.remove(customer);
	}

}
