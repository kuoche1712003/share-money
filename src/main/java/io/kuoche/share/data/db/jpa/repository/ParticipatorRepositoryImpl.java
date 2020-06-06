package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.core.domain.Participator;
import io.kuoche.share.core.usercase.order.ParticipatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ParticipatorRepositoryImpl implements ParticipatorRepository {
    private final JpaParticipatorRepository participatorRepository;

    @Override
    public List<Participator> getActivityParticipators(Long activityId) {
        return participatorRepository.findAllByActivityId(activityId).stream()
                .map(item->new Participator(item.getName()))
                .collect(Collectors.toList());
    }
}
