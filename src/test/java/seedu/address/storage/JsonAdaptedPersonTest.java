package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.person.Name;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_EDULEVEL = "InvalidLevel";
    private static final String INVALID_CURRENT_YEAR = "@2023";
    private static final String INVALID_CURRENT_GRADE = "Z";
    private static final String INVALID_EXP_GRADE = "ABCD";
    private static final String INVALID_TAG = "#friend";
    private static final int INVALID_PAYMENT_FEE = -1000;
    private static final String INVALID_PAYMENT_DATE = "11-14-2000"; // `MM-dd-yyyy` instead
    private static final String INVALID_PAYMENT_STATUS = "OWING";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_EXP_GRADE = BENSON.getExpectedGrade().toString();
    private static final String VALID_CURRENT_YEAR = BENSON.getCurrentYear().toString();
    private static final String VALID_CURRENT_GRADE = BENSON.getCurrentGrade().toString();
    private static final String VALID_EDULEVEL = BENSON.getEduLevel().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final int VALID_PAYMENT_FEE = BENSON.getPaymentInfo().getPaymentFee();
    private static final String VALID_PAYMENT_DATE = BENSON.getPaymentInfo().getPaymentDate();
    private static final String VALID_PAYMENT_STATUS = BENSON.getPaymentInfo().getPaymentStatus();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_EDULEVEL,
                VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEduLevel_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = EduLevel.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCurrentGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, INVALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = CurrentGrade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidExpectedGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, INVALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = ExpectedGrade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullExpectedGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                    VALID_EDULEVEL, VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, null, VALID_TAGS,
                    VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpectedGrade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE,
                        invalidTags, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        assertThrows(IllegalValueException.class, person::toModelType);

        // Invalid tags as it breaks the maximum char constraints
        invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag("1234567890TOOLONG#123123"));
        person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE,
                        invalidTags, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        assertThrows(IllegalValueException.class, person::toModelType);

        // Invalid tags as it breaks the maximum tags in set constraints
        invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag("1"));
        invalidTags.add(new JsonAdaptedTag("2"));
        invalidTags.add(new JsonAdaptedTag("3"));
        invalidTags.add(new JsonAdaptedTag("4"));
        invalidTags.add(new JsonAdaptedTag("5"));
        invalidTags.add(new JsonAdaptedTag("6"));
        invalidTags.add(new JsonAdaptedTag("7"));
        invalidTags.add(new JsonAdaptedTag("8"));
        invalidTags.add(new JsonAdaptedTag("9"));
        person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE,
                        invalidTags, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPaymentFee_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        INVALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = PaymentInfo.MESSAGE_CONSTRAINTS_FEE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPaymentDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, INVALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        String expectedMessage = PaymentInfo.MESSAGE_CONSTRAINTS_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPaymentDate_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, null, VALID_PAYMENT_STATUS);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidPaymentStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EDULEVEL,
                        VALID_CURRENT_YEAR, VALID_CURRENT_GRADE, VALID_EXP_GRADE, VALID_TAGS,
                        VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, INVALID_PAYMENT_STATUS);
        String expectedMessage = PaymentInfo.MESSAGE_CONSTRAINTS_STATUS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPaymentStatus_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(ALICE.getName().toString(), ALICE.getPhone().toString(),
                        ALICE.getEmail().toString(), ALICE.getAddress().toString(), ALICE.getEduLevel().toString(),
                        ALICE.getCurrentYear().toString(), ALICE.getCurrentGrade().toString(),
                        ALICE.getExpectedGrade().toString(), ALICE.getTags().stream()
                        .map(JsonAdaptedTag::new).collect(Collectors.toList()), ALICE.getPaymentInfo().getPaymentFee(),
                        ALICE.getPaymentInfo().getPaymentDate(), null);
        assertEquals(ALICE, person.toModelType());
    }
}
