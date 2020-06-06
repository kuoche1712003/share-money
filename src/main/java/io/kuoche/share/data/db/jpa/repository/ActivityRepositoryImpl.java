package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.core.domain.Activity;
import io.kuoche.share.core.usercase.activity.ActivityRepository;
import io.kuoche.share.data.db.jpa.entity.ActivityData;
import io.kuoche.share.data.db.jpa.entity.ParticipatorData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepository {
    private final JpaActivityRepository activityRepository;
    private final JpaParticipatorRepository participatorRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Activity persist(Activity activity) {
        ActivityData activityData = activityRepository.save(ActivityData.from(activity));
        List<ParticipatorData> participatorData = activity.getParticipators().stream()
                .map(item->new ParticipatorData(item.getName(), activityData.getId()))
                .collect(Collectors.toList());
        participatorData = participatorRepository.saveAll(participatorData);
        return activityData.from(participatorData);
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        Optional<ActivityData> activityData = activityRepository.findById(activityId);
        if(activityData.isPresent()){
            List<ParticipatorData> participatorData = participatorRepository.findAllByActivityId(activityId);
            return Optional.of(activityData.get().from(participatorData));
        }else{
            return Optional.empty();
        }
    }

}
