package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApparels.getTypicalAddressBook;
import static seedu.address.testutil.TypicalApparels.getTypicalApparels;
import static seedu.address.testutil.TypicalApparels.getTypicalApparelsSortedByName;

import java.util.List;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.options.SortOption;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.apparel.Apparel;


/**
 *
 */
public class SortCommandTest {

    // Setup
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_sortedName_success() {
        Model expectedModelSortedByName = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Apparel> expectedList = getTypicalApparelsSortedByName();

        SortCommand sc = new SortCommand(SortOption.NAME);
        String expectedMessage = SortCommand.MESSAGE_SORT_APPAREL_SUCCESS + " by name.";

//        try {
//            sc.execute(model, commandHistory);
//
//            for (int i = 0; i < expectedList.size(); i++) {
//                Apparel actual = model.getFilteredApparelList().get(i);
//                Apparel expected = expectedList.get(i);
//                if (!actual.equals(expected)) {
//                    System.out.println(actual.toString() + "\nvs\n" + expected.toString());
//                }
//            }
//        } catch (CommandException e) {
//            System.out.println("Command exception...");
//        }

        assertCommandSuccess(sc, model, commandHistory, expectedMessage, expectedModelSortedByName);
    }
}
