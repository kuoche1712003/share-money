package io.kuoche.share.core.domain.core.domain;

import io.kuoche.share.core.domain.Debt;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DebtTest {
    @Test
    public void testCalculateDebt(){
        Debt target = new Debt("Mary", "Andy", 100);
        Debt other = new Debt("Andy", "Mary", 50);
        Debt ans = target.calculateDebt(other);
        assertEquals("Mary", ans.getDebtor());
        assertEquals("Andy", ans.getCreditor());
        assertEquals(50, ans.getAmount());

        target = new Debt("Mary", "Andy", 50);
        other = new Debt("Andy", "Mary", 100);
        ans = target.calculateDebt(other);
        assertEquals("Andy", ans.getDebtor());
        assertEquals("Mary", ans.getCreditor());
        assertEquals(50, ans.getAmount());

        target = new Debt("Mary", "Andy", 50);
        other = new Debt("Andy", "Mary", 50);
        ans = target.calculateDebt(other);
        assertNull(ans);
    }
}
