package io.kuoche.share.core.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class Order {
    private Long id;
    private String name;
    private List<OrderDetail> expenditures;
    private List<OrderDetail> details;
    private LocalDateTime startTime;
    private Long activityId;

    public Order(Long id,
                 String name,
                 List<OrderDetail> expenditures,
                 List<OrderDetail> details,
                 LocalDateTime startTime,
                 Long activityId){

        this.id = id;
        this.name = name;
        this.expenditures = expenditures;
        this.details =details;
        this.startTime = startTime;
        this.activityId = activityId;

        if(getDetailAmount() != getExpenditureAmount())
            throw new OrderException("expenditure amount and detail amount not equal");
    }

    public int getExpenditureAmount(){
        return expenditures.stream().mapToInt(item->item.getMoney().getAmount()).sum();
    }

    public int getDetailAmount(){
        return details.stream().mapToInt(item->item.getMoney().getAmount()).sum();
    }

    public List<Debt> getDebts(){
        Map<String, Debt> debtMap = new HashMap<>();
        toDebts().stream().filter(item->!item.getCreditor().equals(item.getDebtor())).
                forEach(debt -> {
                    String key = debt.getDebtor() + ":" + debt.getCreditor();
                    String counterKey = debt.getCreditor() + ":" + debt.getDebtor();
                    Debt counterDebt = debtMap.remove(counterKey);
                    if(counterDebt != null){
                        Debt cal = debt.calculateDebt(counterDebt);
                        String calKey = cal.getDebtor() + ":" + cal.getCreditor();
                        if(cal != null)
                            debtMap.put(calKey, cal);
                    }else{
                        debtMap.put(key, debt);
                    }
                });
        return new ArrayList<>(debtMap.values());
    }



    private List<Debt> toDebts(){
        Stack<MoneyAndOwner> stack = new Stack<>();

        stack.addAll(
                expenditures.stream().map(item->item.getMoney())
                        .collect(Collectors.toList())
        );

        List<Debt> debts = new ArrayList<>();

        for(OrderDetail detail : details){
            int needPay = detail.getMoney().getAmount();
            while(needPay > 0){
                MoneyAndOwner target = stack.pop();
                int needGet = target.getAmount();
                if(needPay > needGet){
                    needPay = needPay - needGet;
                    Debt debt = new Debt(detail.getMoney().getOwner(), target.getOwner(), needGet);
                    debts.add(debt);
                    needGet = 0;
                }else{
                    needGet = needGet - needPay;
                    Debt debt = new Debt(detail.getMoney().getOwner(), target.getOwner(), needPay);
                    debts.add(debt);
                    needPay = 0;
                }
                if(needGet > 0){
                    target = new MoneyAndOwner(target.getOwner(), needGet);
                    stack.push(target);
                }
            }
        }
        return debts;
    }


}
