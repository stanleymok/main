package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.SortBy;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.apparel.Apparel;

/**
 * Edits the details of an existing apparel in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the apparels based on the given option.\n"
            + "Example: sort -n\n"
            + "Outcome: apparel list sorted in ascending order of the name.\n";


    public static final String MESSAGE_SORT_APPAREL_SUCCESS = "Apparels sorted";
    public static final String MESSAGE_INVALID_OPTION = "Invalid option: Type `sort see options` "
            + "to see valid options.\n";

    private final SortBy sortBy;

    /**
     * @param sortBy the sorting option
     */
    public SortCommand(SortBy sortBy) {
        requireNonNull(sortBy);

        this.sortBy = sortBy;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Apparel> lastShownList = model.getFilteredApparelList();

        if (sortBy.isValid()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPAREL_DISPLAYED_INDEX);
        }

        // if sort is not given an option, display invalid command message

        // sort by sortBy.getValue()
        // model delete all apparels
        // model add all in the newly sorted

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SORT_APPAREL_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand e = (SortCommand) other;
        return sortBy.equals(e.sortBy);
    }
}
