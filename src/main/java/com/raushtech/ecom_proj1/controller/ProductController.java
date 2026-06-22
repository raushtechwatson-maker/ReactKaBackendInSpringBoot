package com.raushtech.ecom_proj1.controller;


import com.raushtech.ecom_proj1.model.Product;
import com.raushtech.ecom_proj1.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
  private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String greet(){
        return "WELCOME HOME";
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return  new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }




    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
       Product product = productService.getProductById(id);

       if(product != null)
           return  new ResponseEntity<>(product, HttpStatus.OK);
        else
           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
