package io.kuoche.share.core.usercase.order;

import io.kuoche.share.core.domain.Order;

import java.util.List;


public interface OrderRepository {
    Order persist(Order order);
    List<Order> findByActivityId(Long activity);
}
