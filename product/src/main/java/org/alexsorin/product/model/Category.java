package org.alexsorin.product.model;

import lombok.*;
import org.alexsorin.product.model.dto.CategoryDto;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private List<Product> productList = new ArrayList<>();

    public static Category from(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }

    public void addProduct(Product product){
        productList.add(product);
    }
    public void removeProduct(Product product){ productList.remove(product); }

}
