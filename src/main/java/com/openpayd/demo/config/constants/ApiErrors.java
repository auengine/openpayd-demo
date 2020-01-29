package com.openpayd.demo.config.constants;

public enum ApiErrors {
    GENERIC_ERROR("E10001", "Application error occured."),
    INVALID_REQUEST_PARAMETERS("E10002", "Invalid request parameters"),
    INVALID_OBJECT("E10003", "Invalid request parameters"),
    INVALID_TRANSACTION_ID_OR_DATE("E10004", "Invalid transaction id or date!"),
    INVALID_DATE_FROMAT("E10005", "Invalid date format!");



    private final String errorCode;
    private final String errorMessage;

    ApiErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
