package com.conor.shopping.tax;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.Product;
import com.conor.shopping.product.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TaxService {

    private ProductService productService;
    private List<Tax> taxes;

    public TaxService(ProductService productService, Tax... taxes) {
        this.productService = productService;
        this.taxes = Arrays.asList(taxes);
    }

    public BigDecimal calculateTax(String productName) throws ProductNotFoundException {
        Product product = productService.getProductByName(productName);

        return taxes.stream()
                .map(tax -> tax.calculate(product))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
