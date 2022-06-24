package com.twt.ex;

public class InValidAccessException extends RuntimeException {
    public InValidAccessException(){
        super();
    }

    public InValidAccessException(String message,Throwable cause){
        super(message, cause);
    }

    public InValidAccessException(String message){
        super(message);
    }

    public InValidAccessException(Throwable cause){
        super(cause);
    }
}
