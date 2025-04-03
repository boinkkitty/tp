package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Removes all occurrences of the specified tags from all persons in the address book.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes specified tags from all persons.\n"
            + "Parameters: " + PREFIX_TAG + "TAG" + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "CS2040 "
            + PREFIX_TAG + "CS2030";

    public static final String MESSAGE_SUCCESS = "The following tags have been cleared: %1$s.\n"
            + "%2$d person(s) had their tags updated.";
    public static final String MESSAGE_NO_PERSON_UPDATED = "No persons were updated as no matching tags were found.";

    private final Set<Tag> tagsToRemove;

    /**
     * Creates an UntagCommand to remove the specified {@code tagsToRemove}.
     */
    public UntagCommand(Set<Tag> tagsToRemove) {
        requireNonNull(tagsToRemove);
        this.tagsToRemove = tagsToRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personsToUpdate = model.getFilteredPersonList().stream()
                .filter(person -> person.getTags().stream().anyMatch(tagsToRemove::contains))
                .toList();

        if (personsToUpdate.isEmpty()) {
            throw new CommandException(MESSAGE_NO_PERSON_UPDATED);
        }

        for (Person personToUntag : personsToUpdate) {
            Set<Tag> updatedTags = personToUntag.getTags().stream()
                    .filter(tag -> !tagsToRemove.contains(tag))
                    .collect(Collectors.toSet());
            Person updatedPerson = createUpdatedPerson(personToUntag, updatedTags);
            model.setPerson(personToUntag, updatedPerson);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, tagsToRemove, personsToUpdate.size()));
    }

    /**
     * Creates a new Person object with updated tags.
     *
     * @param personToEdit The original person to update.
     * @param updatedTags The updated set of tags.
     * @return A new Person object with the updated tags.
     */
    private Person createUpdatedPerson(Person personToEdit, Set<Tag> updatedTags) {
        return new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getEduLevel(),
                personToEdit.getCurrentYear(),
                personToEdit.getCurrentGrade(),
                personToEdit.getExpectedGrade(),
                updatedTags,
                personToEdit.getPaymentInfo()
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntagCommand // instanceof handles nulls
                && tagsToRemove.equals(((UntagCommand) other).tagsToRemove));
    }

}
