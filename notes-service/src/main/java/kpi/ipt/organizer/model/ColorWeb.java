package kpi.ipt.organizer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorWeb {

    private int value;

    public ColorWeb(int r, int g, int b) {
        this.value = (r << 16) | (g << 8) | b;
    }

    private int getRed() {
        return (value >> 16) & 0xFF;
    }

    private int getGreen() {
        return (value >> 8) & 0xFF;
    }

    private int getBlue() {
        return value & 0xFF;
    }

    public String toWebString() {
        // @formatter:off
        return "#" + twoByteHex(getRed())
                   + twoByteHex(getGreen())
                   + twoByteHex(getBlue());
        // @formatter:on
    }

    private static String twoByteHex(int value) {
        String hex = Integer.toHexString(value);
        return value < 16 ? "0" + hex : hex;
    }
}
