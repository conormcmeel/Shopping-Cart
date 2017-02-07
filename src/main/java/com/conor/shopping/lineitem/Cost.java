package com.conor.shopping.lineitem;

import java.math.BigDecimal;

public class Cost {

    private final BigDecimal gross;
    private final BigDecimal tax;

    public Cost(BigDecimal gross, BigDecimal tax) {
        this.gross = gross;
        this.tax = tax;
    }

    public Cost add(Cost cost) {
        return new Cost(this.gross.add(cost.gross), this.tax.add(cost.tax));
    }

    public BigDecimal getGross() {
        return gross;
    }

    public BigDecimal getTax() {
        return tax;
    }
}
