package io.kuoche.share.presenter.rest.v1.order;

import io.kuoche.share.core.domain.Order;
import io.kuoche.share.core.usercase.order.CreateOrderUseCase;
import io.kuoche.share.core.usercase.order.GetActivityOrdersUseCase;
import io.kuoche.share.presenter.rest.v1.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetActivityOrdersUseCase getActivityOrdersUseCase;

    @PostMapping("")
    public Response<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        Order order = createOrderUseCase.execute(request.fromThis()).getOrder();
        return Response.ok(OrderResponse.from(order));
    }


    @GetMapping("")
    public Response<List<OrderResponse>> getActivityOrders(@RequestParam Long activityId){
        List<Order> orders = getActivityOrdersUseCase.execute(new GetActivityOrdersUseCase.InputValues(activityId)).getOrders();
        return Response.ok(orders.stream().map(OrderResponse::from).collect(Collectors.toList()));
    }

}
