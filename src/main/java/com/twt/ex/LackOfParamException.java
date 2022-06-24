package com.twt.ex;

public class LackOfParamException extends RuntimeException {
    public LackOfParamException(){
        super();
    }

    public LackOfParamException(String message,Throwable cause){
        super(message, cause);
    }

    public LackOfParamException(String message){
        super(message);
    }

    public LackOfParamException(Throwable cause){
        super(cause);
    }

}
