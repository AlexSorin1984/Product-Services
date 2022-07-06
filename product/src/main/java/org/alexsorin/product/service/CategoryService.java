package org.alexsorin.product.service;

import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.Product;
import org.alexsorin.product.model.exception.CategoryNotFoundException;
import org.alexsorin.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public Category editCategory(Long id, Category category){
        Category changedCategory = getCategory(id);
        changedCategory.setCategoryName(category.getCategoryName());
        return changedCategory;
    }

    public Category deleteCategory(Long id){
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }

    @Transactional
    public Category addProductToCategory(Long categoryId, Long productId){
        Category category = getCategory(categoryId);
        Product product = productService.getProduct(productId);
        category.addProduct(product);
        product.setCategory(category);
        return category;
    }

    @Transactional
    public Category removeProductFromCategory(Long categoryId, Long productId){
        Category category = getCategory(categoryId);
        Product product = productService.getProduct(productId);
        category.removeProduct(product);
        return category;
    }


}
