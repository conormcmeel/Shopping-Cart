package com.conor.shopping.product;

import com.conor.shopping.tax.TaxBand;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class Product {

    private String name;
    private BigDecimal price;
    private List<TaxBand> applicableTaxes;

    public Product(String name, String price, TaxBand... applicableTaxes) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.applicableTaxes = asList(applicableTaxes);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<TaxBand> getApplicableTaxes() {
        return applicableTaxes;
    }
}
