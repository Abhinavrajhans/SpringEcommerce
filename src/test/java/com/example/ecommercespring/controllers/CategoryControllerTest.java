package com.example.ecommercespring.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.exception.GlobalExceptionHandler;
import com.example.ecommercespring.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.ws.rs.core.MediaType;



@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                  .setControllerAdvice(new GlobalExceptionHandler())
                  .build();
    }

    @Test
    @DisplayName("GET /api/categories should return all catogies")
    void getAllCategories_shouldReturnAllCategories() throws Exception {
        //Arrange
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categoryDTOList.add(CategoryDTO.builder().name("Electronics").id(1L).build());
        categoryDTOList.add(CategoryDTO.builder().name("Books").id(2L).build());
        categoryDTOList.add(CategoryDTO.builder().name("Clothing").id(3L).build());
        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);
        //Act
        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Books"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Clothing"));

        //Assert
        verify(categoryService,times(1)).getAllCategories();

    }

}
