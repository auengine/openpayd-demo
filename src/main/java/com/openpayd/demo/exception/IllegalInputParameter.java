package com.openpayd.demo.exception;

import com.openpayd.demo.exception.base.RuntimeExceptionBase;

public class IllegalInputParameter extends RuntimeExceptionBase {
    private static final long serialVersionUID = 1L;

    public IllegalInputParameter(){}

    public IllegalInputParameter(String input, Exception e){
        super("Illegal input:" + input,e );
    }
    public IllegalInputParameter(String input){
        super("Illegal input:" + input,null );
    }
}
