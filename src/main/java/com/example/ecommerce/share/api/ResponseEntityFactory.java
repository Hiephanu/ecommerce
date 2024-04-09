package com.example.ecommerce.share.api;

import com.example.ecommerce.share.enums.HttpCode;
import com.example.ecommerce.share.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityFactory {
    public static ResponseEntity<ResponseBody> ok(Object data){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBody.builder()
                        .message("Success")
                        .code(HttpCode.SUCCESS)
                        .data(data)
                        .build());
    }
    public static ResponseEntity<ResponseBody> badRequest(DomainException domainException){
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBody.builder()
                        .message("Bad request")
                        .code(HttpCode.BAD_REQUEST)
                        .data(domainException.getMessage())
                        .build());
    }
    public static ResponseEntity<ResponseBody> unauthorized(DomainException domainException){
        return  ResponseEntity
                .status((HttpStatus.UNAUTHORIZED))
                .body(ResponseBody.builder()
                        .message("Unauthorized")
                        .code(HttpCode.UNAUTHORIZED)
                        .data(domainException.getMessage())
                        .build());
    }
    public static ResponseEntity<ResponseBody> created(DomainException domainException){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBody.builder()
                        .message("Created")
                        .code(HttpCode.CREATED)
                        .build());
    }
    public static ResponseEntity<ResponseBody> forbidden(){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ResponseBody.builder()
                        .message("forbidden")
                        .code(HttpCode.FORBIDDEN)
                        .build());
    }
    public static ResponseEntity<ResponseBody> notFound(DomainException domainException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBody.builder()
                        .message("Not found")
                        .code(HttpCode.NOT_FOUND)
                        .build());
    }
    public static ResponseEntity<ResponseBody> internalServerError(Throwable throwable){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBody.builder()
                        .message("Internal server error")
                        .code(HttpCode.INTERNAL_SERVER_ERROR)
                        .data(throwable.getMessage())
                        .build());
    }
}
