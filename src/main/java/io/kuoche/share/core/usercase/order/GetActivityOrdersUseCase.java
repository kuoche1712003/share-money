package io.kuoche.share.core.usercase.order;

import io.kuoche.share.core.domain.Order;
import io.kuoche.share.core.usercase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetActivityOrdersUseCase extends UseCase<GetActivityOrdersUseCase.InputValues, GetActivityOrdersUseCase.OutputValues>{
    private final OrderRepository orderRepository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(orderRepository.findByActivityId(input.getActivityId()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final Long activityId;
    }
    @Value
    public static class OutputValues implements UseCase.OutputValues{
        private final List<Order> orders;
    }
}
