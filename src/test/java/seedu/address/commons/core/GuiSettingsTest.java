package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void testGuiSettings() {
        GuiSettings guiSettings = new GuiSettings();

        assertEquals(1080, guiSettings.getWindowWidth());
        assertEquals(600, guiSettings.getWindowHeight());
        assertEquals(null, guiSettings.getWindowCoordinates());
        assertEquals(true, guiSettings.getIsDarkTheme());
    }

    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + ", isDarkTheme=" + guiSettings.getIsDarkTheme() + "}";
        assertEquals(expected, guiSettings.toString());
    }
}
