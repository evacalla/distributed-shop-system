package com.distributed.product.infrastructure.adapter.in.mapper;

import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import com.distributed.product.infrastructure.adapter.out.model.Stock;

public class StockMapper {
    public static StockResource convertModelToResource(Stock stock) {
        StockResource stockResource = new StockResource();
        stockResource.setStockId(stock.getId());
        stockResource.setRetailId(stock.getRetailId());
        stockResource.setWarehouseId(stock.getWarehouseId());
        stockResource.setAvailableQuantity(stock.getAvailableQuantity());
        stockResource.setCommittedQuantity(stock.getCommittedQuantity());
        stockResource.setOnHandQuantity(stock.getOnHandQuantity());
        stockResource.setCreatedAt(stock.getCreatedAt().toString());
        stockResource.setUpdatedAt(stock.getUpdatedAt().toString());
        return stockResource;
    }
}
