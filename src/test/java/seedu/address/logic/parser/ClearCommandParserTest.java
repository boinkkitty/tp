package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_SEQUENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX_SEQUENCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.tag.Tag;

public class ClearCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE);

    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_bothTagsAndIndicesPresent_failure() {
        assertParseFailure(parser, INDEX_SEQUENCE_DESC + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_repeatedIndices_failure() throws Exception {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_SEQUENCE_DESC + INDEX_SEQUENCE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INDEX_SEQUENCE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INDEX_SEQUENCE_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validTags_success() {
        ClearCommand expectedClearCommand = new ClearCommand(Set.of(new Tag(VALID_TAG_FRIEND)));
        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedClearCommand);
    }

    @Test
    public void parse_validIndexSequence_success() {
        ClearCommand expectedClearCommand = new ClearCommand(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);
        assertParseSuccess(parser, INDEX_SEQUENCE_DESC, expectedClearCommand);
    }
}
