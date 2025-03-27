package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void testGuiSettings() {
        GuiSettings guiSettings = new GuiSettings();

        assertEquals(guiSettings.getWindowWidth(), 740);
        assertEquals(guiSettings.getWindowHeight(), 600);
        assertEquals(guiSettings.getWindowCoordinates(), null);
        assertEquals(guiSettings.getIsDarkTheme(), true);
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
