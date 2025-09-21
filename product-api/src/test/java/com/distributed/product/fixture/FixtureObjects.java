package com.distributed.product.fixture;

import com.distributed.product.infrastructure.adapter.in.mapper.ProductMapper;
import com.distributed.product.infrastructure.adapter.in.mapper.StockMapper;
import com.distributed.product.infrastructure.adapter.in.resource.ProductResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import com.distributed.product.infrastructure.adapter.out.model.Product;
import com.distributed.product.infrastructure.adapter.out.model.Stock;

import java.time.LocalDateTime;

public class FixtureObjects {
    public Product getProduct() {
        Product product = new Product();
        product.setProductId("some-product-id");
        product.setRetailId("some-retail-id");
        product.setName("Some Product");
        product.setDescription("This is a description of some product.");
        product.setPrice(java.math.BigDecimal.valueOf(19.99));
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public Stock getStock() {
        Stock stock = new Stock();
        stock.setId("some-stock-id");
        stock.setProductId("some-product-id");
        stock.setRetailId("some-retail-id");
        stock.setWarehouseId("some-warehouse-id");
        stock.setAvailableQuantity(100);
        stock.setCommittedQuantity(20);
        stock.setOnHandQuantity(120);
        stock.setUpdatedAt(LocalDateTime.now());
        stock.setCreatedAt(LocalDateTime.now());
        return stock;
    }

    public StockMovementResource getStockReserveResource() {
        StockMovementResource reserve = new StockMovementResource();
        reserve.setProductId("some-product-id");
        reserve.setRetailId("some-retail-id");
        reserve.setWarehouseId("some-warehouse-id");
        reserve.setNumberOfElement(10);
        return reserve;
    }

    public StockMovementResource getStockReleaseResource() {
        StockMovementResource release = new StockMovementResource();
        release.setProductId("some-product-id");
        release.setRetailId("some-retail-id");
        release.setWarehouseId("some-warehouse-id");
        release.setNumberOfElement(10);
        return release;
    }

    public StockResource getStockResource() {
        return StockMapper.convertModelToResource(this.getStock());
    }

    public ProductResource getProductResource() {
        return ProductMapper.convertModelToResource(this.getProduct());
    }

    public StockMovementResource getStockOnHandResource() {
        StockMovementResource onHand = new StockMovementResource();
        onHand.setProductId("some-product-id");
        onHand.setRetailId("some-retail-id");
        onHand.setWarehouseId("some-warehouse-id");
        onHand.setNumberOfElement(15);
        return onHand;
    }
}
