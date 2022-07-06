package org.alexsorin.product.model.dto;

import lombok.*;
import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String categoryName;
    private List<ProductDto> productList = new ArrayList<>();

    public static CategoryDto from(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setProductList(category.getProductList().stream()
                        .map(product->ProductDto.from(product))
                        .collect(Collectors.toList()));
        return categoryDto;
    }


}
