package com.conor.shopping;

import com.conor.shopping.exception.ProductNotFoundException;
import com.conor.shopping.product.Product;
import com.conor.shopping.product.repository.ProductRepository;
import com.conor.shopping.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProductServiceTest {

    private ProductService productService;
    @Mock private ProductRepository productRepository;

    @Parameter(value = 0) public String product;
    @Parameter(value = 1) public BigDecimal expectedPrice;

    @Before
    public void setUp() {
        initMocks(this);
        productService = new ProductService(productRepository,
                new Product("Book", "29.49"),
                new Product("music CD", "15.99"),
                new Product("chocolate snack", "0.75"),
                new Product("bottle of wine", "20.99"),
                new Product("headache pills", "4.15"));
    }

    @Parameters(name = "{index}: price({0}) = {1}")
    public static Collection prices() {
        return Arrays.asList(new Object[][]{
                {"book", asBigDecimal(29.49)},
                {"music cd", asBigDecimal(15.99)},
                {"chocolate snack", asBigDecimal(0.75)},
                {"bottle of wine", asBigDecimal(20.99)},
                {"headache pills", asBigDecimal(4.15)}
        });
    }

    @Test
    public void validatePrices() throws ProductNotFoundException {
        when(productRepository.price(product)).thenReturn(expectedPrice);
        assertThat(productService.price(product)).isEqualTo(expectedPrice);
    }

    @Test(expected = ProductNotFoundException.class)
    public void handleUnknownProduct() throws ProductNotFoundException {
        String unknownProduct = "dinosaur";
        when(productRepository.price(unknownProduct)).thenThrow(ProductNotFoundException.class);
        productService.price(unknownProduct);
    }

    private static BigDecimal asBigDecimal(Double value) {
        return BigDecimal.valueOf(value).setScale(2);
    }
}

