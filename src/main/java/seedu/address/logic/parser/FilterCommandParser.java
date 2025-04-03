package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDULEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXP_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns an FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_EDULEVEL, PREFIX_CURRENT_GRADE, PREFIX_EXP_GRADE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EDULEVEL, PREFIX_CURRENT_GRADE, PREFIX_EXP_GRADE);

        FilterDescriptor filterDescriptor = new FilterDescriptor();
        if (argMultimap.isEmptyField(PREFIX_TAG)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_TAG + " cannot be empty."));
        }

        if (argMultimap.getValue(PREFIX_EDULEVEL).isPresent()) {
            filterDescriptor.setEduLevel(ParserUtil.parseEduLevel(argMultimap.getValue(PREFIX_EDULEVEL).get()));
        }

        if (argMultimap.getValue(PREFIX_CURRENT_GRADE).isPresent()) {
            filterDescriptor.setCurrentGrade(
                    ParserUtil.parseCurrentGrade(argMultimap.getValue(PREFIX_CURRENT_GRADE).get()));
        }

        if (argMultimap.getValue(PREFIX_EXP_GRADE).isPresent()) {
            filterDescriptor.setExpectedGrade(
                    ParserUtil.parseExpectedGrade(argMultimap.getValue(PREFIX_EXP_GRADE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(filterDescriptor::setTags);

        if (!filterDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(filterDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
