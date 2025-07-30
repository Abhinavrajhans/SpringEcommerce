package com.example.ecommercespring.controllers;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.dto.ProductWithCategoryDTO;
import com.example.ecommercespring.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductWithCategoryDTO> getProductWithCategory(@PathVariable long id) throws IOException {
        ProductWithCategoryDTO dto=this.productService.getProductWithCategory(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findProductsUsingFilters(
            @RequestParam(required = false) double minPrice,
            @RequestParam(required = false) String brand
    ) throws IOException{
        if(brand !=null && !brand.isEmpty()){
            List<ProductDTO> products = this.productService.searchByBrandAndMinPrice(brand, minPrice);
            return ResponseEntity.ok().body(products);
        }
        else {
            List<ProductDTO> listOfExpensiveProducts = this.productService.findExpensiveProducts(minPrice);
            return ResponseEntity.ok().body(listOfExpensiveProducts);
        }
    }

}
