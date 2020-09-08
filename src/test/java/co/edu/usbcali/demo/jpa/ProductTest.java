package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductTest {

	private final static String proId= "XIAOR9PM";
	
	private final static Logger log = LoggerFactory.getLogger(ProductTest.class);
	
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, proId);
		
		assertNull(product, "El producto con proId "+proId+" ya se encuentra registrado");
	
		product = new Product();
		product.setProId(proId);
		product.setName("Xiaomi Redmi Note 9 Pro Max");
		product.setPrice(905000);
		product.setDetail("Nuevo Xiaomi Redmi Note 9 Pro Max");
		product.setImage("https://www.mobilepriceall.com/wp-content/uploads/2020/03/Xiaomi-Redmi-Note-9-Pro-Interstellar-Black.jpg");
		product.setEnable("Y");
		
		entityManager.persist(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, proId);
		
		assertNotNull(product, "El producto con proId "+proId+" no existe");

		log.info(product.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, proId);
		
		assertNotNull(product, "El producto con proId "+proId+" no existe");

		product.setEnable("N");
		
		entityManager.merge(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		assertNotNull(entityManager, "El entityManager es nulo.");
		
		Product product = entityManager.find(Product.class, proId);
		
		assertNotNull(product, "El producto con proId "+proId+" no existe");

		entityManager.remove(product);
	}
}
