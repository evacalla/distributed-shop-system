package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IGetStockController {
    @GetMapping("/products/{productId}/retail/{retailId}")
    @Operation(summary = "Get stock information for a specific product and retail",
            description = "Retrieve stock details based on the provided product ID and retail ID"
    )
    ResponseEntity<List<StockResource>> getStockByProductAndRetail(@PathVariable("productId") String productId,
                                                                   @PathVariable("retailId") String retailId);
}
