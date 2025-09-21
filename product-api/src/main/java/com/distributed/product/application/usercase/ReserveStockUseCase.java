package com.distributed.product.application.usercase;

import com.distributed.product.application.ports.in.IReserveStockUseCase;
import com.distributed.product.domain.exception.InsufficientStockException;
import com.distributed.product.domain.exception.NotFoundException;
import com.distributed.product.domain.exception.StockVersionException;
import com.distributed.product.infrastructure.adapter.in.mapper.StockMapper;
import com.distributed.product.infrastructure.adapter.in.resource.StockMovementResource;
import com.distributed.product.infrastructure.adapter.in.resource.StockResource;
import com.distributed.product.infrastructure.adapter.out.model.Stock;
import com.distributed.product.infrastructure.adapter.out.repository.IStockRepository;
import jakarta.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReserveStockUseCase implements IReserveStockUseCase {
    private static final Logger log = LoggerFactory.getLogger(ReserveStockUseCase.class);

    private final IStockRepository repository;

    @Autowired
    public ReserveStockUseCase(IStockRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public StockResource reserve(StockMovementResource reserve) {
        log.info("[RESERVE-STOCK-USE-CASE]Reserving stock for: {}", reserve);
        final Stock stock = repository.findByProductIdAndRetailIdAndWarehouseId(reserve.getProductId(), reserve.getRetailId(), reserve.getWarehouseId()).map(it -> {
            this.verifyAvailableStock(it);

            it.setAvailableQuantity(it.getAvailableQuantity() - reserve.getNumberOfElement());
            it.setCommittedQuantity(it.getCommittedQuantity() + reserve.getNumberOfElement());

            return this.save(it);
        }).orElseThrow(() -> {
            log.warn("[RESERVE-STOCK-USE-CASE] Stock not found for: {}", reserve);
            return new NotFoundException("Stock not found");
        });


        return StockMapper.convertModelToResource(stock);
    }

    private void verifyAvailableStock(Stock stock) {
        if (stock.getAvailableQuantity() <= 0) {
            log.warn("[RESERVE-STOCK-USE-CASE] No available stockId: {}", stock.getId());
            throw new InsufficientStockException("No available stock");
        }
    }

    private Stock save(Stock stock) {
        try {
            log.info("[RESERVE-STOCK-USE-CASE] Saving stock: {}", stock);
            return repository.save(stock);
        } catch (OptimisticLockException ex) {
            log.warn("[RESERVE-STOCK-USE-CASE] OptimisticLockException occurred while saving stock: {}", stock);
            throw new StockVersionException("Stock version conflict, please retry");
        }
    }

}
