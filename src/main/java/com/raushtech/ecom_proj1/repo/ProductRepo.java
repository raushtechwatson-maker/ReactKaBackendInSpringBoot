package com.raushtech.ecom_proj1.repo;

import com.raushtech.ecom_proj1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {


}
