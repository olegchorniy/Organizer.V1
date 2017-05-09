package kpi.ipt.organizer.notes.utils;

import kpi.ipt.organizer.notes.model.Color;

public abstract class ColorUtils {

    private ColorUtils() {
    }

    public static int getRed(Color color) {
        return (color.getValue() >> 16) & 0xFF;
    }

    public static int getGreen(Color color) {
        return (color.getValue() >> 8) & 0xFF;
    }

    public static int getBlue(Color color) {
        return color.getValue() & 0xFF;
    }

    public static String toWebString(Color color) {
        // @formatter:off
        return "#" + twoByteHex(getRed(color))
                   + twoByteHex(getGreen(color))
                   + twoByteHex(getBlue(color));
        // @formatter:on
    }

    private static String twoByteHex(int value) {
        String hex = Integer.toHexString(value);
        return value < 16 ? "0" + hex : hex;
    }
}
