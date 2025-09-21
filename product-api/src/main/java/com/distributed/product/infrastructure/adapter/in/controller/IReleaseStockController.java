package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IReleaseStockController {
    @PostMapping("/release")
    @Operation(
            summary = "Reserve stock for a product at a retail location",
            description = "Reserve a specified quantity of stock for a given product and retail location",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock successfully reserved"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "404", description = "Product or retail location not found"),
                    @ApiResponse(responseCode = "409", description = "Insufficient stock available to fulfill the reservation")
            }
    )
    ResponseEntity<StockResource> release(@RequestBody @Valid StockMovementResource request);

}
