package io.kuoche.share.core.usercase.activity;

import io.kuoche.share.core.domain.Activity;
import io.kuoche.share.core.domain.Participator;
import io.kuoche.share.core.usercase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateActivityUseCase extends UseCase<CreateActivityUseCase.InputValues, CreateActivityUseCase.OutputValues> {
    private final ActivityRepository activityRepository;

    @Override
    public OutputValues execute(InputValues input) {
        List<Participator> participators = new ArrayList<>();
        if(input.getParticipators() != null){
            participators = input.getParticipators().stream()
                    .map(item->new Participator(item))
                    .distinct()
                    .collect(Collectors.toList());
        }

        Activity activity = new Activity(
                null,
                input.getName(),
                participators,
                LocalDateTime.now());
        return new OutputValues(activityRepository.persist(activity));
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        private final String name;
        private final List<String> participators;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Activity activity;
    }
}
