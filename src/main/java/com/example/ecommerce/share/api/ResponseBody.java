package com.example.ecommerce.share.api;

import com.example.ecommerce.share.enums.HttpCode;
import lombok.Builder;

@Builder
public class ResponseBody {
    public final String message;
    public final Object data;
    public final HttpCode code;
}

