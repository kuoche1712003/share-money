package io.kuoche.share.presenter.rest.v1.common;

import io.kuoche.share.core.domain.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BaseExceptionAdvice {


    @ExceptionHandler(DomainException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<String> handleException(DomainException ex) {
        if(ex.getMessage() == null){
            return Response.fail(StatusCode.VIOLATE_BUSINESS_LOGIC);
        }else{
            return Response.fail(ex);
        }
    }

}
