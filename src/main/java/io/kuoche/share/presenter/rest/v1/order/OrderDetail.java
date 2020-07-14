package io.kuoche.share.presenter.rest.v1.order;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class OrderDetail {
    @NotNull
    private final String name;
    @NotNull
    private final String owner;
    @NotNull
    @Positive
    private final Integer amount;
}
