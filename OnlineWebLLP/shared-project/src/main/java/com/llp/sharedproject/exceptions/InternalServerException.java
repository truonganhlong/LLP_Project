package com.llp.sharedproject.exceptions;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
