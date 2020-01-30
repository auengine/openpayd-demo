package com.openpayd.demo.exception;

import com.openpayd.demo.exception.base.RuntimeExceptionBase;

public class ConversionNotFound extends RuntimeExceptionBase {
    private static final long serialVersionUID = 1L;

    public ConversionNotFound() {
    }

    public ConversionNotFound(String message) {
        super(message, null);
    }
}
