package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EDULEVEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXP_GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXP_GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDULEVEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXP_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EDULEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXP_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXP_GRADE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FilterDescriptorBuilder;

public class FilterCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_EXP_GRADE_DESC,
                ExpectedGrade.MESSAGE_CONSTRAINTS); // invalid expected grade
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, INVALID_GRADE_DESC, CurrentGrade.MESSAGE_CONSTRAINTS); // invalid grade
        assertParseFailure(parser, INVALID_EDULEVEL_DESC, EduLevel.MESSAGE_CONSTRAINTS); // invalid education level

        // invalid edulevel followed by invalid grade
        assertParseFailure(parser,
                INVALID_EDULEVEL_DESC + INVALID_GRADE_DESC, EduLevel.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_EDULEVEL_DESC + INVALID_GRADE_DESC + INVALID_EXP_GRADE_DESC
                + INVALID_TAG_DESC, EduLevel.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = EDULEVEL_DESC_AMY + GRADE_DESC_AMY + EXP_GRADE_DESC_AMY + TAG_DESC_FRIEND;

        FilterDescriptor descriptor = new FilterDescriptorBuilder().withEduLevel(VALID_EDULEVEL_AMY)
                .withCurrentGrade(VALID_GRADE_AMY).withExpectedGrade(VALID_EXP_GRADE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = EDULEVEL_DESC_AMY + GRADE_DESC_AMY;

        FilterDescriptor descriptor = new FilterDescriptorBuilder().withEduLevel(VALID_EDULEVEL_AMY)
                .withCurrentGrade(VALID_GRADE_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // edu level
        String userInput = EDULEVEL_DESC_AMY;
        FilterDescriptor descriptor = new FilterDescriptorBuilder().withEduLevel(VALID_EDULEVEL_AMY).build();
        FilterCommand filterCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, filterCommand);

        // current grade
        userInput = GRADE_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withCurrentGrade(VALID_GRADE_AMY).build();
        filterCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, filterCommand);

        // expected grade
        userInput = EXP_GRADE_DESC_AMY;
        descriptor = new FilterDescriptorBuilder().withExpectedGrade(VALID_EXP_GRADE_AMY).build();
        filterCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, filterCommand);

        // tags
        userInput = TAG_DESC_HUSBAND;
        descriptor = new FilterDescriptorBuilder().withTags(VALID_TAG_HUSBAND).build();
        filterCommand = new FilterCommand(descriptor);
        assertParseSuccess(parser, userInput, filterCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in

        // valid followed by invalid
        String userInput = EXP_GRADE_DESC_BOB + INVALID_EXP_GRADE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EXP_GRADE));

        // invalid followed by valid
        userInput = INVALID_EXP_GRADE_DESC + EXP_GRADE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EXP_GRADE));

        // mulltiple valid fields repeated
        userInput = TAG_DESC_FRIEND + GRADE_DESC_AMY + EXP_GRADE_DESC_AMY + TAG_DESC_FRIEND + GRADE_DESC_AMY
            + EXP_GRADE_DESC_AMY + TAG_DESC_HUSBAND + GRADE_DESC_BOB + EXP_GRADE_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CURRENT_GRADE, PREFIX_EXP_GRADE));
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // Non-empty preamble with valid field
        assertParseFailure(parser, "invalidPreamble" + GRADE_DESC_AMY ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // Non-empty preamble with no field
        assertParseFailure(parser, "invalidPreamble",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // Empty preamble
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
