package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ToggleThemeCommand.SHOWING_TOGGLE_THEME_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ToggleThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switchTheme_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_TOGGLE_THEME_MESSAGE, false, false, true);
        assertCommandSuccess(new ToggleThemeCommand(), model, expectedCommandResult, expectedModel);
    }
}
