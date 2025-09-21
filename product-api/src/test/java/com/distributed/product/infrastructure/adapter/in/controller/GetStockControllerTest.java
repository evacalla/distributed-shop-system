package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.application.ports.in.IGetStockUseCase;
import com.distributed.product.fixture.FixtureObjects;
import com.distributed.product.infrastructure.adapter.in.controller.impl.GetStockController;
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

class GetStockControllerTest {
    @InjectMocks private GetStockController controller;
    @Mock private IGetStockUseCase getStockUseCase;

    private final FixtureObjects fixture = new FixtureObjects();

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void when_get_stock_by_productId_and_retail_then_ok() throws Exception {

        Mockito.when(getStockUseCase.fetchProductIdAndRetailId(Mockito.any(), Mockito.any())).thenReturn(List.of(fixture.getStockResource()));

        mockMvc.perform(MockMvcRequestBuilders.get("/stock/products/{productId}/retail/{retailId}", "product-123", "retail-123"))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                );

        Mockito.verify(getStockUseCase, Mockito.atLeastOnce()).fetchProductIdAndRetailId(Mockito.any(), Mockito.any());
    }
}
