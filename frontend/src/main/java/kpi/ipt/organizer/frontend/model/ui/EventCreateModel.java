package kpi.ipt.organizer.frontend.model.ui;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateModel {

    private String title;
    private long start;
    private String color;
}
