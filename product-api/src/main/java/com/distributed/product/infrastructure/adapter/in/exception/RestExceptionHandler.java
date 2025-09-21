package com.distributed.product.infrastructure.adapter.in.exception;

import com.distributed.product.domain.exception.CommitedStockException;
import com.distributed.product.domain.exception.InsufficientStockException;
import com.distributed.product.domain.exception.NotFoundException;
import com.distributed.product.domain.exception.StockVersionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockVersionException.class)
    public final ResponseEntity<ErrorResponse> handleStockVersionException(StockVersionException ex, HttpServletRequest request) {
        logger.warn("[REST-EXCEPTION-HANDLER] handle stock version exception", ex);
        final HttpStatus status = HttpStatus.CONFLICT;
        return this.handleErrorExceptions(status, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CommitedStockException.class)
    public final ResponseEntity<ErrorResponse> handleCommitedStockException(CommitedStockException ex, HttpServletRequest request) {
        logger.warn("[REST-EXCEPTION-HANDLER] handle commited stock exception", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return this.handleErrorExceptions(status, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public final ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException ex, HttpServletRequest request) {
        logger.warn("[REST-EXCEPTION-HANDLER] handle insufficient stock exception", ex);
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return this.handleErrorExceptions(status, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        logger.warn("[REST-EXCEPTION-HANDLER] handle not found exception", ex);
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return this.handleErrorExceptions(status, ex.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> handleErrorExceptions(HttpStatus status,
                                                                String errorMessage, String requestURI) {
        final ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss")) ,status.value(), errorMessage, requestURI);
        return new ResponseEntity<>(errorResponse, status);
    }

}
