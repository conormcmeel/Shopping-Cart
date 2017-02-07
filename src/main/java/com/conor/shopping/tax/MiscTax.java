package com.conor.shopping.tax;

import com.conor.shopping.product.Product;

import java.math.BigDecimal;

import static com.conor.shopping.tax.TaxBand.MISC;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.UP;

public class MiscTax implements Tax {

    public static final BigDecimal ROUND_TO = new BigDecimal("0.05");
    public static final BigDecimal TAX_RATE = new BigDecimal("17.5");

    public BigDecimal calculate(Product product) {

        if(!product.getApplicableTaxes().contains(MISC)){
            return ZERO;
        }
        return percentage(product.getPrice(), TAX_RATE);
    }

    private BigDecimal percentage(BigDecimal base, BigDecimal percentage){
        return round(base.multiply(percentage).divide(new BigDecimal(100)));
    }

    private BigDecimal round(BigDecimal value) {
        BigDecimal divided = value.divide(ROUND_TO, 0, UP);
        return divided.multiply(ROUND_TO);
    }
}

