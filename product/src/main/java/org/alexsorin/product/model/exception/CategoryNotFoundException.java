package org.alexsorin.product.model.exception;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long id){
        super(MessageFormat.format("Category with id {0} not found", id));
    }
}
