package com.example.ecommercespring.controllers;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final IProductService productService;

    ProductController(@Qualifier("productService") IProductService concreteClass) {
        this.productService = concreteClass;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws IOException {
        ProductDTO result= this.productService.getProductById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws IOException {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }
}
