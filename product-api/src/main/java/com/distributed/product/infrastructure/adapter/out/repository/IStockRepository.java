package com.distributed.product.infrastructure.adapter.out.repository;

import com.distributed.product.infrastructure.adapter.out.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends CrudRepository<Stock, String> {
    List<Stock> findByProductIdAndRetailId(String productId, String retailId);
    Optional<Stock> findByProductIdAndRetailIdAndWarehouseId(String productId, String retailId, String warehouseId);
}
