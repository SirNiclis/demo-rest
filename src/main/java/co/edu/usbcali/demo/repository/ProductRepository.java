package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	//Busca productos segun los parametros de "Enable" y "Name" que sean ingresados
	public List<Product> findByEnableAndName(String enable, String name);
	
	//Busca productos que tengan dentro de su nombre "Pa" en alguna parte
	@Query("SELECT prod FROM Product prod WHERE prod.name LIKE '%Pa%'")
	public List<Product> findProductLikepa();
	
	//Busca los productos y los ordena de manera ascendente segun su precio
	public List<Product> findByOrderByPriceAsc();
	
	//Busca productos que en pro_id contengan los valores ingresados
	public List<Product> findByProIdContaining(String proId);
	
	//Busca productos que en el nombre contengan el valor ingresado ignorando las mayusculas que tenga
	public List<Product> findByNameContainingIgnoreCase(String name);
}
