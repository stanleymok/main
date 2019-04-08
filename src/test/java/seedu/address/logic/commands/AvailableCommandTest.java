package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApparelAtIndex;
import static seedu.address.logic.commands.CommandTestUtilExtra.DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtilExtra.DESC_BOBBY;
import static seedu.address.testutil.TypicalApparels.getDirtyAddressBook;
import static seedu.address.testutil.TypicalApparels.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPAREL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPAREL;

import org.junit.Assert;
import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.AvailableCommand.AvailablePersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.apparel.Apparel;
import seedu.address.testutil.AvailableApparelDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class AvailableCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model dirtyModel = new ModelManager(getDirtyAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_wash_success() {
        //Apparel editedApparel = new ApparelBuilder().buildUnavailable();
        //AvailablePersonDescriptor descriptor = new AvailableApparelDescriptorBuilder(editedApparel).build();
        AvailableCommand availableCommand = new AvailableCommand(INDEX_FIRST_APPAREL, new AvailablePersonDescriptor());

        Apparel apparelToWash = dirtyModel.getFilteredApparelList().get(0);
        Apparel apparelAfterWash = apparelToWash.setWashed();

        String expectedMessage = String.format(AvailableCommand.MESSAGE_CLEAN_APPAREL_SUCCESS,
                INDEX_FIRST_APPAREL.getOneBased(), apparelAfterWash);

        // reset
        apparelToWash.setDirty();

        Model expectedModel = new ModelManager(new AddressBook(dirtyModel.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(apparelToWash, new Apparel(apparelToWash).setDirty());
        expectedModel.commitAddressBook();

        assertCommandSuccess(availableCommand, dirtyModel, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_apparelAlreadyClean_failure() {
        showApparelAtIndex(model, INDEX_FIRST_APPAREL);

        Apparel apparelInFilteredList = model.getFilteredApparelList().get(INDEX_FIRST_APPAREL.getZeroBased());
        AvailableCommand availableCommand = new AvailableCommand(INDEX_FIRST_APPAREL,
                new AvailableApparelDescriptorBuilder().build());

        String expectedMessage = String.format(AvailableCommand.MESSAGE_APPAREL_ALREADY_CLEAN_OCD,
                INDEX_FIRST_APPAREL.getOneBased(), apparelInFilteredList);

        assertCommandFailure(availableCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApparelList().size() + 1);
        AvailablePersonDescriptor descriptor = new AvailableApparelDescriptorBuilder().withName(VALID_NAME_B).build();
        AvailableCommand availableCommand = new AvailableCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(availableCommand, model, commandHistory, Messages.MESSAGE_INVALID_APPAREL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showApparelAtIndex(model, INDEX_FIRST_APPAREL);
        Index outOfBoundIndex = INDEX_SECOND_APPAREL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApparelList().size());

        AvailableCommand availableCommand = new AvailableCommand(outOfBoundIndex,
                new AvailableApparelDescriptorBuilder().withName(VALID_NAME_B).build());

        try {
            availableCommand.execute(model, commandHistory);
            Assert.fail("CommandException should be thrown.");
        } catch (CommandException ce) {
            // do nothing
        }
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApparelList().size() + 1);
        AvailablePersonDescriptor descriptor = new AvailableApparelDescriptorBuilder().withName(VALID_NAME_B).build();
        AvailableCommand availableCommand = new AvailableCommand(outOfBoundIndex, descriptor);

        // execution failed -> address book state not added into model
        assertCommandFailure(availableCommand, model, commandHistory, Messages.MESSAGE_INVALID_APPAREL_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        final AvailableCommand standardCommand = new AvailableCommand(INDEX_FIRST_APPAREL, DESC_ANDY);

        // same values -> returns true
        AvailablePersonDescriptor copyDescriptor = new AvailablePersonDescriptor(DESC_ANDY);
        AvailableCommand commandWithSameValues = new AvailableCommand(INDEX_FIRST_APPAREL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AvailableCommand(INDEX_SECOND_APPAREL, DESC_ANDY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AvailableCommand(INDEX_FIRST_APPAREL, DESC_BOBBY)));
    }

}
