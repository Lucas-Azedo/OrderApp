package com.OrderApp.exception.businessException;

import com.OrderApp.exception.BussinessException;

public class ItemNotFound extends BussinessException {
    public ItemNotFound(String message) {
        super(message);
    }
}
