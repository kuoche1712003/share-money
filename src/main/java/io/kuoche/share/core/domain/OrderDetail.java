package io.kuoche.share.core.domain;


import lombok.Value;

@Value
public class OrderDetail {
    private Long id;
    private String name;
    private MoneyAndOwner money;
}
