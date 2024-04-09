package com.example.ecommerce.share.exception;

public class ValidException extends DomainException{
    public ValidException(String messgae){
        super(messgae,"INVALID");
    }
}
