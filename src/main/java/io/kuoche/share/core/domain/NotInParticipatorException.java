package io.kuoche.share.core.domain;

public class NotInParticipatorException extends DomainException{
    public NotInParticipatorException(String message) {
        super(message);
    }
    public NotInParticipatorException(String messageFormat, Object... args){
        super(String.format(messageFormat, args));
    }
}
