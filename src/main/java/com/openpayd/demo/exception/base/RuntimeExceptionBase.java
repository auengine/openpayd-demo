package com.openpayd.demo.exception.base;

import com.openpayd.demo.config.constants.ApiErrors;

import java.util.Optional;

public abstract class RuntimeExceptionBase extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected Optional<ApiErrors> apiErrors;

    public RuntimeExceptionBase(){}

    public RuntimeExceptionBase(String message,Exception e){
        super(message,e );
    }

    public RuntimeExceptionBase(Optional<ApiErrors> apiErrors,String message,Exception e){
        super(message,e );
        this.apiErrors=apiErrors;
    }

    public Optional<ApiErrors> getApiErrors() {
        return apiErrors;
    }

    public void setApiErrors(ApiErrors apiErrors) {
        this.apiErrors = Optional.ofNullable(apiErrors);
    }


}
