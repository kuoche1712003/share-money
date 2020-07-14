package io.kuoche.share.core.domain;

public class MoneyAndOwnerException extends DomainException{
    public MoneyAndOwnerException(String message) {
        super(message);
    }
    public MoneyAndOwnerException(String messageFormat, Object... args){
        super(String.format(messageFormat, args));
    }
}
