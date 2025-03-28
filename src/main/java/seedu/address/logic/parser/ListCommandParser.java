package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code ListCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code ListCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public ListCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("List command does not take any arguments!");
        }
        return new ListCommand();
    }
}
