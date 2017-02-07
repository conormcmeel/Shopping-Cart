package com.conor.shopping.product;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, Product... items) {
        this.productRepository = productRepository;
        Arrays.stream(items).forEach(item -> this.productRepository.add(item));
    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        return productRepository.findByProductName(name);
    }

    public BigDecimal price(String name) throws ProductNotFoundException {
        return productRepository.price(name);
    }
}

