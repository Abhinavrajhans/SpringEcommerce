package com.example.ecommercespring.gateway;

import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.dto.FakeStoreCategoryResponseDTO;
import com.example.ecommercespring.gateway.api.IFakeStoreCategoryApi;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class FakeStoreCategoryGateway implements ICategoryGateway {

    private final IFakeStoreCategoryApi fakeStoreCategoryApi;

    public FakeStoreCategoryGateway(IFakeStoreCategoryApi fakeStoreCategoryApi) {
        this.fakeStoreCategoryApi = fakeStoreCategoryApi;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        FakeStoreCategoryResponseDTO response= this.fakeStoreCategoryApi.getAllFakeCategory().execute().body();
        if (response==null){
            throw new IOException("Failed to fetch all categories From FakeStoreAPI");
        }
        return response.getCategories().stream()
                .map(category->CategoryDTO.builder()
                        .name(category)
                        .build())
                .toList();
    }
}
