package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UntagCommand;
import seedu.address.model.tag.Tag;

public class UntagCommandParserTest {

    private final UntagCommandParser parser = new UntagCommandParser();

    @Test
    public void parse_validArgs_success() {
        Set<Tag> tagsToRemove = Set.of(new Tag("CS2040"), new Tag("Math"));
        assertParseSuccess(parser, " t/CS2040 t/Math", new UntagCommand(tagsToRemove));
    }

    @Test
    public void parse_missingTag_failure() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, " t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_TAG + " cannot be empty."));
    }

    @Test
    public void parse_duplicateTags_success() {
        Set<Tag> tagsToRemove = Set.of(new Tag("CS2040"));
        assertParseSuccess(parser, " t/CS2040 t/CS2040", new UntagCommand(tagsToRemove));
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // Non-empty preamble with valid tag
        assertParseFailure(parser, "invalidPreamble t/CS2040",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));

        // Non-empty preamble with no tags
        assertParseFailure(parser, "invalidPreamble",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));

        // Empty preamble but no tags provided
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE));
    }
}
