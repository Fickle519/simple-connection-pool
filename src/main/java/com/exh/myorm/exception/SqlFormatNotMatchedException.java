package com.exh.myorm.exception;

public class SqlFormatNotMatchedException extends RuntimeException{
    public SqlFormatNotMatchedException(){}
    public SqlFormatNotMatchedException(String msg){
        super(msg);
    }
}
