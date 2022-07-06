package org.alexsorin.product.model.dto;

import lombok.*;
import org.alexsorin.product.model.Category;
import org.alexsorin.product.model.Product;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String productName;
    private String productCode;
    private PlainCategoryDto category;

    public static ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setProductCode(product.getProductCode());
        if(Objects.nonNull(product.getCategory())){
            productDto.setCategory(PlainCategoryDto.from(product.getCategory()));
        }
        return productDto;
    }
}
