package io.kuoche.share.core.domain;

import lombok.Value;

@Value
public class MoneyAndOwner {
    private final String owner;
    private final Integer amount;

    public MoneyAndOwner(String owner, Integer amount){
        if(amount.intValue() < 0){
            throw new MoneyException("money amount not negative");
        }
        this.owner = owner;
        this.amount = amount;
    }
}
