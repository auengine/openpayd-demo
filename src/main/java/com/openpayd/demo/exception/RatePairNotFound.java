package com.openpayd.demo.exception;

import com.openpayd.demo.exception.base.RuntimeExceptionBase;

public class RatePairNotFound extends RuntimeExceptionBase {
    private static final long serialVersionUID = 1L;

    public RatePairNotFound() {
    }

    public RatePairNotFound(String base, String symbol, Exception e) {
        super("Not found for base:" + base + " symbol:" + symbol, e);
    }

    public RatePairNotFound(String base, String symbol) {
        super("Not found for base:" + base + " symbol:" + symbol, null);
    }
}
