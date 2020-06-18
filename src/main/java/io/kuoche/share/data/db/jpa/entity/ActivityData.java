package io.kuoche.share.data.db.jpa.entity;

import io.kuoche.share.core.domain.Activity;
import io.kuoche.share.core.domain.Participator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime startTime;

    public static ActivityData from(Activity activity){
        return new ActivityData(
                activity.getId(),
                activity.getName(),
                activity.getStartTime()
        );
    }

    public Activity from(List<ParticipatorData> participatorData){
        List<Participator> participators = participatorData.stream()
                .map(item -> new Participator(item.getName()))
                .collect(Collectors.toList());
        return new Activity(
                id,
                name,
                participators,
                startTime
        );
    }
}
