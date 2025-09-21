package com.distributed.product.domain.ports.out.repository;

import com.distributed.product.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, String> {
}
