package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.CommandRecommendation;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;




/**
 * Return a recommended outfit to the user.
 */
// @@author PhilipPhil
public class RecommendationCommand extends Command {

    public static final String COMMAND_WORD = "recommendation";

    public static final String MESSAGE_SUCCESS = "---RECOMMENDATION---";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        CommandRecommendation rec = new CommandRecommendation(model);
        String outfit = rec.returnRecommendationString();

        return new CommandResult(MESSAGE_SUCCESS + "\n" + outfit + "\n" + MESSAGE_SUCCESS);
    }
}
