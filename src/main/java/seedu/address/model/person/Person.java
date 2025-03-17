package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final CurrentYear currentYear;
    private final CurrentGrade currentGrade;
    private final ExpectedGrade expectedGrade;
    private final Set<Tag> tags = new HashSet<>();
    private final PaymentInfo paymentInfo;
    private final EduLevel eduLevel;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, EduLevel eduLevel, CurrentYear currentYear,
                  CurrentGrade currentGrade, ExpectedGrade expectedGrade, Set<Tag> tags, PaymentInfo paymentInfo) {
        requireAllNonNull(name, phone, email, address, currentYear, currentGrade, expectedGrade, tags, paymentInfo);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.expectedGrade = expectedGrade;
        this.eduLevel = eduLevel;
        this.currentYear = currentYear;
        this.currentGrade = currentGrade;
        this.tags.addAll(tags);
        this.paymentInfo = paymentInfo;

    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public EduLevel getEduLevel() {
        return eduLevel;
    }

    public CurrentYear getCurrentYear() {
        return currentYear;
    }

    public CurrentGrade getCurrentGrade() {
        return currentGrade;
    }

    public ExpectedGrade getExpectedGrade() {
        return expectedGrade;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && eduLevel.equals(otherPerson.eduLevel)
                && currentYear.equals(otherPerson.currentYear)
                && currentGrade.equals(otherPerson.currentGrade)
                && expectedGrade.equals(otherPerson.expectedGrade)
                && tags.equals(otherPerson.tags)
                && paymentInfo.equals(otherPerson.paymentInfo);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, currentYear, currentGrade, expectedGrade, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("eduLevel", eduLevel)
                .add("currentYear", currentYear)
                .add("currentGrade", currentGrade)
                .add("expectedGrade", expectedGrade)
                .add("tags", tags)
                .add("paymentInfo", paymentInfo)
                .toString();
    }

}
