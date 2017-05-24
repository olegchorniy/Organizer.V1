package kpi.ipt.organizer.frontend.utils;

import org.springframework.core.convert.ConversionService;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConversionUtils {

    public static Long dateToTimestamp(Date date) {
        return (date == null) ? null : date.getTime();
    }

    public static Date timestampToDate(Long timestamp) {
        return (timestamp == null) ? null : new Date(timestamp);
    }

    public static <T, U> List<U> convert(ConversionService conversionService, List<T> sourceList, Class<U> targetClass) {
        return sourceList.stream()
                .map(converter(conversionService, targetClass))
                .collect(Collectors.toList());
    }

    public static <T, U> Function<T, U> converter(ConversionService conversionService, Class<U> targetClass) {
        return source -> conversionService.convert(source, targetClass);
    }
}
