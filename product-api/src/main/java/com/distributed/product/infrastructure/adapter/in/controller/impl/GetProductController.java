package com.distributed.product.infrastructure.adapter.in.controller.impl;

import com.distributed.product.application.ports.in.IGetProductUseCase;
import com.distributed.product.infrastructure.adapter.in.controller.IGetProductController;
import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class GetProductController implements IGetProductController {

    private final IGetProductUseCase getProductUseCase;

    @Autowired
    public GetProductController(final IGetProductUseCase getProductUseCase) {
        this.getProductUseCase = getProductUseCase;
    }

    @Override
    public ResponseEntity<List<ProductResource>> getProductsByRetailId(String retailId) {
        return ResponseEntity.ok(getProductUseCase.getProductByRetailId(retailId));
    }

    @Override
    public ResponseEntity<ProductResource> getProductByIdAndRetail(String productId, String retailId) {
        return ResponseEntity.ok(getProductUseCase.getProductByIdAndRetailId(productId, retailId));
    }
}
