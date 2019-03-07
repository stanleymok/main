package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.apparel.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a apparel with the same identity as {@code apparel} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given apparel.
     * The apparel must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given apparel.
     * {@code apparel} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given apparel {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The apparel identity of {@code editedPerson} must not be the same as another existing apparel in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered apparel list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered apparel list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected apparel in the filtered apparel list.
     * null if no apparel is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected apparel in the filtered apparel list.
     * null if no apparel is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected apparel in the filtered apparel list.
     */
    void setSelectedPerson(Person person);
}
