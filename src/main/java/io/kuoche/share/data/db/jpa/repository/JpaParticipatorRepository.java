package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.data.db.jpa.entity.ParticipatorData;
import io.kuoche.share.data.db.jpa.entity.pk.ParticipatorDataPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaParticipatorRepository extends JpaRepository<ParticipatorData, ParticipatorDataPk> {
    List<ParticipatorData> findAllByActivityId(Long activityId);
}
