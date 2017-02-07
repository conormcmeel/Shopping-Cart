package com.conor.shopping;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.repository.MapProductRepository;
import com.conor.shopping.product.repository.ProductRepository;
import com.conor.shopping.product.ProductService;
import com.conor.shopping.tax.CompactDiskTax;
import com.conor.shopping.tax.MiscTax;
import com.conor.shopping.tax.Tax;
import com.conor.shopping.tax.TaxService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.conor.shopping.Products.*;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private TaxService taxService;
    private Tax[] taxes = {new CompactDiskTax(), new MiscTax()};

    @Before
    public void setUp() {
        productRepository = new MapProductRepository();
    }

    @Test
    public void empty_basket_cost_nothing() {
        productService = new ProductService(productRepository, HEADACHE_PILLS);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        assertThat(basket.getSalesTax()).isEqualTo(ZERO);
        assertThat(basket.getTotalPrice()).isEqualTo(ZERO);
    }

    @Test
    public void two_headache_pills_incurs_no_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, HEADACHE_PILLS);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("headache pills", "headache pills");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(ZERO);
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(8.30));
    }

    @Test
    public void one_book_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOOK);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("Book");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(5.20));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(34.69));
    }

    @Test
    public void two_books_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOOK);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("Book", "Book");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(10.40));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(69.38));
    }

    @Test
    public void two_cds_incurs_misc_and_cd_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, MUSIC_CD);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("music CD", "music CD");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(8.10));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(40.08));
    }

    @Test
    public void two_different_cds_incurs_misc_and_cd_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, MUSIC_CD, MUSIC_CD2);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("music CD", "music cd2");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(7.95));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(38.93));
    }

    @Test
    public void one_chocolate_snack_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, CHOCOLATE_SNACK);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("Chocolate Snack");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(0.15));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(0.90));
    }

    @Test
    public void two_boxes_of_pins_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOX_OF_PINS);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("box of pins", "box of pins");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(4.00));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(26.50));
    }

    @Test
    public void two_bottles_of_wine_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOTTLE_OF_WINE);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("bottle of wine", "bottle of wine");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(7.40));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(49.38));
    }

    @Test
    public void tooth_ache_pills_buys_headache_pills() throws ProductNotFoundException {
        productService = new ProductService(productRepository, HEADACHE_PILLS);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("tooth ache pills");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(ZERO);
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(4.15));
    }

    @Test
    public void full_basket() throws ProductNotFoundException {
        productService = new ProductService(productRepository,
                BOOK,
                MUSIC_CD,
                CHOCOLATE_SNACK);

        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("Book", "music CD", "Chocolate Snack");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(9.40));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(55.63));
    }

    @Test
    public void another_full_basket() throws ProductNotFoundException {
        productService = new ProductService(productRepository,
                BOTTLE_OF_WINE,
                MUSIC_CD2,
                BOX_OF_PINS,
                HEADACHE_PILLS);

        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("bottle of wine", "music CD2", "box of pins", "tooth ache pills");

        System.out.println(basket);

        assertThat(basket.getSalesTax()).isEqualTo(asBigDecimal(9.60));
        assertThat(basket.getTotalPrice()).isEqualTo(asBigDecimal(60.98));
    }

    @Test(expected = ProductNotFoundException.class)
    public void throwExceptionOnUnknownProduct() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOOK);
        taxService = new TaxService(productService, taxes);
        Basket basket = new Basket(productService, taxService);

        basket.addItems("bo999ok", "unknown", "book");
    }

    private static BigDecimal asBigDecimal(Double value) {
        return BigDecimal.valueOf(value).setScale(2);
    }
}
