package io.kuoche.share.data.db.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_expenditure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderExpenditureData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner;
    private Integer amount;
    private Long activityId;
    private Long orderId;
}
