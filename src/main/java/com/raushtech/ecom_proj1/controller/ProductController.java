package com.raushtech.ecom_proj1.controller;


import com.raushtech.ecom_proj1.model.Product;
import com.raushtech.ecom_proj1.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@RestController
@CrossOrigin(origins = "*")
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


    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile){
        try {
            Product product1 = productService.addProduct(product, imageFile);
            return new  ResponseEntity<>(product1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product = productService.getProductById(id);

        if(product == null || product.getImageData() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(
                        product.getImageType() != null
                                ? MediaType.valueOf(product.getImageType())
                                : MediaType.IMAGE_JPEG
                )
                .body(product.getImageData());
    }



    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product
            ,@RequestPart MultipartFile imageFile){

        Product product1=null;

        try{
            product1=productService.updateProduct(id, product, imageFile);
        }catch (Exception e){
            return new  ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }


         if (product1 != null)
             return new ResponseEntity<>("Updated",HttpStatus.OK);
         else
             return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);


    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){

        System.out.print("searching with " +keyword);
        List<Product> products = productService.searchProducts(keyword);
        return  new ResponseEntity<>(products,HttpStatus.OK);
    }








}
