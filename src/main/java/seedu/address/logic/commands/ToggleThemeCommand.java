package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class ToggleThemeCommand extends Command {

    public static final String COMMAND_WORD = "toggletheme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles dark and light themes.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TOGGLE_THEME_MESSAGE = "Toggled Theme.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_TOGGLE_THEME_MESSAGE, false, false, true);
    }
}
