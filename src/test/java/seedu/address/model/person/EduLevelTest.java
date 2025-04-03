package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.Assert;

/**
 * Contains unit tests for {@code EduLevel}.
 */
public class EduLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EduLevel(null));
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
    public void isValidLength() {
        // Empty edu level
        assertTrue(EduLevel.isValidLength(""));

        // Valid edu level
        assertTrue(EduLevel.isValidLength("Primary"));

        // 30 characters - valid as within limit
        assertTrue(EduLevel.isValidLength("abcdefghijklmnopqrstabcdefghij"));

        // 31 characters - invalid as exceeds limit
        assertFalse(EduLevel.isValidLength("abcdefghijklmnopqrstabcdefghijK"));

        // Super long string - meant to fail
        assertFalse(EduLevel.isValidLength(
                "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass. "
                        + "Absurdly long string that is meant to fail and it should fail and only fail and not pass."));
    }

    @Test
    public void isValidEduLevel() {
        // null edu level
        Assert.assertThrows(NullPointerException.class, () -> CurrentYear.isValidCurrentYear(null));

        // invalid edu level
        assertFalse(EduLevel.isValidEduLevel("^")); // only non-alphanumeric characters
        assertFalse(EduLevel.isValidEduLevel("Primary*")); // contains non-alphanumeric characters
        assertFalse(EduLevel.isValidEduLevel("abcdefghijklmnopqrstabcdefghijK")); // exceeds limit

        // valid edu level
        assertTrue(EduLevel.isValidEduLevel("")); // empty string
        assertTrue(EduLevel.isValidEduLevel(" ")); // spaces only
        assertTrue(EduLevel.isValidEduLevel("10")); // numbers only
        assertTrue(EduLevel.isValidEduLevel("Primary 10")); // alphanumeric characters
        assertTrue(EduLevel.isValidEduLevel("Bachelor Degree")); // with spaces
        assertTrue(EduLevel.isValidEduLevel("abcdefghijklmnopqrstabcdefghij")); // max limit
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
