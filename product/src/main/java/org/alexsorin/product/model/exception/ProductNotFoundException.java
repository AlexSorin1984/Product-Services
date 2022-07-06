package org.alexsorin.product.model.exception;

import org.apache.logging.log4j.message.Message;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id){
        super(MessageFormat.format("Could not find product with id {0}", id));
    }
}
