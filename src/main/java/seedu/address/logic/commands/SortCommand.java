package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.options.SortOption;
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

    private final SortOption sortOption;

    /**
     * @param sortOption the sorting option
     */
    public SortCommand(SortOption sortOption) {
        requireNonNull(sortOption);

        this.sortOption = sortOption;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Apparel> lastShownList = model.getFilteredApparelList();
        List<Apparel> modifiableList = new ArrayList<Apparel>(lastShownList);
        System.out.println("Before: " + modifiableList.toString());
        if (SortOption.NAME.equals(sortOption)) {
            //System.out.println("e-Name: " + sortOption.toString());
            modifiableList.sort(new Comparator<Apparel>() {
                @Override
                public int compare(Apparel x, Apparel y) {
                    return x.getName().compareTo(y.getName());
                }
            });
        } else if (SortOption.COLOR.equals(sortOption)) {
            //System.out.println("e-Color: " + sortOption.toString());
        } else if (SortOption.TYPE.equals(sortOption)) {
            //System.out.println("e-Type: " + sortOption.toString());
        }

        System.out.println("After: " + modifiableList.toString());

        // TODO: update database
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
        return sortOption.equals(e.sortOption);
    }
}
