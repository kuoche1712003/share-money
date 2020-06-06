package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.data.db.jpa.entity.ActivityData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaActivityRepository extends JpaRepository<ActivityData, Long> {
}
