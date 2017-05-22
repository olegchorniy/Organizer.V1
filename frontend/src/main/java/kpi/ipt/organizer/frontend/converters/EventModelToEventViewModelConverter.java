package kpi.ipt.organizer.frontend.converters;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.model.rest.events.EventModel;
import kpi.ipt.organizer.frontend.model.ui.EventViewModel;
import org.springframework.core.convert.converter.Converter;

public class EventModelToEventViewModelConverter implements Converter<EventModel, EventViewModel> {

    @Override
    public EventViewModel convert(EventModel source) {
        return new EventViewModel(
                source.getId(),
                source.getTitle(),
                source.getStart().toString(),
                source.getEnd().toString(),
                ColorUtils.toWebString(source.getColor())
        );
    }
}
