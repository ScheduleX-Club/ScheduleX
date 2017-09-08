package org.schedulex.exceptions;


public class SchedulexBasicException extends RuntimeException{

    public SchedulexBasicException(){
        super();
    }

    public SchedulexBasicException(String message){
        super(message);
    }

    public SchedulexBasicException(Throwable throwable){
        super(throwable);
    }
}
