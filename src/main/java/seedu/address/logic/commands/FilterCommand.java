package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDULEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXP_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter the contact list according to the specified "
            + "conditions provided. If multiple conditions are provided, all conditions must be met.\n"
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_EDULEVEL + "EDUCATION] "
            + "[" + PREFIX_CURRENT_GRADE + "CURRENT_GRADE] "
            + "[" + PREFIX_EXP_GRADE + "EXP_GRADE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Husband ";


    public static final String MESSAGE_FILTER_SUCCESS = "Filtered Persons: %1$s";
    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    private final FilterDescriptor filterDescriptor;

    /**
     * @param filterDescriptor details to filter the list with
     */
    public FilterCommand(FilterDescriptor filterDescriptor) {
        requireNonNull(filterDescriptor);

        this.filterDescriptor = new FilterDescriptor(filterDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(filterDescriptor);
        return new CommandResult(String.format(MESSAGE_FILTER_SUCCESS, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return filterDescriptor.equals(otherFilterCommand.filterDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("filterDescriptor", filterDescriptor)
                .toString();
    }

    /**
     * Stores the details to filter the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class FilterDescriptor implements Predicate<Person> {
        private CurrentGrade currentGrade;
        private EduLevel eduLevel;
        private ExpectedGrade expectedGrade;
        private Set<Tag> tags;

        public FilterDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FilterDescriptor(FilterDescriptor toCopy) {
            setEduLevel(toCopy.eduLevel);
            setExpectedGrade(toCopy.expectedGrade);
            setCurrentGrade(toCopy.currentGrade);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eduLevel, currentGrade, expectedGrade, tags);
        }

        @Override
        public boolean test(Person person) {
            return this.compareEduLevel(person.getEduLevel())
                    && this.compareCurrentGrade(person.getCurrentGrade())
                    && this.compareExpectedGrade(person.getExpectedGrade())
                    && this.compareTags(person.getTags());
        }

        public void setEduLevel(EduLevel eduLevel) {
            this.eduLevel = eduLevel;
        }

        public Optional<EduLevel> getEduLevel() {
            return Optional.ofNullable(eduLevel);
        }

        public void setExpectedGrade(ExpectedGrade expectedGrade) {
            this.expectedGrade = expectedGrade;
        }

        public Optional<ExpectedGrade> getExpectedGrade() {
            return Optional.ofNullable(expectedGrade);
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

        public boolean compareEduLevel(EduLevel otherEduLevel) {
            return eduLevel == null || eduLevel.equals(otherEduLevel);
        }

        public boolean compareCurrentGrade(CurrentGrade otherCurrentGrade) {
            return currentGrade == null || currentGrade.equals(otherCurrentGrade);
        }

        public boolean compareExpectedGrade(ExpectedGrade otherExpectedGrade) {
            return expectedGrade == null || expectedGrade.equals(otherExpectedGrade);
        }

        public boolean compareTags(Set<Tag> otherTags) {
            return tags == null || otherTags.containsAll(tags);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterDescriptor)) {
                return false;
            }

            FilterDescriptor otherFilterDescriptor = (FilterDescriptor) other;
            return Objects.equals(eduLevel, otherFilterDescriptor.eduLevel)
                    && Objects.equals(currentGrade, otherFilterDescriptor.currentGrade)
                    && Objects.equals(expectedGrade, otherFilterDescriptor.expectedGrade)
                    && Objects.equals(tags, otherFilterDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("eduLevel", eduLevel)
                    .add("currentGrade", currentGrade)
                    .add("expectedGrade", expectedGrade)
                    .add("tags", tags)
                    .toString();
        }
    }
}
