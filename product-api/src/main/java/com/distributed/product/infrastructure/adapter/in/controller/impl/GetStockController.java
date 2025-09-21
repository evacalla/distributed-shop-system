package com.distributed.product.infrastructure.adapter.in.controller;


import com.distributed.product.application.ports.in.IGetStockUseCase;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class GetStockController implements IGetStockController {

    private final IGetStockUseCase getStockUseCase;

    @Autowired
    public GetStockController(IGetStockUseCase getStockUseCase) {
        this.getStockUseCase = getStockUseCase;
    }

    @Override
    public ResponseEntity<List<StockResource>> getStockByProductAndRetail(String productId, String retailId) {
        return ResponseEntity.ok(getStockUseCase.fetchProductIdAndRetailId(productId, retailId));
    }
}
