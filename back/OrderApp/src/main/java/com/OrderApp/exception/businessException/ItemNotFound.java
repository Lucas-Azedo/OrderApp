package com.OrderApp.exception.businessException;

import com.OrderApp.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ItemNotFound extends BusinessException {
    public ItemNotFound(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
