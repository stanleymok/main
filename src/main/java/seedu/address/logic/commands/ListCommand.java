package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPARELS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.options.ListOption;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all apparels";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List the apparels based on the option you supplied.\n"
            + "Example: list top\n"
            + "Outcome: all apparels that are of clothing type top.\n";

    private final ListOption listOption;

    /**
     * @param listOption the list option
     */
    public ListCommand(ListOption listOption) {
        requireNonNull(listOption);

        this.listOption = listOption;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS + " ");
        if (listOption.ALL.equals(listOption)) {
            model.updateFilteredApparelList(PREDICATE_SHOW_ALL_APPARELS);
        } else if (ListOption.TOP.equals(listOption)) {
            // do smth
            sb.append("by " + ListOption.TOP.toString().toLowerCase() + ".");
        } else if (ListOption.BOTTOM.equals(listOption)) {
            // do smth
            sb.append("by " + ListOption.BOTTOM.toString().toLowerCase() + ".");
        } else if (ListOption.BELT.equals(listOption)) {
            // do smth
            sb.append("by " + ListOption.BELT.toString().toLowerCase() + ".");
        } else if (ListOption.SHOES.equals(listOption)) {
            // do smth
            sb.append("by " + ListOption.SHOES.toString().toLowerCase() + ".");
        }

        return new CommandResult(sb.toString());
    }
}
