package io.kuoche.share.core.domain.core.domain;

import io.kuoche.share.core.domain.MoneyAndOwner;
import io.kuoche.share.core.domain.MoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyAndOwnerTest {

    @Test
    public void testConstructor(){
        MoneyAndOwner moneyAndOwner = new MoneyAndOwner("Mary", 100);
        Assertions.assertEquals("Mary", moneyAndOwner.getOwner());
        Assertions.assertEquals(100, moneyAndOwner.getAmount());

        Assertions.assertThrows(MoneyException.class, ()->{
           new MoneyAndOwner("Mary", -12);
        });
    }

}
