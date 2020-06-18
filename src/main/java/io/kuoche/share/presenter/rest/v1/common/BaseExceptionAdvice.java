package io.kuoche.share.presenter.rest.v1.common;

import io.kuoche.share.core.domain.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BaseExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<Void>  handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            message.append(fieldName).append(":")
                    .append(errorMessage).append(",");
        });
        // delete last ,
        message.setLength(message.length() - 1);
        return Response.fail(StatusCode.BAD_REQUEST, message.toString());
    }

    @ExceptionHandler(DomainException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<Void> handleException(DomainException ex) {
        if(ex.getMessage() == null){
            return Response.fail(StatusCode.VIOLATE_BUSINESS_LOGIC);
        }else{
            return Response.fail(ex);
        }
    }

}
