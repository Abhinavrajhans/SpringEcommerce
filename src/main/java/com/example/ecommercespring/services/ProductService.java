package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.entity.Category;
import com.example.ecommercespring.entity.Product;
import com.example.ecommercespring.mappers.ProductMapper;
import com.example.ecommercespring.repository.CategoryRepository;
import com.example.ecommercespring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("productService")
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->  new IOException("Category Not Found"));
        Product saved=productRepository.save(ProductMapper.toEntity(productDTO,category));
        return ProductMapper.toDto(saved);
    }
}
