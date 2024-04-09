package com.example.ecommerce.share.model;

import lombok.Builder;

@Builder
public class Result <S,F>{
    public final S successData;
    public final F failedData;
    public final boolean isSuccess;
    public static <S,F> Result<S,F> success(S successData){
        return new Result<>(
                successData,
                null,
                true
        );
    }
    public static <S,F> Result<S,F> fail(F failedData){
        return new Result<>(
                null,
                failedData,
                false
        );
    }
    public boolean isFailed(){
        return !isSuccess;
    }
}
