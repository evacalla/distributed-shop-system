package com.distributed.product.infrastructure.adapter.out.repository;

import com.distributed.product.infrastructure.adapter.out.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends CrudRepository<Product, String> {
    List<Product> findByRetailId(String retailId);
    Optional<Product> findByProductIdAndRetailId(String productId, String retailId);
}
