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
import java.util.stream.Collectors;

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
                    Debt targetDebt = debtMap.remove(key);
                    Debt counterDebt = debtMap.remove(counterKey);
                    Debt cal = debt;
                    if(targetDebt != null)
                        cal = new Debt(targetDebt.getDebtor(), targetDebt.getCreditor(), debt.getAmount() + targetDebt.getAmount());
                    if(counterDebt != null)
                        cal = cal.calculateDebt(counterDebt);
                    if(cal != null){
                        String calKey =  cal.getDebtor() + ":" + cal.getCreditor();
                        debtMap.put(calKey, cal);
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
