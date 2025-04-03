package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDULEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXP_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EDULEVEL, PREFIX_TAG, PREFIX_CURRENT_YEAR, PREFIX_CURRENT_GRADE, PREFIX_EXP_GRADE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EDULEVEL, PREFIX_CURRENT_YEAR, PREFIX_CURRENT_GRADE, PREFIX_EXP_GRADE);
        if (argMultimap.isEmptyField(PREFIX_EDULEVEL)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_EDULEVEL + " cannot be empty."));
        }
        if (argMultimap.isEmptyField(PREFIX_CURRENT_YEAR)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_CURRENT_YEAR + " cannot be empty."));
        }
        if (argMultimap.isEmptyField(PREFIX_CURRENT_GRADE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_CURRENT_GRADE + " cannot be empty."));
        }
        if (argMultimap.isEmptyField(PREFIX_EXP_GRADE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PREFIX_EXP_GRADE + " cannot be empty."));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        EduLevel eduLevel = ParserUtil.parseEduLevel(argMultimap.getValue(PREFIX_EDULEVEL).orElse(""));

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (tagList.size() > Tag.MAX_TAGS_IN_SET) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS_ADD_SET);
        }
        CurrentYear currentYear = ParserUtil.parseCurrentYear(argMultimap.getValue(PREFIX_CURRENT_YEAR)
            .orElse(""));
        CurrentGrade currentGrade = ParserUtil.parseCurrentGrade(argMultimap
                .getValue(PREFIX_CURRENT_GRADE).orElse(""));
        ExpectedGrade expectedGrade = ParserUtil.parseExpectedGrade(argMultimap.getValue(PREFIX_EXP_GRADE).orElse(""));

        // Add command does not allow adding paymentInfo straight away
        PaymentInfo paymentInfo = new PaymentInfo.Builder().build();

        Person person = new Person(name, phone, email, address, eduLevel, currentYear, currentGrade, expectedGrade,
                tagList, paymentInfo);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
