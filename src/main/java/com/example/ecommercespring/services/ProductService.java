package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.entity.Product;
import com.example.ecommercespring.mappers.ProductMapper;
import com.example.ecommercespring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("productService")
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProductById(Long id) throws IOException {
//        return productRepository.findById(id)
//                .map(ProductMapper::toDto)
//                .orElseThrow(()-> new IOException("Product not Found"));
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IOException("Product not found"));

        return ProductMapper.toDto(product);

    }



    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws IOException {
        Product saved=productRepository.save(ProductMapper.toEntity(productDTO));
        return ProductMapper.toDto(saved);
    }
}
