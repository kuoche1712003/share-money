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
        Map<String, Debt> debtMap = new LinkedHashMap<>();
        toDebts().stream().filter(item->!item.getCreditor().equals(item.getDebtor())).
                forEach(debt -> {
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
        return new ArrayList<>(debtMap.values());
    }



    private List<Debt> toDebts(){
        Map<String,MoneyAndOwner> map =  expenditures.stream()
                .map(item->item.getMoney())
                .collect(Collectors.toMap(item->item.getOwner(), item->item));

        List<Debt> debts = new ArrayList<>();

        for(OrderDetail detail : details){
            int needPay = detail.getMoney().getAmount();
            while(needPay > 0){

                String key = detail.getMoney().getOwner();
                if(map.get(key) == null)
                    key = map.keySet().stream().findFirst().get();

                MoneyAndOwner target = map.remove(key);

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
                    map.put(target.getOwner() ,target);
                }
            }
        }
        return debts;
    }


}
