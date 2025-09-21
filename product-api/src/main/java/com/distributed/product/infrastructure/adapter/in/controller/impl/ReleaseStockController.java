package com.distributed.product.infrastructure.adapter.in.controller.impl;

import com.distributed.product.application.ports.in.IReleaseStockUseCase;
import com.distributed.product.infrastructure.adapter.in.controller.IReleaseStockController;
import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class ReleaseStockController implements IReleaseStockController {
    private final IReleaseStockUseCase releaseStockUseCase;

    @Autowired
    public ReleaseStockController(IReleaseStockUseCase releaseStockUseCase) {
        this.releaseStockUseCase = releaseStockUseCase;
    }

    @Override
    public ResponseEntity<StockResource> release(StockMovementResource request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseStockUseCase.release(request));
    }
}
