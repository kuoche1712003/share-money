package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.data.db.jpa.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderData, Long> {
    List<OrderData> findAllByActivityId(Long activityId);
}
