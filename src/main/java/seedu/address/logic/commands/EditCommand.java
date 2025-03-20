package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDULEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXP_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_EDULEVEL + "EDUCATION] "
            + "[" + PREFIX_CURRENT_YEAR + "CURRENT_YEAR] "
            + "[" + PREFIX_CURRENT_GRADE + "CURRENT_GRADE] "
            + "[" + PREFIX_EXP_GRADE + "EXP_GRADE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_TAG + "REMOVE_TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_CURRENT_GRADE + "C";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        EduLevel updatedEduLevel = editPersonDescriptor.getEduLevel().orElse(personToEdit.getEduLevel());
        CurrentYear updatedCurrentYear = editPersonDescriptor.getCurrentYear().orElse(personToEdit.getCurrentYear());
        CurrentGrade updatedCurrentGrade = editPersonDescriptor.getCurrentGrade()
                .orElse(personToEdit.getCurrentGrade());
        ExpectedGrade updatedExpectedGrade = editPersonDescriptor.getExpectedGrade()
                .orElse(personToEdit.getExpectedGrade());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        if (editPersonDescriptor.getTagsToRemove().isPresent()) {
            updatedTags = updatedTags.stream()
                    .filter(tag -> !editPersonDescriptor.getTagsToRemove().get().contains(tag))
                    .collect(Collectors.toSet());
        }

        if (editPersonDescriptor.getTagsToAppend().isPresent()) {
            Set<Tag> modifiableTags = new HashSet<>(updatedTags);
            modifiableTags.addAll(editPersonDescriptor.getTagsToAppend().get());
            updatedTags = modifiableTags;
        }


        // Edit command does not allow editing paymentInfo
        PaymentInfo updatedPaymentInfo = personToEdit.getPaymentInfo();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedEduLevel, updatedCurrentYear,
                updatedCurrentGrade, updatedExpectedGrade, updatedTags, updatedPaymentInfo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private ExpectedGrade expectedGrade;
        private CurrentYear currentYear;
        private CurrentGrade currentGrade;
        private Set<Tag> tags;
        private Set<Tag> tagsToRemove;
        private Set<Tag> tagsToAppend;
        private EduLevel eduLevel;



        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setExpectedGrade(toCopy.expectedGrade);
            setEduLevel(toCopy.eduLevel);
            setCurrentYear(toCopy.currentYear);
            setCurrentGrade(toCopy.currentGrade);
            setTags(toCopy.tags);
            setTagsToRemove(toCopy.tagsToRemove);
            setTagsToAppend(toCopy.tagsToAppend);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, eduLevel, currentYear, currentGrade,
                    expectedGrade, tags, tagsToRemove, tagsToAppend);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public void setEduLevel(EduLevel eduLevel) {
            this.eduLevel = eduLevel;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setExpectedGrade(ExpectedGrade expectedGrade) {
            this.expectedGrade = expectedGrade;
        }

        public Optional<ExpectedGrade> getExpectedGrade() {
            return Optional.ofNullable(expectedGrade);
        }

        public Optional<EduLevel> getEduLevel() {
            return Optional.ofNullable(eduLevel);
        }

        public void setCurrentYear(CurrentYear currentYear) {
            this.currentYear = currentYear;
        }

        public Optional<CurrentYear> getCurrentYear() {
            return (currentYear != null) ? Optional.of(currentYear) : Optional.empty();
        }

        public void setCurrentGrade(CurrentGrade currentGrade) {
            this.currentGrade = currentGrade;
        }

        public Optional<CurrentGrade> getCurrentGrade() {
            return Optional.ofNullable(currentGrade);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tagsToRemove}
         * A defensive copy of {@code tagsToRemove} is used internally.
         */
        public void setTagsToRemove(Set<Tag> tagsToRemove) {
            this.tagsToRemove = (tagsToRemove != null) ? new HashSet<>(tagsToRemove) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tagsToRemove} is null.
         */
        public Optional<Set<Tag>> getTagsToRemove() {
            return (tagsToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(tagsToRemove))
                    : Optional.empty();
        }

        public void setTagsToAppend(Set<Tag> tagsToAppend) {
            this.tagsToAppend = (tagsToAppend != null) ? new HashSet<>(tagsToAppend) : null;
        }

        public Optional<Set<Tag>> getTagsToAppend() {
            return (tagsToAppend != null)
                    ? Optional.of(Collections.unmodifiableSet(tagsToAppend))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(eduLevel, otherEditPersonDescriptor.eduLevel)
                    && Objects.equals(currentYear, otherEditPersonDescriptor.currentYear)
                    && Objects.equals(currentGrade, otherEditPersonDescriptor.currentGrade)
                    && Objects.equals(expectedGrade, otherEditPersonDescriptor.expectedGrade)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(tagsToRemove, otherEditPersonDescriptor.tagsToRemove)
                    && Objects.equals(tagsToAppend, otherEditPersonDescriptor.tagsToAppend);
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
                    .toString();
        }
    }
}
