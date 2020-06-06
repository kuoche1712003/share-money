package io.kuoche.share.data.db.jpa.entity.pk;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ParticipatorDataPk implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long activityId;
}
