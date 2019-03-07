package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Apparel> filteredApparels;
    private final SimpleObjectProperty<Apparel> selectedPerson = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApparels = new FilteredList<>(versionedAddressBook.getApparelList());
        filteredApparels.addListener(this::ensureSelectedPersonIsValid);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson(Apparel apparel) {
        requireNonNull(apparel);
        return versionedAddressBook.hasApparel(apparel);
    }

    @Override
    public void deletePerson(Apparel target) {
        versionedAddressBook.removeApparel(target);
    }

    @Override
    public void addPerson(Apparel apparel) {
        versionedAddressBook.addApparel(apparel);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Apparel target, Apparel editedApparel) {
        requireAllNonNull(target, editedApparel);

        versionedAddressBook.setApparel(target, editedApparel);
    }

    //=========== Filtered Apparel List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Apparel} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Apparel> getFilteredPersonList() {
        return filteredApparels;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Apparel> predicate) {
        requireNonNull(predicate);
        filteredApparels.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    //=========== Selected apparel ===========================================================================

    @Override
    public ReadOnlyProperty<Apparel> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public Apparel getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedPerson(Apparel apparel) {
        if (apparel != null && !filteredApparels.contains(apparel)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(apparel);
    }

    /**
     * Ensures {@code selectedPerson} is a valid apparel in {@code filteredApparels}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends Apparel> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected apparel, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the apparel that came before it in the list,
                // or clear the selection if there is no such apparel.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApparels.equals(other.filteredApparels)
                && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
    }

}
