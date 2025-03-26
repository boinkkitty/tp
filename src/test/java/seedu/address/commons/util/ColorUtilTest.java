package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.ColorUtil.getGradeHexColor;
import static seedu.address.commons.util.ColorUtil.isLightColor;

import org.junit.jupiter.api.Test;

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
}
