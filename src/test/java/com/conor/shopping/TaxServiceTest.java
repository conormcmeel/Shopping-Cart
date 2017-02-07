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

public class TaxServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private TaxService taxService;
    private Tax[] taxes = {new CompactDiskTax(), new MiscTax()};

    @Before
    public void setUp() {
        productRepository = new MapProductRepository();
    }

    @Test
    public void headache_pills_incurs_no_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, HEADACHE_PILLS);
        taxService = new TaxService(productService, taxes);

        assertThat(taxService.calculateTax("headache pills")).isEqualTo(ZERO);
    }

    @Test
    public void one_book_incurs_misc_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, BOOK);
        taxService = new TaxService(productService, taxes);

        assertThat(taxService.calculateTax("book")).isEqualTo(asBigDecimal(5.20));
    }

    @Test
    public void cd_incurs_misc_and_cd_tax() throws ProductNotFoundException {
        productService = new ProductService(productRepository, MUSIC_CD);
        taxService = new TaxService(productService, taxes);

        assertThat(taxService.calculateTax("music cd")).isEqualTo(asBigDecimal(4.05));
    }

    private static BigDecimal asBigDecimal(Double value) {
        return BigDecimal.valueOf(value).setScale(2);
    }
}
