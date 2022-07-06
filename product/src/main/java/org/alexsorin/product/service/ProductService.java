package org.alexsorin.product.service;

import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.Product;
import org.alexsorin.product.model.exception.ProductNotFoundException;
import org.alexsorin.product.repository.CategoryRepository;
import org.alexsorin.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }
    
    public Product editProduct(Long id, Product product){
        Product changedProduct = getProduct(id);
        changedProduct.setProductName(product.getProductName());
        changedProduct.setProductCode(product.getProductCode());
        return productRepository.save(changedProduct);
    }

    public Product deleteProduct(Long id){
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }
}
