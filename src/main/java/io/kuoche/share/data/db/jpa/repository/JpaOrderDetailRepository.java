package io.kuoche.share.data.db.jpa.repository;

import io.kuoche.share.data.db.jpa.entity.OrderDetailData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderDetailRepository extends JpaRepository<OrderDetailData, Long> {
    List<OrderDetailData> findAllByActivityId(Long activityId);
}
