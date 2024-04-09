package com.example.ecommerce.share.exception;

public class AuthorizeException extends DomainException{
    public AuthorizeException(String message){
        super(message,"Unauthorized");
    }
}
