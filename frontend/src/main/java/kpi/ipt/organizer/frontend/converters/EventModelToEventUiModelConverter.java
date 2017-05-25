package kpi.ipt.organizer.frontend.converters;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import kpi.ipt.organizer.frontend.model.ui.events.EventUiModel;
import org.springframework.core.convert.converter.Converter;

public class EventModelToEventUiModelConverter implements Converter<Event, EventUiModel> {

    @Override
    public EventUiModel convert(Event source) {
        return new EventUiModel(
                source.getId(),
                source.getTitle(),
                source.getStart().getTime(),
                ColorUtils.toWebString(source.getColor())
        );
    }
}
