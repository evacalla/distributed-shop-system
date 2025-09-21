package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IReserveStockController {
    @PostMapping("/reserve")
    @Operation(
            summary = "Reserve stock for a product at a specific retail location",
            description = "Reserve a specified quantity of stock for a given product and retail location",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Stock reserved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "404", description = "Product or retail location not found"),
                    @ApiResponse(responseCode = "409", description = "Insufficient stock available to reserve")
            }
    )
    ResponseEntity<StockResource> reserve(@RequestBody @Valid StockMovementResource request);

}
