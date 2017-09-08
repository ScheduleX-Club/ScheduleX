package org.schedulex.exceptions;


public class SchedulexJobInstanceException extends RuntimeException{

    public SchedulexJobInstanceException(){
        super();
    }

    public SchedulexJobInstanceException(String message){
        super(message);
    }

    public SchedulexJobInstanceException(Throwable throwable){
        super(throwable);
    }
}
