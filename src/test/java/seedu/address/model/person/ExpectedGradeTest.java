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
        String invalidAddress = " ";
        assertThrows(IllegalArgumentException.class, () -> new ExpectedGrade(invalidAddress));
    }

    @Test
    public void isvalidExpectedGrade() {
        // null address
        assertThrows(NullPointerException.class, () -> ExpectedGrade.isValidExpectedGrade(null));

        // invalid addresses
        assertFalse(ExpectedGrade.isValidExpectedGrade(" ")); // No whitespaces allowed

        // valid addresses
        assertTrue(ExpectedGrade.isValidExpectedGrade("")); // Treated as none
        assertTrue(ExpectedGrade.isValidExpectedGrade("A")); // one character
        assertTrue(ExpectedGrade.isValidExpectedGrade("Grade 5"));
    }

    @Test
    public void equals() {
        ExpectedGrade expectedGrade = new ExpectedGrade("Valid Expected Grade");

        // same values -> returns true
        assertTrue(expectedGrade.equals(new Address("Valid Expected Grade")));

        // same object -> returns true
        assertTrue(expectedGrade.equals(address));

        // null -> returns false
        assertFalse(expectedGrade.equals(null));

        // different types -> returns false
        assertFalse(expectedGrade.equals(5.0f));

        // different values -> returns false
        assertFalse(expectedGrade.equals(new ExpectedGrade("Other Valid Expected Grade")));
    }
}
