package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code EduLevel}.
 */
public class EduLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EduLevel(null));
    }

    @Test
    public void constructor_invalidEduLevel_throwsIllegalArgumentException() {
        String invalidEduLevel = "NOT_VALID";
        assertThrows(IllegalArgumentException.class, () -> new EduLevel(invalidEduLevel));
    }

    @Test
    public void constructor_validEduLevel_success() {
        // Empty string is allowed.
        assertDoesNotThrow(() -> new EduLevel(""));

        // Valid levels.
        assertDoesNotThrow(() -> new EduLevel("Primary"));
        assertDoesNotThrow(() -> new EduLevel("Secondary"));
        assertDoesNotThrow(() -> new EduLevel("Diploma"));
        assertDoesNotThrow(() -> new EduLevel("Bachelor"));
        assertDoesNotThrow(() -> new EduLevel("Master"));
        assertDoesNotThrow(() -> new EduLevel("PhD"));
    }

    @Test
    public void isValidEduLevel() {
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> EduLevel.isValidEduLevel(null));

        // invalid eduLevel
        assertFalse(EduLevel.isValidEduLevel("asdf"));
        assertFalse(EduLevel.isValidEduLevel("Primmmary"));
        assertFalse(EduLevel.isValidEduLevel("123"));

        // valid eduLevel
        assertTrue(EduLevel.isValidEduLevel("")); // empty allowed
        assertTrue(EduLevel.isValidEduLevel("Primary"));
        assertTrue(EduLevel.isValidEduLevel("secondary")); // ignoring case
        assertTrue(EduLevel.isValidEduLevel("PhD"));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        EduLevel first = new EduLevel("Bachelor");
        EduLevel second = new EduLevel("bachelor");
        // equalsIgnoreCase in the class
        assertTrue(first.equals(second));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        EduLevel first = new EduLevel("Bachelor");
        EduLevel second = new EduLevel("Master");
        assertFalse(first.equals(second));
    }

    @Test
    public void toString_validOutput() {
        EduLevel eduLevel = new EduLevel("PhD");
        assertEquals("PhD", eduLevel.toString());
    }
}
