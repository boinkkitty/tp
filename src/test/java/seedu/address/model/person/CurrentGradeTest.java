package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentGradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentGrade(null));
    }

    @Test
    public void constructor_invalidCurrentGrade_throwsIllegalArgumentException() {
        String invalidGrade = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidGrade));
    }

    @Test
    public void isValidCurrentGrade() {
        // null currentGrade number
        assertThrows(NullPointerException.class, () -> CurrentGrade.isValidCurrentGrade(null));

        // invalid currentGrade numbers
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
    public void getCurrentGradeLetter() {
        CurrentGrade currentGrade1 = new CurrentGrade("B+");
        CurrentGrade currentGrade2 = new CurrentGrade("");

        //returns right current grade letters
        assertEquals(currentGrade1.getCurrentGradeLetter(currentGrade1.value), "B");
        assertEquals(currentGrade2.getCurrentGradeLetter(currentGrade2.value), "");
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
        assertFalse(currentGrade.equals(new CurrentGrade("D")));
    }
}
