package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpectedGradeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpectedGrade(null));
    }

    @Test
    public void constructor_invalidExpectedGrade_throwsIllegalArgumentException() {
        String invalidExpectedGrade = " ";
        assertThrows(IllegalArgumentException.class, () -> new ExpectedGrade(invalidExpectedGrade));
    }

    @Test
    public void isvalidExpectedGrade() {
        // null address
        assertThrows(NullPointerException.class, () -> ExpectedGrade.isValidExpectedGrade(null));

        // invalid addresses
        assertFalse(ExpectedGrade.isValidExpectedGrade(" ")); // No whitespaces allowed
        assertFalse(ExpectedGrade.isValidExpectedGrade("a")); // No small letters
        assertFalse(ExpectedGrade.isValidExpectedGrade("G")); // Only A - F
        assertFalse(ExpectedGrade.isValidExpectedGrade("AB")); // Only 1 letter at most

        // valid addresses
        assertTrue(ExpectedGrade.isValidExpectedGrade("")); // Treated as none
        assertTrue(ExpectedGrade.isValidExpectedGrade("A")); // one character
        assertTrue(ExpectedGrade.isValidExpectedGrade("B+")); // Upper alphabet with sign
    }

    @Test
    public void equals() {
        ExpectedGrade expectedGrade = new ExpectedGrade("A");

        // same values -> returns true
        assertTrue(expectedGrade.equals(new ExpectedGrade("A")));

        // same object -> returns true
        assertTrue(expectedGrade.equals(expectedGrade));

        // null -> returns false
        assertFalse(expectedGrade.equals(null));

        // different types -> returns false
        assertFalse(expectedGrade.equals(5.0f));

        // different values -> returns false
        assertFalse(expectedGrade.equals(new ExpectedGrade("B")));
    }
}
