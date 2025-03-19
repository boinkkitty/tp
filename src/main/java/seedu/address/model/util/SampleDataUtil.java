package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final PaymentInfo EMPTY_PAYMENT_INFO = new PaymentInfo();

    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), new EduLevel("Primary"), new CurrentYear("Primary 6"),
                        new CurrentGrade("C"), new ExpectedGrade("A"), getTagSet("Math#FF5733"),
                        new PaymentInfo(200, "12-01-2024")),

                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new EduLevel("Secondary"), new CurrentYear("Secondary 3"),
                        new CurrentGrade("D"), new ExpectedGrade("B"), getTagSet("Physics#4287f5", "friends"),
                        new PaymentInfo(300, "15-02-2024")),

                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new EduLevel("Diploma"), new CurrentYear("Year 2"),
                        new CurrentGrade("F"), new ExpectedGrade("C"), getTagSet("IT101#8e44ad"),
                        EMPTY_PAYMENT_INFO),

                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new EduLevel("Bachelor"),
                        new CurrentYear("Year 4"), new CurrentGrade("B-"), new ExpectedGrade("D"),
                        getTagSet("CS2040C#1abc9c", "classmates"),
                        new PaymentInfo(600, "01-04-2024")),

                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), new EduLevel("Master"), new CurrentYear("Year 2"),
                        new CurrentGrade("C+"), new ExpectedGrade("E"), getTagSet("CS2103T#e67e22", "CS4238"),
                        new PaymentInfo(750, "18-05-2024")),

                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), new EduLevel(), new CurrentYear(),
                        new CurrentGrade(), new ExpectedGrade(), getTagSet("CS3230#3498db"),
                        EMPTY_PAYMENT_INFO)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
