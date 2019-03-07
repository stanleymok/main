package seedu.address.model.apparel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.apparel.exceptions.DuplicatePersonException;
import seedu.address.model.apparel.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A apparel is considered unique by comparing using {@code Apparel#isSamePerson(Apparel)}. As such, adding and updating of
 * persons uses Apparel#isSamePerson(Apparel) for equality so as to ensure that the apparel being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a apparel uses Apparel#equals(Object) so
 * as to ensure that the apparel with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Apparel#isSamePerson(Apparel)
 */
public class UniquePersonList implements Iterable<Apparel> {

    private final ObservableList<Apparel> internalList = FXCollections.observableArrayList();
    private final ObservableList<Apparel> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent apparel as the given argument.
     */
    public boolean contains(Apparel toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a apparel to the list.
     * The apparel must not already exist in the list.
     */
    public void add(Apparel toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the apparel {@code target} in the list with {@code editedApparel}.
     * {@code target} must exist in the list.
     * The apparel identity of {@code editedApparel} must not be the same as another existing apparel in the list.
     */
    public void setPerson(Apparel target, Apparel editedApparel) {
        requireAllNonNull(target, editedApparel);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedApparel) && contains(editedApparel)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedApparel);
    }

    /**
     * Removes the equivalent apparel from the list.
     * The apparel must exist in the list.
     */
    public void remove(Apparel toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setApparels(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code apparels}.
     * {@code apparels} must not contain duplicate apparels.
     */
    public void setApparels(List<Apparel> apparels) {
        requireAllNonNull(apparels);
        if (!personsAreUnique(apparels)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(apparels);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Apparel> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Apparel> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code apparels} contains only unique apparels.
     */
    private boolean personsAreUnique(List<Apparel> apparels) {
        for (int i = 0; i < apparels.size() - 1; i++) {
            for (int j = i + 1; j < apparels.size(); j++) {
                if (apparels.get(i).isSamePerson(apparels.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
