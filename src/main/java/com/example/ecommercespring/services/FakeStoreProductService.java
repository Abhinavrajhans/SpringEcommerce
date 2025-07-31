package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.ProductDTO;
import com.example.ecommercespring.dto.ProductWithCategoryDTO;
import com.example.ecommercespring.gateway.IProductGateway;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final IProductGateway productGateway;
    FakeStoreProductService(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public ProductDTO getProductById(Long id) throws IOException {
        return  this.productGateway.getProductById(id);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws IOException {
        return null;
    }

    @Override
    public ProductWithCategoryDTO getProductWithCategory(Long id) throws IOException {
        return null;
    }

    @Override
    public List<ProductDTO> findExpensiveProducts(double price) throws IOException {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchByBrandAndMinPrice(String brand, double minPrice) throws IOException {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchProductWithKeywordInNameAndDescription(String keyword) throws IOException {
        return List.of();
    }

    @Override
    public List<ProductDTO> getAllProductsOfACategory(Long categoryId) throws IOException {
        return List.of();
    }
}
