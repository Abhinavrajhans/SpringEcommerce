package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.ProductDTO;

import java.io.IOException;

public interface IProductService {
    ProductDTO getProductById(Long id) throws IOException;
    ProductDTO createProduct(ProductDTO productDTO) throws IOException;
}
