package com.conor.shopping.tax;

import com.conor.shopping.product.Product;

import java.math.BigDecimal;

import static com.conor.shopping.tax.TaxBand.CD;
import static java.math.BigDecimal.*;

public class CompactDiskTax implements Tax {

    public static final BigDecimal TAX = new BigDecimal("1.25");

    @Override
    public BigDecimal calculate(Product product) {

        if(!product.getApplicableTaxes().contains(CD)){
            return ZERO;
        }
        return TAX;
    }
}
