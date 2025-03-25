package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.CurrentYear;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.person.Name;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String expectedGrade;
    private final String currentGrade;
    private final String currentYear;
    private final String eduLevel;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final int paymentFee;
    private final String paymentDate;
    private final String paymentStatus;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("eduLevel") String eduLevel, @JsonProperty("currentYear") String currentYear,
            @JsonProperty("currentGrade") String currentGrade, @JsonProperty("expectedGrade") String expectedGrade,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("paymentFee") int paymentFee,
            @JsonProperty("paymentDate") String paymentDate, @JsonProperty("paymentStatus") String paymentStatus) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.expectedGrade = expectedGrade;
        this.currentYear = currentYear;
        this.currentGrade = currentGrade;
        this.eduLevel = eduLevel;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.paymentFee = paymentFee;
        this.paymentDate = Objects.requireNonNullElse(paymentDate, "");
        this.paymentStatus = Objects.requireNonNullElse(paymentStatus, "");
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        expectedGrade = source.getExpectedGrade().value;
        currentYear = source.getCurrentYear().value;
        currentGrade = source.getCurrentGrade().value;
        eduLevel = source.getEduLevel().value; // getEduLevel() returns the value
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        paymentFee = source.getPaymentInfo().getPaymentFee();
        paymentDate = source.getPaymentInfo().getPaymentDate();
        paymentStatus = source.getPaymentInfo().getPaymentStatus();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (eduLevel == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EduLevel.class.getSimpleName()));
        }
        if (!EduLevel.isValidEduLevel(eduLevel)) {
            throw new IllegalValueException(EduLevel.MESSAGE_CONSTRAINTS);
        }
        final EduLevel modelEduLevel = new EduLevel(eduLevel);

        final CurrentYear modelCurrentYear;
        if (currentYear == null) {
            modelCurrentYear = new CurrentYear(); // If currentYear field is missing, currentYear will be set to "".
        } else {
            if (!CurrentYear.isValidCurrentYear(currentYear)) {
                throw new IllegalValueException(CurrentYear.MESSAGE_CONSTRAINTS);
            }
            modelCurrentYear = new CurrentYear(currentYear);
        }

        if (currentGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CurrentGrade.class.getSimpleName()));
        }
        if (!CurrentGrade.isValidCurrentGrade(currentGrade)) {
            throw new IllegalValueException(CurrentGrade.MESSAGE_CONSTRAINTS);
        }
        final CurrentGrade modelCurrentGrade = new CurrentGrade(currentGrade);

        if (expectedGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ExpectedGrade.class.getSimpleName()));
        }
        if (!ExpectedGrade.isValidExpectedGrade(expectedGrade)) {
            throw new IllegalValueException(ExpectedGrade.MESSAGE_CONSTRAINTS);
        }
        final ExpectedGrade modelExpectedGrade = new ExpectedGrade(expectedGrade);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        // IF paymentFee field is missing, it will be set as 0 by default. Therefore, no checks needed.
        if (!PaymentInfo.isValidFee(paymentFee)) {
            throw new IllegalValueException(PaymentInfo.MESSAGE_CONSTRAINTS_FEE);
        }
        if (paymentDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "[paymentDate]"));
        }
        if (!PaymentInfo.isValidDate(paymentDate)) {
            throw new IllegalValueException(PaymentInfo.MESSAGE_CONSTRAINTS_DATE);
        }
        if (paymentStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "[paymentStatus]"));
        }
        if (!PaymentInfo.isValidStatus(paymentStatus)) {
            throw new IllegalValueException(PaymentInfo.MESSAGE_CONSTRAINTS_STATUS);
        }
        final PaymentInfo paymentInfo = new PaymentInfo.Builder().setPaymentFee(paymentFee)
                .setPaymentDate(paymentDate).setPaymentStatus(paymentStatus).build();

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelEduLevel, modelCurrentYear,
                modelCurrentGrade, modelExpectedGrade, modelTags, paymentInfo);
    }

}
