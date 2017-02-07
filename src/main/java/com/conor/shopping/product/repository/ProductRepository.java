package com.conor.shopping.product.repository;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.Product;

import java.math.BigDecimal;

public interface ProductRepository {

    Product findByProductName(String name) throws ProductNotFoundException;

    BigDecimal price(String name) throws ProductNotFoundException;

    void add(Product product);
}
