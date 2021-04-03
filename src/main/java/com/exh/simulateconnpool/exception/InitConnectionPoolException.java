package com.exh.simulateconnpool.exception;

public class InitConnectionPoolException extends RuntimeException{
    public InitConnectionPoolException(){}
    public InitConnectionPoolException(String msg){
        super(msg);
    }
}
