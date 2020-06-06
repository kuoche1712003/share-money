package io.kuoche.share.core.usercase.order;

import io.kuoche.share.core.domain.Participator;

import java.util.List;

public interface ParticipatorRepository {
    List<Participator> getActivityParticipators(Long activityId);
}
