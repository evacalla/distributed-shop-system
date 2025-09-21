package com.distributed.product.infrastructure.adapter.in.controller;

import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IGetProductController {
    @GetMapping("/retail/{retailId}")
    @Operation(summary = "Get products by retail ID",
            description = "Retrieve a list of products associated with the specified retail ID"
    )
    ResponseEntity<List<ProductResource>> getProductsByRetailId(@PathVariable("retailId") String retailId);

    @GetMapping("/{productId}/retail/{retailId}")
    @Operation(summary = "Get product by product ID and retail ID",
            description = "Retrieve product details based on the provided product ID and retail ID"
    )
    ResponseEntity<ProductResource> getProductByIdAndRetail(@PathVariable("productId") String productId,
                                                            @PathVariable("retailId") String retailId);
}
