package com.LibraryMGT.Training.Service.IMPL;

import com.LibraryMGT.Training.Model.DTO.CategoryCreateDTO;
import com.LibraryMGT.Training.Model.DTO.CategoryDTO;
import com.LibraryMGT.Training.Model.Entity.Category;
import com.LibraryMGT.Training.Service.CategoryService;
import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceIMPL implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("Categories not found");
        }
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .categoryId(category.getCategoryId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        }
        return categoriesDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).get();
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
        return categoryDTO;
    }

    @Override
    public CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        if(categoryCreateDTO.getName().equals(categoryRepo.findByName(categoryCreateDTO.getName()))) {
            throw new EntryDuplicateException("Category name already exists");
        }
        Category category = Category.builder()
                .name(categoryCreateDTO.getName())
                .description(categoryCreateDTO.getDescription())
                .build();
        Category categorynew = categoryRepo.save(category);
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
        return categoryDTO;

    }
}
