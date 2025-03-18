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
        assertEquals(getGradeHexColor("A"), "#6DF162");
        assertEquals(getGradeHexColor("B"), "#D2F088");
        assertEquals(getGradeHexColor("C"), "#E1E888");
        assertEquals(getGradeHexColor("D"), "#E8D588");
        assertEquals(getGradeHexColor("E"), "#EE9C59");
        assertEquals(getGradeHexColor("F"), "#EC736E");
        assertEquals(getGradeHexColor(""), "#FFFFFF");
    }
}
