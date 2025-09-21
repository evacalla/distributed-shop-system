package com.distributed.product.domain.ports.out.repository;

import com.distributed.product.domain.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends CrudRepository<Stock, String> {

}
