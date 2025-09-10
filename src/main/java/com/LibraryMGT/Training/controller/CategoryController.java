package com.LibraryMGT.Training.controller;


import com.LibraryMGT.Training.Model.DTO.CategoryCreateDTO;
import com.LibraryMGT.Training.Model.DTO.CategoryDTO;
import com.LibraryMGT.Training.Service.CategoryService;
import com.LibraryMGT.Training.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<StandardResponse> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", categories),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", category),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryCreateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", createdCategory),
                HttpStatus.OK
        );
    }
}
