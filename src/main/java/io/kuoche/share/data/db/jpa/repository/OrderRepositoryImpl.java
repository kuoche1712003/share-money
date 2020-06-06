package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.core.domain.MoneyAndOwner;
import io.kuoche.share.core.domain.Order;
import io.kuoche.share.core.domain.OrderDetail;
import io.kuoche.share.core.usercase.order.OrderRepository;
import io.kuoche.share.data.db.jpa.entity.OrderData;
import io.kuoche.share.data.db.jpa.entity.OrderDetailData;
import io.kuoche.share.data.db.jpa.entity.OrderExpenditureData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JpaOrderRepository orderRepository;
    private final JpaOrderExpenditureRepository expenditureRepository;
    private final JpaOrderDetailRepository detailRepository;

    @Override
    public Order persist(Order order) {
        OrderData orderData = orderRepository.save(OrderData.from(order));
        List<OrderExpenditureData> expenditureData = order.getExpenditures().stream().map(item->
               new OrderExpenditureData(
                       null,
                       item.getName(),
                       item.getMoney().getOwner(),
                       item.getMoney().getAmount(),
                       orderData.getActivityId(),
                       orderData.getId()
               )
        ).collect(Collectors.toList());
        expenditureData = expenditureRepository.saveAll(expenditureData);
        List<OrderDetailData> detailData = order.getDetails().stream().map(item->
                new OrderDetailData(
                        null,
                        item.getName(),
                        item.getMoney().getOwner(),
                        item.getMoney().getAmount(),
                        orderData.getActivityId(),
                        orderData.getId()
                )
        ).collect(Collectors.toList());
        detailData = detailRepository.saveAll(detailData);
        return toOrder(orderData, expenditureData, detailData);
    }

    @Override
    public List<Order> findByActivityId(Long activityId) {
        List<OrderData> orderData = orderRepository.findAllByActivityId(activityId);
        Map<Long, List<OrderExpenditureData>> expenditureMap = expenditureRepository.findAllByActivityId(activityId)
                .stream().collect(Collectors.groupingBy(item->item.getOrderId()));
        Map<Long, List<OrderDetailData>> detailMap = detailRepository.findAllByActivityId(activityId)
                .stream().collect(Collectors.groupingBy(item->item.getOrderId()));

        List<Order> orders = new ArrayList<>();
        for(OrderData data : orderData){
            Order order = toOrder(
                    data,
                    expenditureMap.get(data.getId()) == null ?
                            new ArrayList<>() :
                            expenditureMap.get(data.getId()),
                    detailMap.get(data.getId()) == null ?
                            new ArrayList<>() :
                            detailMap.get(data.getId())
            );
            orders.add(order);
        }
        return orders;
    }

    private Order toOrder(OrderData orderData,
                          List<OrderExpenditureData> expenditureData,
                          List<OrderDetailData>detailData){
        return new Order(
                orderData.getId(),
                orderData.getName(),
                expenditureData.stream().map(item->toDetail(item)).collect(Collectors.toList()),
                detailData.stream().map(item->toDetail(item)).collect(Collectors.toList()),
                orderData.getStartTime(),
                orderData.getActivityId()
        );
    }
    private OrderDetail toDetail(OrderExpenditureData expenditureData){
        return new OrderDetail(
                expenditureData.getId(),
                expenditureData.getName(),
                new MoneyAndOwner(
                        expenditureData.getOwner(),
                        expenditureData.getAmount()
                )
        );
    }
    private OrderDetail toDetail(OrderDetailData detailData){
        return new OrderDetail(
                detailData.getId(),
                detailData.getName(),
                new MoneyAndOwner(
                        detailData.getOwner(),
                        detailData.getAmount()
                )
        );
    }

}
