package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.SortBy;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.apparel.Address;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;
import seedu.address.model.apparel.Color;
import seedu.address.model.apparel.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing apparel in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the apparels based on the given option.\n"
            + "Example: sort -n\n"
            + "Outcome: apparel list sorted in ascending order of the name.\n";


    public static final String MESSAGE_SORT_APPAREL_SUCCESS = "Apparels sorted by %1$s";
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

        // sort by sortBy.getValue()
        // model delete all apparels
        // model add all in the newly sorted

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SORT_APPAREL_SUCCESS));
    }

    /**
     * Creates and returns a {@code Apparel} with the details of {@code apparelToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Apparel createEditedPerson(Apparel apparelToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert apparelToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(apparelToEdit.getName());
        Color updatedColor = editPersonDescriptor.getColor().orElse(apparelToEdit.getColor());
        ClothingType updatedClothingType =
                editPersonDescriptor.getClothingType().orElse(apparelToEdit.getClothingType());

        return new Apparel(updatedName, updatedColor, updatedClothingType);
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

    /**
     * Stores the details to edit the apparel with. Each non-empty field value will replace the
     * corresponding field value of the apparel.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Color color;
        private ClothingType clothingType;
        private Address address;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setColor(toCopy.color);
            setClothingType(toCopy.clothingType);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, color, clothingType, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Optional<Color> getColor() {
            return Optional.ofNullable(color);
        }

        public void setClothingType(ClothingType clothingType) {
            this.clothingType = clothingType;
        }

        public Optional<ClothingType> getClothingType() {
            return Optional.ofNullable(clothingType);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getColor().equals(e.getColor())
                    && getClothingType().equals(e.getClothingType())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
