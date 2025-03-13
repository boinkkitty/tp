package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CurrentGradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentGrade(null));
    }

    @Test
    public void constructor_invalidCurrentGrade_throwsIllegalArgumentException() {
        String invalidGrade = "b";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidGrade));
    }

    @Test
    public void isValidPhone() {
        // null currentGrade number
        assertThrows(NullPointerException.class, () -> CurrentGrade.isValidCurrentGrade(null));

        // invalid currentGrade numbers
        assertFalse(CurrentGrade.isValidCurrentGrade("")); // empty string
        assertFalse(CurrentGrade.isValidCurrentGrade(" ")); // spaces only
        assertFalse(CurrentGrade.isValidCurrentGrade("b")); // non-capitalized letter
        assertFalse(CurrentGrade.isValidCurrentGrade("B%")); // wrong symbol
        assertFalse(CurrentGrade.isValidCurrentGrade("Z")); // wrong letter
        assertFalse(CurrentGrade.isValidCurrentGrade("h%")); // non-capitalized, wrong symbol, wrong letter

        // valid currentGrade numbers
        assertTrue(CurrentGrade.isValidCurrentGrade("B")); // correct grade
        assertTrue(CurrentGrade.isValidCurrentGrade("B+")); // correct symbol
    }

    @Test
    public void equals() {
        CurrentGrade currentGrade = new CurrentGrade("B");

        // same values -> returns true
        assertTrue(currentGrade.equals(new CurrentGrade("B")));

        // same object -> returns true
        assertTrue(currentGrade.equals(currentGrade));

        // null -> returns false
        assertFalse(currentGrade.equals(null));

        // different values -> returns false
        assertFalse(currentGrade.equals(new CurrentGrade("A")));
    }
}
