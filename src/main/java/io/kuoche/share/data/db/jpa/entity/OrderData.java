package io.kuoche.share.data.db.jpa.entity;

import io.kuoche.share.core.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private Long activityId;

    public static OrderData from(Order order){
        return new OrderData(
                null,
                order.getName(),
                order.getStartTime(),
                order.getActivityId()
        );
    }

}
