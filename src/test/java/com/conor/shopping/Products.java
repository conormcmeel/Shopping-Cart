package com.conor.shopping;

import com.conor.shopping.product.Product;

import static com.conor.shopping.tax.TaxBand.*;
import static com.conor.shopping.tax.TaxBand.MEDICAL;
import static com.conor.shopping.tax.TaxBand.MISC;

public class Products {

    public static final Product HEADACHE_PILLS = new Product("headache pills", "4.15", MEDICAL);
    public static final Product BOOK = new Product("Book", "29.49", MISC);
    public static final Product MUSIC_CD = new Product("music CD", "15.99", CD, MISC);
    public static final Product MUSIC_CD2 = new Product("music CD2", "14.99", CD, MISC);
    public static final Product CHOCOLATE_SNACK = new Product("Chocolate Snack", "0.75", MISC);
    public static final Product BOX_OF_PINS = new Product("box of pins", "11.25", MISC);
    public static final Product BOTTLE_OF_WINE = new Product("bottle of wine", "20.99", MISC);
}
