package com.example.ecommercespring.services;

import com.example.ecommercespring.dto.CategoryDTO;
import com.example.ecommercespring.entity.Category;
import com.example.ecommercespring.mappers.CategoryMapper;
import com.example.ecommercespring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        List<CategoryDTO> listOfCategoryDTO = new ArrayList<>();
        for(Category category : this.categoryRepository.findAll()){
            CategoryDTO dto=CategoryMapper.toDto(category);
            listOfCategoryDTO.add(dto);
        }
        return listOfCategoryDTO;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws IOException {
        Category category = CategoryMapper.toEntity(categoryDTO);
        Category saved=categoryRepository.save(category);
        return CategoryMapper.toDto(saved);
    }

    @Override
    public CategoryDTO getByName(String name) throws IOException {
        Category category = this.categoryRepository.findByName(name)
                .orElseThrow(()->new IOException("Category Not Found with name: "+name));
        return CategoryMapper.toDto(category);
    }


}
