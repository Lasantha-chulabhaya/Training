package com.LibraryMGT.Training.Service;

import com.LibraryMGT.Training.Model.DTO.CategoryCreateDTO;
import com.LibraryMGT.Training.Model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO);
}
