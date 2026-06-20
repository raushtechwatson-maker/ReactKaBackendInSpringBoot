package com.raushtech.ecom_proj1.controller;


import com.raushtech.ecom_proj1.model.Product;
import com.raushtech.ecom_proj1.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
  private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String greet(){
        return "Hello World!";
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }



}
