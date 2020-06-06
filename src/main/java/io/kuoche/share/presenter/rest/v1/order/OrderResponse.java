package io.kuoche.share.presenter.rest.v1.order;

import io.kuoche.share.core.domain.Order;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class OrderResponse {
    private final String name;
    private final Integer amount;
    private final List<OrderDetail> expenditures;
    private final List<OrderDetail> details;

    public static OrderResponse from(Order order){
        return new OrderResponse(
                order.getName(),
                order.getDetailAmount(),
                order.getExpenditures().stream().map(item->
                        new OrderDetail(
                                item.getName(),
                                item.getMoney().getOwner(),
                                item.getMoney().getAmount()
                        )).collect(Collectors.toList()),
                order.getDetails().stream().map(item->
                        new OrderDetail(
                                item.getName(),
                                item.getMoney().getOwner(),
                                item.getMoney().getAmount()
                        )).collect(Collectors.toList())
                );
    }

}
