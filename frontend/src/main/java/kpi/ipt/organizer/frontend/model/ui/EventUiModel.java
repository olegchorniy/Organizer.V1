package kpi.ipt.organizer.frontend.model.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventUiModel extends EventCreateModel {

    private String id;

    public EventUiModel(String id, String title, long start, String color) {
        super(title, start, color);
        this.id = id;
    }
}
