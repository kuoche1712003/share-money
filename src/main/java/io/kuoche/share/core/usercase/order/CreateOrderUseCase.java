package io.kuoche.share.core.usercase.order;


import io.kuoche.share.core.domain.*;
import io.kuoche.share.core.usercase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateOrderUseCase extends UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues>{
    private final ParticipatorRepository participatorRepository;
    private final OrderRepository orderRepository;

    @Override
    public OutputValues execute(InputValues input) {
        Map<String, Participator> map = participatorRepository.getActivityParticipators(input.getActivityId())
                .stream().collect(Collectors.toMap(item->item.getName(), item->item));
        Order order = orderRepository.persist(createOrder(input, map));
        return new OutputValues(order);
    }

    private Order createOrder(InputValues input, Map<String, Participator> map){
        return new Order(
                null,
                input.getName(),
                createOrderDetail(input.getExpenditures(), map),
                createOrderDetail(input.getDetails(), map),
                LocalDateTime.now(),
                input.getActivityId()
        );
    }

    private List<OrderDetail> createOrderDetail(List<InputItem> items, Map<String, Participator> map){
        List<OrderDetail> details = new ArrayList<>();
        for(InputItem item : items){
            if(map.get(item.getOwner()) == null)
                throw new NotInParticipatorException("%s not in participator list", item.getOwner());
            OrderDetail detail = new OrderDetail(
                    null,
                    item.getName(),
                    new MoneyAndOwner(item.getOwner(), item.getAmount())
            );
            details.add(detail);
        }
        return details;
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final String name;
        private final List<InputItem> expenditures;
        private final List<InputItem> details;
        private final Long activityId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues{
        private final Order order;
    }

    @Value
    public static class InputItem{
        private final String name;
        private final String owner;
        private final Integer amount;
    }

}
