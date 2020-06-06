package io.kuoche.share.core.domain;

public class OrderException extends DomainException{
    public OrderException(String message) {
        super(message);
    }
    public OrderException(String messageFormat, Object... args){
        super(String.format(messageFormat, args));
    }
}
