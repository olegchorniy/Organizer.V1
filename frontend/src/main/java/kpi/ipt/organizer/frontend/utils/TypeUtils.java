package kpi.ipt.organizer.frontend.utils;

import org.springframework.core.convert.TypeDescriptor;

public abstract class TypeUtils {
    private TypeUtils() {
    }

    public static TypeDescriptor collection(Class<?> collectionClass, Class<?> itemsClass) {
        return TypeDescriptor.collection(collectionClass, TypeDescriptor.valueOf(itemsClass));
    }
}
