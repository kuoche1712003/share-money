package io.kuoche.share.core.usercase.order;

import io.kuoche.share.core.domain.Debt;
import io.kuoche.share.core.domain.Order;
import io.kuoche.share.core.usercase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GetActivityDebtUseCase extends UseCase<GetActivityDebtUseCase.InputValues, GetActivityDebtUseCase.OutputValues>{
    private final GetActivityOrdersUseCase getActivityOrdersUseCase;

    @Override
    public OutputValues execute(InputValues input) {
        List<Order> orders = getActivityOrdersUseCase.execute(new GetActivityOrdersUseCase.InputValues(input.getActivityId())).getOrders();
        Map<String, Debt> debtMap = new HashMap<>();
        orders.stream().map(item->item.getDebts())
                .flatMap(item->item.stream())
                .forEach(debt -> {
                    String key = debt.getDebtor() + ":" + debt.getCreditor();
                    String counterKey = debt.getCreditor() + ":" + debt.getDebtor();
                    Debt counterDebt = debtMap.remove(counterKey);
                    if(counterDebt != null){
                        Debt cal = debt.calculateDebt(counterDebt);
                        if(cal != null){
                            String calKey = cal.getDebtor() + ":" + cal.getCreditor();
                            debtMap.put(calKey, cal);
                        }
                    }else{
                        debtMap.put(key, debt);
                    }
                });
        return new OutputValues(new ArrayList<>(debtMap.values()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final Long activityId;
    }
    @Value
    public static class OutputValues implements UseCase.OutputValues{
        private List<Debt> debts;
    }
}
