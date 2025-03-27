package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.ColorUtil.getGradeHexColor;
import static seedu.address.commons.util.ColorUtil.isLightColor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.PaymentInfo;

public class ColorUtilTest {
    @Test
    public void isLightColor_whiteHexCode_returnTrue() {
        assertTrue(isLightColor("FFFFFF"));
    }

    @Test
    public void isLightColor_blackHexCode_returnFalse() {
        assertFalse(isLightColor("000000"));
    }

    @Test
    public void isLightColor_invalidHexCode_returnTrue() {
        assertTrue(isLightColor("INVALID_HEX_COLOR"));
    }

    @Test
    public void getHexColor() {
        assertEquals(getGradeHexColor("A"), "#4CAF50");
        assertEquals(getGradeHexColor("B"), "#6CAA00");
        assertEquals(getGradeHexColor("C"), "#BEB005");
        assertEquals(getGradeHexColor("D"), "#F57C00");
        assertEquals(getGradeHexColor("E"), "#FF7043");
        assertEquals(getGradeHexColor("F"), "#FF5252");
        assertEquals(getGradeHexColor(""), "#FFFFFF");
    }

    @Test
    public void getPaymentInfoColor_paidStatus_green() {
        PaymentInfo paidInfo = new PaymentInfo.Builder()
                .setPaymentFee(100)
                .setPaymentStatus("paid")
                .setPaymentDate("01-01-2020") // Irrelevant when paid
                .build();

        assertEquals(ColorUtil.GREEN_COLOR_HEX, ColorUtil.getPaymentInfoColor(paidInfo));
    }

    @Test
    public void getPaymentInfoColor_waitingNoDate_yellow() {
        PaymentInfo waitingNoDate = new PaymentInfo.Builder()
                .setPaymentFee(100)
                .setPaymentStatus("waiting")
                .build();

        assertEquals(ColorUtil.YELLOW_COLOR_HEX, ColorUtil.getPaymentInfoColor(waitingNoDate));
    }

    @Test
    public void getPaymentInfoColor_waitingOverdue_red() {
        String pastDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PaymentInfo overdue = new PaymentInfo.Builder()
                .setPaymentFee(100)
                .setPaymentStatus("waiting")
                .setPaymentDate(pastDate)
                .build();

        assertEquals(ColorUtil.RED_COLOR_HEX, ColorUtil.getPaymentInfoColor(overdue));
    }

    @Test
    public void getPaymentInfoColor_waitingDueSoon_yellow() {
        String nearFutureDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PaymentInfo dueSoon = new PaymentInfo.Builder()
                .setPaymentFee(100)
                .setPaymentStatus("waiting")
                .setPaymentDate(nearFutureDate)
                .build();

        assertEquals(ColorUtil.YELLOW_COLOR_HEX, ColorUtil.getPaymentInfoColor(dueSoon));
    }

    @Test
    public void getPaymentInfoColor_waitingDueLater_green() {
        String futureDate = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PaymentInfo dueLater = new PaymentInfo.Builder()
                .setPaymentFee(100)
                .setPaymentStatus("waiting")
                .setPaymentDate(futureDate)
                .build();

        assertEquals(ColorUtil.GREEN_COLOR_HEX, ColorUtil.getPaymentInfoColor(dueLater));
    }
}
