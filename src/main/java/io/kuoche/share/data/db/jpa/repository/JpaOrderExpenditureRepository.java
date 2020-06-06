package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.data.db.jpa.entity.OrderExpenditureData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderExpenditureRepository extends JpaRepository<OrderExpenditureData, Long> {
    List<OrderExpenditureData> findAllByActivityId(Long activityId);
}
