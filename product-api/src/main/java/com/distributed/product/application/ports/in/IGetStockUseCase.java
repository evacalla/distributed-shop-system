package com.distributed.product.application.ports.in;

import com.distributed.product.infrastructure.adapter.in.resource.StockResource;

import java.util.List;

public interface IGetStockUseCase {
    List<StockResource> fetchProductIdAndRetailId(String productId, String retailId);
}
