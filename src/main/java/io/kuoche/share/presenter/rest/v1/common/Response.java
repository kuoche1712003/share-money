package io.kuoche.share.presenter.rest.v1.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.kuoche.share.core.domain.DomainException;

public class Response<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private Integer code;
    private String message;

    public Response(T data, StatusCode statusCode) {
        this.data = data;
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public Response(T data, Integer code, String message){
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static Response ok (){
        return new Response(null, StatusCode.SUCCESS);
    }

    public static Response ok (Object data){
        return new Response(data, StatusCode.SUCCESS);
    }

    public static Response fail(StatusCode statusCode){
        return new Response(null, statusCode);
    }

    public static Response fail(StatusCode statusCode, String message){
        return new Response(null, statusCode.getCode(), message);
    }

    public static Response fail(DomainException ex){
        return new Response(null, StatusCode.VIOLATE_BUSINESS_LOGIC.getCode(), ex.getMessage());
    }

    public T getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
