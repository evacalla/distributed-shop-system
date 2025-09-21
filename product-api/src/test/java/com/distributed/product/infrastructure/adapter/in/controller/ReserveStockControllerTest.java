package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.application.ports.in.IReserveStockUseCase;
import com.distributed.product.fixture.FixtureObjects;
import com.distributed.product.infrastructure.adapter.in.controller.impl.ReserveStockController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

public class ReserveStockControllerTest {
    @InjectMocks private ReserveStockController controller;
    @Mock private IReserveStockUseCase reserveStockUseCase;

    private final FixtureObjects fixture = new FixtureObjects();
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void when_stock_reserve_then_ok() throws Exception{
        Mockito.when(reserveStockUseCase.reserve(Mockito.any())).thenReturn(fixture.getStockResource());

        mockMvc.perform(MockMvcRequestBuilders.post("/stock/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {"productId":"prod123","retailId":"R-01","numberOfElement":10,"warehouseId":"WH-01"}
                        """.getBytes(StandardCharsets.UTF_8))
        ).andExpectAll(
                MockMvcResultMatchers.status().isCreated()
        );

        Mockito.verify(reserveStockUseCase, Mockito.atLeastOnce()).reserve(Mockito.any());
    }
}
