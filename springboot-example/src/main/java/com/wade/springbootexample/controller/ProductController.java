package com.wade.springbootexample.controller;

import com.wade.springbootexample.dto.ProductRequest;
import com.wade.springbootexample.model.Product;
import com.wade.springbootexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = productService.getProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        //check product exists
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
