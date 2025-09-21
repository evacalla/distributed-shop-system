package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.application.ports.in.IGetProductUseCase;
import com.distributed.product.fixture.FixtureObjects;
import com.distributed.product.infrastructure.adapter.in.controller.impl.GetProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

class GetProductControllerTest {
    @InjectMocks private GetProductController controller;
    @Mock private IGetProductUseCase getProductUseCase;

    private final FixtureObjects fixture = new FixtureObjects();
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void when_get_product_by_retail_id_then_ok() throws Exception {
        final String retailId = "retail-123";
        Mockito.when(getProductUseCase.getProductByRetailId(retailId)).thenReturn(List.of(fixture.getProductResource()));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/retail/{retailId}", retailId))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                );

        Mockito.verify(getProductUseCase, Mockito.atLeastOnce()).getProductByRetailId(retailId);
    }

    @Test
    void when_get_product_by_id_and_retail_id_then_ok() throws Exception{
        final String retailId = "retail-123";
        final String productId = "product-123";
        Mockito.when(getProductUseCase.getProductByIdAndRetailId(productId, retailId)).thenReturn(fixture.getProductResource());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}/retail/{retailId}", productId, retailId))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                );

        Mockito.verify(getProductUseCase, Mockito.atLeastOnce()).getProductByIdAndRetailId(productId, retailId);
    }
}
