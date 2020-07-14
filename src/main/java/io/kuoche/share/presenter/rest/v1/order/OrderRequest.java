package io.kuoche.share.presenter.rest.v1.order;

import io.kuoche.share.core.usercase.order.CreateOrderUseCase;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class OrderRequest {
    @NotNull
    private String name;

    @NotNull
    @NotEmpty
    @Valid
    private List<OrderDetail> expenditures;

    @NotNull
    @NotEmpty
    @Valid
    private List<OrderDetail> details;

    @NotNull
    private Long activityId;

    public CreateOrderUseCase.InputValues fromThis(){
        return new CreateOrderUseCase.InputValues(
                name,
                expenditures.stream().map(item->
                        new CreateOrderUseCase.InputItem(
                                item.getName(),
                                item.getOwner(),
                                item.getAmount()
                        )
                ).collect(Collectors.toList()),
                details.stream().map(item->
                        new CreateOrderUseCase.InputItem(
                                item.getName(),
                                item.getOwner(),
                                item.getAmount()
                        )
                ).collect(Collectors.toList()),
                activityId
        );
    }

}
