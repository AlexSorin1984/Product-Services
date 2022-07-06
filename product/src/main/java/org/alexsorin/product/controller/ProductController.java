package org.alexsorin.product.controller;

import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.Product;
import org.alexsorin.product.model.dto.ProductDto;
import org.alexsorin.product.repository.ProductRepository;
import org.alexsorin.product.service.CategoryService;
import org.alexsorin.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/v1")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        Product product = productService.addProduct(Product.from(productDto));
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> productList = productService.getAllProducts();
        List<ProductDto> productDtoList = productList.stream()
                .map(product -> ProductDto.from(product))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
        //return new ResponseEntity<>(new Product(1L, "Ball", "ABC123"), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "id") Long id){
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable(value = "id") Long id, @RequestBody ProductDto productDto){
        Product editedProduct = productService.editProduct(id, Product.from(productDto));
        return new ResponseEntity<>(ProductDto.from(editedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable(value = "id") Long id){
        Product productToBeDeleted = productService.deleteProduct(id);
        return new ResponseEntity<>(ProductDto.from(productToBeDeleted), HttpStatus.OK);
    }
}
