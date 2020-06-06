package io.kuoche.share.presenter.rest.v1.activity;

import io.kuoche.share.core.domain.Activity;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ActivityResponse {
    private final Long activityId;
    private String name;
    private List<String> participators;
    private LocalDateTime startTime;


    public static ActivityResponse from(Activity activity){
        return new ActivityResponse(
                activity.getId(),
                activity.getName(),
                activity.getParticipators().stream()
                        .map(item->item.getName())
                        .collect(Collectors.toList()),
                activity.getStartTime()
        );
    }
}
