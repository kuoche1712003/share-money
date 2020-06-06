package io.kuoche.share.data.db.jpa.entity;

import io.kuoche.share.core.domain.Participator;
import io.kuoche.share.data.db.jpa.entity.pk.ParticipatorDataPk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "participator")
@IdClass(ParticipatorDataPk.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatorData {
    @Id
    private String name;
    @Id
    private Long activityId;

}
