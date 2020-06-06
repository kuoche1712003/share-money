package io.kuoche.share.presenter.rest.v1.common;

public enum StatusCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    VIOLATE_BUSINESS_LOGIC(499, "Violate Business Logic"),
    Internal_Server_Error(500, "Internal Server Error");

    private Integer code;
    private String message;

    StatusCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
