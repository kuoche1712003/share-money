package io.kuoche.share.core.domain;

public abstract class DomainException extends RuntimeException{
    public DomainException(String message){
        super(message);
    }
}
