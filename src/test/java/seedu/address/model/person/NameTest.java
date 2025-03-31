package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidLength() {
        // Invalid name but valid length - cannot be empty
        assertTrue(Name.isValidLength(""));

        // Valid name
        assertTrue(Name.isValidLength("Stephanie Tan Tik Tok"));

        // 85 characters - Valid as within limit
        assertTrue(Name.isValidLength("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrst"
                + "abcdefghijklmnopqrstabcde"));

        // 86 characters - Invalid as exceeds limit
        assertFalse(Name.isValidLength("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrst"
                + "abcdefghijklmnopqrstabcdeF"));

        // Super long string - Invalid as exceeds limit
        assertFalse(Name.isValidLength(
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
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrst"
                + "abcdefghijklmnopqrstabcdeF")); // exceeds limit

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklmnopqrst"
                + "abcdefghijklmnopqrstabcde")); // at max limit
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
