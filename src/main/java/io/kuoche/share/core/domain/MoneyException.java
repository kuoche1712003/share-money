package io.kuoche.share.core.domain;

public class MoneyException extends DomainException{
    public MoneyException(String message) {
        super(message);
    }
    public MoneyException(String messageFormat, Object... args){
        super(String.format(messageFormat, args));
    }
}
