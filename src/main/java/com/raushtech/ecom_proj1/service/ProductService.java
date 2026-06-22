package com.raushtech.ecom_proj1.service;
import com.raushtech.ecom_proj1.model.Product;
import com.raushtech.ecom_proj1.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo  productRepo;

    public ProductService(ProductRepo  productRepo){
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
       // return productRepo.findById(id).get();

    }
}
