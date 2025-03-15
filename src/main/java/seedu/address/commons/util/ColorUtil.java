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
}
