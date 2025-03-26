package seedu.address.commons.util;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import seedu.address.model.person.PaymentInfo;

/**
 * Helper functions for handling color.
 */
public class ColorUtil {
    public static final String RED_COLOR_HEX = "#EC736E";
    public static final String YELLOW_COLOR_HEX = "#E8D588";
    public static final String GREEN_COLOR_HEX = "#6DF162";

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
            return GREEN_COLOR_HEX;
        case "B":
            return "#6CAA00";
        case "C":
            return "#BEB005";
        case "D":
            return YELLOW_COLOR_HEX;
        case "E":
            return "#FF7043";
        case "F":
            return RED_COLOR_HEX;
        default:
            return "#FFFFFF";
        }
    }

    /**
     * Returns a color code for a {@code PaymentInfo} object.
     * This helper function assumes that either {@code paymentDate} or {@code paymentStatus} are non-empty.
     *
     * @param info The {@code PaymentInfo} object to evaluate.
     * @return The corresponding hex color code as a {@code String}.
     */
    public static String getPaymentInfoColor(PaymentInfo info) {
        String status = info.getPaymentStatus();
        String date = info.getPaymentDate();

        if (status.equalsIgnoreCase("paid")) {
            return GREEN_COLOR_HEX;
        }

        if (date.isEmpty()) {
            return YELLOW_COLOR_HEX; // waiting but no due date
        }

        LocalDate today = LocalDate.now();
        LocalDate dueDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        long daysUntilDue = ChronoUnit.DAYS.between(today, dueDate);

        if (daysUntilDue < 0) {
            return RED_COLOR_HEX; // overdue
        } else if (daysUntilDue <= 7) {
            return YELLOW_COLOR_HEX; // payment due within 7 days
        } else {
            return GREEN_COLOR_HEX; // payment due later
        }
    }
}
