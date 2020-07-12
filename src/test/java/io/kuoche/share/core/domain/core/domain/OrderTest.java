package io.kuoche.share.core.domain.core.domain;

import io.kuoche.share.core.domain.MoneyAndOwner;
import io.kuoche.share.core.domain.Order;
import io.kuoche.share.core.domain.OrderDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

public class OrderTest {
    private Order order;

    @BeforeAll
    public void init(){
        List<OrderDetail> expenditures = Arrays.asList(new OrderDetail[]{
                new OrderDetail(1L,"test", new MoneyAndOwner("Marry", 1000))
        });
        List<OrderDetail> details = Arrays.asList(new OrderDetail[]{
                new OrderDetail(1L, "test", new MoneyAndOwner("Mary", 500)),
                new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 500))
        });
        order = new Order(
                1L,
                "test",
                expenditures,
                details,
                LocalDateTime.now(),
                1L
        );
    }

    @Test
    public void testConstructor(){

    }

}
