package org.alexsorin.product.model.dto;

import lombok.*;
import org.alexsorin.product.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlainCategoryDto {
    private Long id;
    private String categoryName;

    public static PlainCategoryDto from(Category category){
        PlainCategoryDto plainCategoryDto = new PlainCategoryDto();
        plainCategoryDto.setId(category.getId());
        plainCategoryDto.setCategoryName(category.getCategoryName());
        return plainCategoryDto;
    }

}
