package io.kuoche.share.presenter.rest.v1.activity;

import io.kuoche.share.core.usercase.activity.CreateActivityUseCase;
import lombok.Value;

import java.util.List;

@Value
public class ActivityRequest {
    private final String name;
    private final List<String> participators;

    public CreateActivityUseCase.InputValues fromThis(){
        return new CreateActivityUseCase.InputValues(
                name,
                participators
        );
    }
}
