package com.distributed.product.application.ports.in;

import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;

public interface IReleaseStockUseCase {
    StockResource release(StockMovementResource release);
}
