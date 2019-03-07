package seedu.address.model.apparel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Apparel's color in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidColor(String)}
 */
public class Color {


    public static final String MESSAGE_CONSTRAINTS =
            "Color numbers should only contain numbers, and it should be at least 3 digits long"; //TODO
    public static final String VALIDATION_REGEX = "\\d{3,}"; //TODO
    public final String value;

    /**
     * Constructs a {@code Color}.
     *
     * @param color A valid color.
     */
    public Color(String color) {
        requireNonNull(color);
        checkArgument(isValidColor(color), MESSAGE_CONSTRAINTS);
        value = color;
    }

    /**
     * Returns true if a given string is a valid color.
     */
    public static boolean isValidColor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Color // instanceof handles nulls
                && value.equals(((Color) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
