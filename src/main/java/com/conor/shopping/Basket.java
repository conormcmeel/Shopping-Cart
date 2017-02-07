package com.conor.shopping;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.lineitem.Cost;
import com.conor.shopping.lineitem.LineItem;
import com.conor.shopping.product.ProductService;
import com.conor.shopping.tax.TaxService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private final ProductService productService;
    private final TaxService taxService;
    private final Map<String, LineItem> items;

    public Basket(ProductService productService, TaxService taxService) {
        this.items = new HashMap<>();
        this.productService = productService;
        this.taxService = taxService;
    }

    public void addItems(String... names) throws ProductNotFoundException {

        for(String name : names) {
            name = name.equalsIgnoreCase("tooth ache pills") ? "headache pills" : name;  //hack alert: see ReadMe

            if (items.containsKey(name.toLowerCase())) {
                updateExistingLineItem(name);
            } else {
                addNewLineItem(name);
            }
        }
    }

    private void updateExistingLineItem(String name) throws ProductNotFoundException {
        Cost cost = calculateCost(name);
        items.get(name.toLowerCase()).update(cost);
    }

    private void addNewLineItem(String name) throws ProductNotFoundException {
        Cost cost = calculateCost(name);
        LineItem lineItem = new LineItem(name, cost);
        items.put(name.toLowerCase(), lineItem);
    }

    private Cost calculateCost(String name) throws ProductNotFoundException {
        BigDecimal gross = productService.price(name);
        BigDecimal tax = taxService.calculateTax(name);
        return new Cost(gross, tax);
    }

    public BigDecimal getTotalPrice() {
        return items.values()
                .stream().map(LineItem::getNetPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSalesTax() {
        return items.values()
                .stream().map(LineItem::getTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        String formattedItems = items.values().toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();

        return formattedItems + "\nSales Taxes: " + getSalesTax() + "\nTotal: " + getTotalPrice() + "\n";
    }
}
