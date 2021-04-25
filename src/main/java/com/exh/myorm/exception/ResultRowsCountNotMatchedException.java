package com.exh.myorm.exception;

public class ResultRowsCountNotMatchedException extends RuntimeException{
    public ResultRowsCountNotMatchedException(){}
    public ResultRowsCountNotMatchedException(String msg){
        super(msg);
    }
}
