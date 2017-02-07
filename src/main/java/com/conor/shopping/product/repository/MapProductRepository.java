package com.conor.shopping.product.repository;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MapProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    @Override
    public Product findByProductName(String name) throws ProductNotFoundException{
        Product product = products.get(name.toLowerCase());

        if(product == null) {
            throw new ProductNotFoundException(name + " not supported");
        }
        return product;
    }

    @Override
    public BigDecimal price(String name) throws ProductNotFoundException{
        return findByProductName(name.toLowerCase()).getPrice();
    }

    @Override
    public void add(Product product) {
        products.put(product.getName().toLowerCase(), product);
    }
}




