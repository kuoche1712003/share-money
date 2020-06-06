package io.kuoche.share.core.usercase.activity;

import io.kuoche.share.core.domain.Activity;
import io.kuoche.share.core.domain.NotFoundException;
import io.kuoche.share.core.usercase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class GetActivityUseCase extends UseCase<GetActivityUseCase.InputValues, GetActivityUseCase.OutputValues> {
    private final ActivityRepository activityRepository;

    @Override
    public OutputValues execute(InputValues input) {
        Activity activity = activityRepository.findById(input.getActivityId())
                .orElseThrow(() -> new NotFoundException("activity %s not found", input.getActivityId()));
        return new OutputValues(activity);
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final Long activityId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues{
        private final Activity activity;
    }
}
