package com.conor.shopping.lineitem;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class LineItem {

    private final String name;
    private Integer quantity = 0;
    private Cost cost;

    public LineItem(String name, Integer quantity, Cost cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public LineItem(String name, Cost cost) {
        this(name, 1, cost);
    }

    public void update(Cost cost) {
        this.cost = this.cost.add(cost);
        this.quantity++;
    }

    public BigDecimal getTax() {
        return this.cost.getTax();
    }

    public BigDecimal getNetPrice() {
        return cost.getGross().add(getTax());
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(quantity.toString());
        joiner.add(name);
        joiner.add(":");
        joiner.add(getNetPrice().toString());

        return "\n" + joiner.toString();
    }
}
