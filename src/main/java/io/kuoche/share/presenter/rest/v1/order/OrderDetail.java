package io.kuoche.share.presenter.rest.v1.order;

import lombok.Value;

@Value
public class OrderDetail {
    private final String name;
    private final String owner;
    private final Integer amount;
}
