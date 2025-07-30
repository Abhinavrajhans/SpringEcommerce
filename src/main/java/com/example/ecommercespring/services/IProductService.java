package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.dto.ProductWithCategoryDTO;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductDTO getProductById(Long id) throws IOException;
    ProductDTO createProduct(ProductDTO productDTO) throws IOException;
    ProductWithCategoryDTO getProductWithCategory(Long id) throws IOException;
    List<ProductDTO> findExpensiveProducts(double price) throws IOException;
    List<ProductDTO> searchByBrandAndMinPrice(String brand,double minPrice) throws IOException;
}
