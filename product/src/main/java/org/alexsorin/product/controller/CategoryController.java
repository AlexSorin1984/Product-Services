package org.alexsorin.product.controller;

import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.dto.CategoryDto;
import org.alexsorin.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/v1")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(category->CategoryDto.from(category))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(value="id") Long id){
        Category category = categoryService.getCategory(id);
        CategoryDto categoryDto = CategoryDto.from(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.addCategory(Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> editCategory(@PathVariable(value="id") Long id, @RequestBody CategoryDto categoryDto){
        Category editedCategory = categoryService.editCategory(id, Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(editedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable(value="id") Long id){
        Category categoryToBeDeleted = categoryService.deleteCategory(id);
        return new ResponseEntity<>(CategoryDto.from(categoryToBeDeleted), HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/products/{productId}/add")
    public ResponseEntity<CategoryDto> addProductToCategory(@PathVariable(value="categoryId") Long categoryId,
                                                         @PathVariable(value="productId") Long productId){
        Category category = categoryService.addProductToCategory(categoryId, productId);
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }


}
