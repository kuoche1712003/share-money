package io.kuoche.share.presenter.rest.v1.debt;

import lombok.Value;

@Value
public class DebtResponse {
    private String debtor;
    private String creditor;
    private Integer amount;
}
