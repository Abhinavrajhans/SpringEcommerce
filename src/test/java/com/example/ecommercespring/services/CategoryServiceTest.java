package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.entity.Category;
import com.example.ecommercespring.mappers.CategoryMapper;
import com.example.ecommercespring.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // this annotation is used to enable Mokito for the test class with Junit.
public class CategoryServiceTest {

    @Mock   // this will mock the actual class
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;
    private Category category1;
    private Category category2;
    private Category category3;

    @BeforeEach
    public void setUp() throws IOException {
        categoryDTO = CategoryDTO.builder().name("Electronics").build();
        category1= Category.builder().name("Electronics").build();
        category2= Category.builder().name("Books").build();
        category3= Category.builder().name("Clothing").build();
        category1.setId(1L);
        category2.setId(2L);
        category3.setId(3L);
    }

    @Test
    @DisplayName("should return all the categories successfully")
    void getAllCategories_shouldReturnAllCategories() throws IOException {
        // Arrange
                // here we have to do the mocking .
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        //now we say that whenever Repository.findAll() is calld then return categories
        when(categoryRepository.findAll()).thenReturn(categories); //mocked the repository to return the category

        // Act
            // now is the time to act means actually call the service
            // now CategoryServiceTest actally wants to calls the CategoryService so CategoryServiceTest Depends on the CategoryService
            List<CategoryDTO> result = this.categoryService.getAllCategories(); // service is actually relly called.
            //when we will try to call the getAllCategories() then when .thenReturn will get called which we have written above.
        //Assert
        //After the mocked implementation is called which is when .thenReturn then we can do Assert
        //Assertion is like verify or testing like what answer u wanted and what answer we are getting
        assertEquals(result.size(),3 );
        verify(categoryRepository,times(1)).findAll(); // this is to check that the findAll method is only called once.

    }

    //let's make a test when we have no category to return that no category is present in the db.
    @Test
    @DisplayName("should return empty list when no categories exists")
    void getAllCategories_shouldReturnEmptyListWhenNoCategoryExists() throws IOException {
        //Arrange
            when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        // Act
            List<CategoryDTO> result = categoryService.getAllCategories();

        //Assert
        assertEquals(result.size(),0);
        verify(categoryRepository,times(1)).findAll();

    }

    @Test
    @DisplayName("Should create a new category succesfully")
    void createCategory_shouldCreateNewCategory() throws IOException {
        //Arrange
        when(categoryRepository.save(any(Category.class))).thenReturn(category1);
        //Act
        CategoryDTO newCategoryDTO = categoryService.createCategory(categoryDTO);
        //Assert
        assertEquals(newCategoryDTO.getName(),category1.getName());
        verify(categoryRepository,times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("Should return catorgy by name")
    void getCategory_shouldReturnCategoryByName() throws IOException {
        //Arrange
        String name = "Electronics";


        when(categoryRepository.findByName(name)).thenReturn(Optional.of(category1));
        //Act
        CategoryDTO result = categoryService.getByName(name);
        //Assert
        assertEquals(result.getName(),category1.getName());
        verify(categoryRepository,times(1)).findByName(name);


    }

}
