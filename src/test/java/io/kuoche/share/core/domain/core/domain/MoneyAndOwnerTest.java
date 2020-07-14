package io.kuoche.share.core.domain.core.domain;

import io.kuoche.share.core.domain.MoneyAndOwner;
import io.kuoche.share.core.domain.MoneyAndOwnerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyAndOwnerTest {
    @Test
    public void testMoneyAndOwnerException(){
        assertThrows(MoneyAndOwnerException.class, ()->{
           new MoneyAndOwner("Marry", 0);
        });
    }
}
