package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX_SEQUENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsContainTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Deletes all persons that matches provided criteria in the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes all persons EITHER"
            + " from a start to end index OR"
            + " matching the specified tags. \n"
            + "Parameters:  " + PREFIX_INDEX_SEQUENCE + "START...END OR "
            + PREFIX_TAG + "TAG" + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX_SEQUENCE + "1...5"
            + " OR " + COMMAND_WORD + " " + PREFIX_TAG + "CS2030C " + PREFIX_TAG + "GEA1000";

    public static final String MESSAGE_SUCCESS = "Number of persons removed successfully: %1$s";
    public static final String MESSAGE_SUCCESS_INDEX = "%d. Deleted persons from index %d to %d";
    public static final String MESSAGE_SUCCESS_TAG = "%d. Person(s) deleted had at least one of these tag(s): %s";
    public static final String MESSAGE_NO_PERSONS_FOUND = "0. No persons found with the tags: %s.";
    public static final String MESSAGE_INVALID_INDEX_RANGE = "Invalid index range provided. End index must be "
            + "strictly greater than start index";
    public static final String MESSAGE_INVALID_START_INDEX = "Invalid start index provided";
    public static final String MESSAGE_INVALID_END_INDEX = "Invalid end index provided";
    public static final String MESSAGE_MISSING_TAGS = "Please provide at least one tag to clear";


    private final Index start;
    private final Index end;
    private final Set<Tag> tags;

    /**
     * Creates a ClearCommand to remove persons with matching {@Tags}
     */
    public ClearCommand(Set<Tag> tags) {
        requireNonNull(tags);
        this.start = null;
        this.end = null;
        this.tags = tags;
    }

    /**
     * Creates a ClearCommand to remove persons with matching {@Index}
     */
    public ClearCommand(Index start, Index end) {
        requireNonNull(start);
        requireNonNull(end);
        this.start = start;
        this.end = end;
        this.tags = null;
    }

    public ClearCommand() throws CommandException {
        throw new CommandException(MESSAGE_USAGE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (tags != null) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, executeClearWithTags(model)));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, executeClearWithIndexRange(model)));
        }
    }

    private String executeClearWithIndexRange(Model model) throws CommandException {
        if (start == null || end == null) {
            throw new IllegalStateException();
        }
        List<Person> lastShownList = model.getFilteredPersonList();
        if (start.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_START_INDEX);
        }
        if (end.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_END_INDEX);
        }
        if (end.getZeroBased() <= start.getZeroBased()) {
            throw new CommandException(MESSAGE_INVALID_INDEX_RANGE);
        }

        List<Person> subListToDelete = lastShownList
                .subList(start.getZeroBased(), end.getZeroBased() + 1)
                .stream()
                .toList();

        subListToDelete.forEach(model::deletePerson);

        int numberOfPersonsDeleted = end.getZeroBased() - start.getZeroBased() + 1;
        return String.format(MESSAGE_SUCCESS_INDEX,
                numberOfPersonsDeleted, start.getOneBased(), end.getOneBased());
    }

    private String executeClearWithTags(Model model) throws CommandException {
        if (tags == null) {
            throw new IllegalStateException();
        }

        if (tags.isEmpty()) {
            throw new CommandException(MESSAGE_MISSING_TAGS);
        }

        TagsContainTagPredicate predicate = new TagsContainTagPredicate(tags);

        // Filter and delete persons
        List<Person> personsToDelete = model.getFilteredPersonList().stream()
                .filter(predicate) // Use the reusable predicate here
                .toList();

        personsToDelete.forEach(model::deletePerson);

        int numberOfPersonsDeleted = personsToDelete.size();
        if (numberOfPersonsDeleted == 0) {
            return String.format(MESSAGE_NO_PERSONS_FOUND, tags.toString());
        }

        return String.format(MESSAGE_SUCCESS_TAG,
                numberOfPersonsDeleted, tags.toString());

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearCommand)) {
            return false;
        }

        ClearCommand otherClearCommand = (ClearCommand) other;
        if (tags == null && otherClearCommand.tags == null) {
            return start.equals(otherClearCommand.start) && end.equals(otherClearCommand.end);
        } else if (tags != null && otherClearCommand.tags != null) {
            return tags.equals(otherClearCommand.tags);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (tags != null) {
            return new ToStringBuilder(this).add("tags", tags).toString();
        } else {
            return new ToStringBuilder(this)
                    .add("start", start.getOneBased())
                    .add("end", end.getOneBased())
                    .toString();
        }
    }
}
