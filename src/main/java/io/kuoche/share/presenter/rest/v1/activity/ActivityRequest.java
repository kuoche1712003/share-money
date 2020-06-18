package io.kuoche.share.presenter.rest.v1.activity;

import io.kuoche.share.core.usercase.activity.CreateActivityUseCase;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class ActivityRequest {
    @NotNull
    private final String name;

    @NotNull
    @NotEmpty
    private final List<String> participators;

    public CreateActivityUseCase.InputValues fromThis(){
        return new CreateActivityUseCase.InputValues(
                name,
                participators
        );
    }
}
