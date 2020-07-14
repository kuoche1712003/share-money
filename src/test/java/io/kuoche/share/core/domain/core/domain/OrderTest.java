package io.kuoche.share.core.domain.core.domain;

import io.kuoche.share.core.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrderException(){
        List<OrderDetail> expenditures = new ArrayList<>();
        expenditures.add(new OrderDetail(1L,"test", new MoneyAndOwner("Marry", 1000)));

        List<OrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(1L, "test", new MoneyAndOwner("Marry", 500)));
        details.add(new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 500)));
        details.add(new OrderDetail(3L, "test", new MoneyAndOwner("Andy", 500)));

        LocalDateTime now = LocalDateTime.now();

        assertThrows(OrderException.class, ()->{
            new Order(
                    1L,
                    "test",
                    expenditures,
                    details,
                    now,
                    1L
            );
        });
    }

    @Test
    public void testGetExpenditureAmount(){
        List<OrderDetail> expenditures = new ArrayList<>();
        expenditures.add(new OrderDetail(1L,"test", new MoneyAndOwner("Marry", 1000)));

        List<OrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(1L, "test", new MoneyAndOwner("Marry", 500)));
        details.add(new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 500)));

        LocalDateTime now = LocalDateTime.now();

        Order order = new Order(
                1L,
                "test",
                expenditures,
                details,
                now,
                1L
        );

        int amount = order.getExpenditureAmount();

        assertEquals(1000, amount);
    }

    @Test
    public void testGetDetailAmount(){
        List<OrderDetail> expenditures = new ArrayList<>();
        expenditures.add(new OrderDetail(1L,"test", new MoneyAndOwner("Marry", 1000)));

        List<OrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(1L, "test", new MoneyAndOwner("Marry", 500)));
        details.add(new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 500)));

        LocalDateTime now = LocalDateTime.now();

        Order order = new Order(
                1L,
                "test",
                expenditures,
                details,
                now,
                1L
        );

        int amount = order.getDetailAmount();

        assertEquals(1000, amount);
    }

    @Test
    public void testGetDebt(){
        List<OrderDetail> expenditures = new ArrayList<>();
        expenditures.add(new OrderDetail(1L,"test", new MoneyAndOwner("Marry", 1000)));
        expenditures.add(new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 200)));

        List<OrderDetail> details = new ArrayList<>();
        details.add(new OrderDetail(1L, "test", new MoneyAndOwner("Marry", 400)));
        details.add(new OrderDetail(2L, "test", new MoneyAndOwner("Andy", 400)));
        details.add(new OrderDetail(3L, "test", new MoneyAndOwner("Mark", 400)));

        LocalDateTime now = LocalDateTime.now();

        Order order = new Order(
                1L,
                "test",
                expenditures,
                details,
                now,
                1L
        );

        List<Debt> expectedDebts = new ArrayList<>();
        expectedDebts.add(new Debt("Andy", "Marry", 200));
        expectedDebts.add(new Debt("Mark", "Marry", 400));

        List<Debt> debts = order.getDebts();

        assertIterableEquals(expectedDebts, debts);

    }

}
