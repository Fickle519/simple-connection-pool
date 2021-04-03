package com.exh.simulateconnpool.exception;

public class ConnectionPoolBusyException extends RuntimeException{
    public ConnectionPoolBusyException(){}
    public ConnectionPoolBusyException(String msg){
        super(msg);
    }
}
