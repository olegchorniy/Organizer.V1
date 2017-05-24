package kpi.ipt.organizer.color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ColorUtils {

    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb" +
            "\\(" +
            "\\s*(\\d+)\\s*," +
            "\\s*(\\d+)\\s*," +
            "\\s*(\\d+)\\s*" +
            "\\)$"
    );

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

    public static Color parseRgb(String rgb) {
        Matcher colorMatcher = RGB_PATTERN.matcher(rgb);
        if (!colorMatcher.find()) {
            throw new IllegalArgumentException("Illegal format: " + rgb);
        }

        int r = Integer.parseInt(colorMatcher.group(1));
        int g = Integer.parseInt(colorMatcher.group(2));
        int b = Integer.parseInt(colorMatcher.group(3));

        return fromRgb(r, g, b);
    }

    public static Color fromRgb(int r, int g, int b) {
        return new Color((r << 16) | (g << 8) | b);
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
