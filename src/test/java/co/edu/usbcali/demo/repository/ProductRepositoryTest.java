package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
public class ProductRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	private final static String proId= "XIAOR9PM";
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		
		log.info("save");
		
		Optional<Product> productOptional = productRepository.findById(proId);
		
		assertFalse(productOptional.isPresent(), "El producto ya existe");
		
		Product product = new Product();
		product.setProId(proId);
		product.setName("Xiaomi Redmi Note 9 Pro Max");
		product.setPrice(905000);
		product.setDetail("Nuevo Xiaomi Redmi Note 9 Pro Max");
		product.setImage("https://www.mobilepriceall.com/wp-content/uploads/2020/03/Xiaomi-Redmi-Note-9-Pro-Interstellar-Black.jpg");
		product.setEnable("Y");
		
		productRepository.save(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		log.info("findById");
		
		Optional<Product> productOptional = productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		log.info(productOptional.get().getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("update");
		
		Optional<Product> productOptional = productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product = productOptional.get();
		
		product.setEnable("N");
		
		productRepository.save(product);
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		log.info("delete");
		
		Optional<Product> productOptional = productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(), "El producto no existe");
		
		Product product = productOptional.get();
		
		productRepository.delete(product);
		
	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		log.info("findAll");
		productRepository.findAll().forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Price:"+product.getPrice());
		});
		
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		
		log.info("count");
		log.info("Count:"+productRepository.count());
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		
		List<Product> products = productRepository.findByEnableAndName("Y", "iPad Pro");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void findProductLikepa() {
		
		List<Product> products = productRepository.findProductLikepa();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void findProductByOrderByPriceAsc() {
		
		List<Product> products = productRepository.findByOrderByPriceAsc();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(10)
	void findByProIdContaining() {
		
		List<Product> products = productRepository.findByProIdContaining("PPL");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(11)
	void findByNameContainingIgnoreCase() {
		
		List<Product> products = productRepository.findByNameContainingIgnoreCase("x");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Email:"+product.getPrice());
		});
	}
}
