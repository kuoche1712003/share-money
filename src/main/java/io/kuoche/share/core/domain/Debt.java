package io.kuoche.share.core.domain;

import lombok.Value;

@Value
public class Debt {
    private String debtor;
    private String creditor;
    private Integer amount;

    public Debt calculateDebt(Debt counter){
        if(amount.equals(counter.getAmount())){
            return null;
        }else if( amount.intValue() > counter.getAmount().intValue() ){
            int amount = getAmount().intValue() - counter.getAmount().intValue();
            return new Debt(debtor, creditor, Integer.valueOf(amount));
        }else{
            int amount = counter.getAmount().intValue() - getAmount().intValue();
            return new Debt(counter.getDebtor(),counter.getCreditor(), Integer.valueOf(amount));
        }
    }
}
