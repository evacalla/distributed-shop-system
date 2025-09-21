package com.distributed.product.application.ports.in;

import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;

import java.util.List;

public interface IGetProductUseCase {
    List<ProductResource> getProductByRetailId(String retailId);

    ProductResource getProductByIdAndRetailId(String productId, String retailId);
}
