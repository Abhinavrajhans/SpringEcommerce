package com.example.ecommercespring.gateway;

import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.dto.FakeStoreCategoryResponseDTO;
import com.example.ecommercespring.gateway.api.OkHttpRequest;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("fakeStoreCategoryGatewayOkHttp")
public class FakeStoreCategoryGatewayOkHttp implements ICategoryGateway{
    private final OkHttpRequest okHttpRequest;
    private final Gson gson;

    public FakeStoreCategoryGatewayOkHttp(OkHttpRequest okHttpRequest, Gson gson) {
        this.okHttpRequest = okHttpRequest;
        this.gson = gson;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {

        String response = this.okHttpRequest.run("https://fakestoreapi.in/api/products/category");
        FakeStoreCategoryResponseDTO fakeStoreCategoryResponseDTO = this.gson.fromJson(response,FakeStoreCategoryResponseDTO.class);
        if (response==null){
            throw new IOException("Failed to fetch all categories From FakeStoreAPI");
        }
        return fakeStoreCategoryResponseDTO.getCategories().stream()
                .map(category->CategoryDTO.builder()
                        .name(category)
                        .build())
                .toList();

    }
}
