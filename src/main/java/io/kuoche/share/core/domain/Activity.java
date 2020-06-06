package io.kuoche.share.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Activity {
    private Long id;
    private String name;
    private List<Participator> participators;
    private LocalDateTime startTime;
}
