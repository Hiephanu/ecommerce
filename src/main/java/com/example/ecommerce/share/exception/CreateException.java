package com.example.ecommerce.share.exception;

public class CreateException extends DomainException{
    public CreateException(String message){
        super(message,"FAIL_CREATE");
    }
}
