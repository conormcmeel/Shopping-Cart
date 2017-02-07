package com.conor.shopping.tax;

import com.conor.shopping.product.Product;

import java.math.BigDecimal;

public interface Tax {

    BigDecimal calculate(Product product);
}
