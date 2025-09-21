package com.distributed.product.domain.exception;

public class CommitedStockException extends RuntimeException{
    public CommitedStockException(String message) {
        super(message);
    }
}
