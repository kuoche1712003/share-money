package io.kuoche.share.presenter.rest.v1.debt;

import io.kuoche.share.core.domain.Debt;
import io.kuoche.share.core.usercase.order.GetActivityDebtUseCase;
import io.kuoche.share.presenter.rest.v1.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/debt")
@RequiredArgsConstructor
public class DebtController {
    private final GetActivityDebtUseCase getActivityDebtUseCase;


    @GetMapping("")
    public Response<List<DebtResponse>> getActivityDebts(@RequestParam Long activityId){
        List<Debt> debts = getActivityDebtUseCase.execute(new GetActivityDebtUseCase.InputValues(activityId)).getDebts();
        List<DebtResponse> response = debts.stream().map(item->new DebtResponse(
                item.getDebtor(),
                item.getCreditor(),
                item.getAmount()
        )).collect(Collectors.toList());
        return Response.ok(response);
    }
}
