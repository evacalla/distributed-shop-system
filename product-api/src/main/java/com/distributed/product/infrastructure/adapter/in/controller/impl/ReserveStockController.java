package com.distributed.product.infrastructure.adapter.in.controller.impl;

import com.distributed.product.application.ports.in.IReserveStockUseCase;
import com.distributed.product.infrastructure.adapter.in.controller.IReserveStockController;
import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class ReserveStockController implements IReserveStockController {
    private final IReserveStockUseCase reserveStockUseCase;

    @Autowired
    public ReserveStockController(IReserveStockUseCase reserveStockUseCase) {
        this.reserveStockUseCase = reserveStockUseCase;
    }

    @Override
    public ResponseEntity<StockResource> reserve(StockMovementResource request) {
        return ResponseEntity.status(201).body(reserveStockUseCase.reserve(request));
    }
}
