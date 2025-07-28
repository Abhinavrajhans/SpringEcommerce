package com.example.ecommercespring.controllers;


import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() throws IOException {
            List<CategoryDTO> result= this.categoryService.getAllCategories();
            return ResponseEntity.ok().body(result);
    }

}
