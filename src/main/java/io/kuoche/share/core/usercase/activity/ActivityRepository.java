package io.kuoche.share.core.usercase.activity;

import io.kuoche.share.core.domain.Activity;

import java.util.Optional;

public interface ActivityRepository {
    Activity persist(Activity activity);
    Optional<Activity> findById(Long activityId);
}
