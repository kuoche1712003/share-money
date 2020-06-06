package io.kuoche.share.presenter.rest.v1.order;

import io.kuoche.share.core.usercase.order.CreateOrderUseCase;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class OrderRequest {
    private String name;
    private List<OrderDetail> expenditures;
    private List<OrderDetail> details;
    private Long activityId;

    public CreateOrderUseCase.InputValues fromThis(){
        return new CreateOrderUseCase.InputValues(
                name,
                expenditures.stream().map(item->
                        new CreateOrderUseCase.InputItem(
                                item.getName(),
                                item.getOwner(),
                                item.getAmount()
                        )).collect(Collectors.toList()),
                details.stream().map(item->
                        new CreateOrderUseCase.InputItem(
                                item.getName(),
                                item.getOwner(),
                                item.getAmount()
                        )).collect(Collectors.toList()),
                activityId
        );
    }

}
