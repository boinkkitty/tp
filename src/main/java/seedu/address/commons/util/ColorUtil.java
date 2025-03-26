package seedu.address.commons.util;

import java.awt.Color;

/**
 * Helper functions for handling color.
 */
public class ColorUtil {
    /**
     * Determines if a hex color is light or dark.
     * @param hexColor The 6-character hex code (e.g., "F3B7D2").
     * @return true if the color is light, false if dark.
     */
    public static boolean isLightColor(String hexColor) {
        // [ACKNOWLEDGEMENT] Code logic adopted from here:
        // https://stackoverflow.com/a/14714716
        try {
            Color color = Color.decode("#" + hexColor);

            // Calculate luminance (perceived brightness)
            double luminance = (0.299 * color.getRed()
                    + 0.587 * color.getGreen()
                    + 0.114 * color.getBlue()) / 255;

            return luminance > 0.5; // Light if luminance > 0.5, otherwise dark
        } catch (Exception e) {
            return true; // Default to light to prevent errors
        }
    }

    /**
     * Returns the hexadecimal color code associated with a given grade. Expected valid input grades: "A", "B", "C",
     * "D", "E", or "F". If the provided grade is not recognized, the method returns "#FF0000" (red) as a default.
     *
     * @param grade the current grade {@code String } which may contain "+" / "-"
     * @return the corresponding hex color code as a {@code String}
     */
    public static String getGradeHexColor(String grade) {
        switch (grade) {
        case "A":
            return "#4CAF50";
        case "B":
            return "#6CAA00";
        case "C":
            return "#BEB005";
        case "D":
            return "#F57C00";
        case "E":
            return "#FF7043";
        case "F":
            return "#FF5252";
        default:
            return "#FFFFFF";
        }
    }
}
