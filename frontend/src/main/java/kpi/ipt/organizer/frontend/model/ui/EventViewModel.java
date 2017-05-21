package kpi.ipt.organizer.frontend.model.ui;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventViewModel {

    private String id;
    private String title;
    private String start;
    private String end;
    private String color;
}
