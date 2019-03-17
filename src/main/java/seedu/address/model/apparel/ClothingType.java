package seedu.address.model.apparel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents am Apparel's ClothingType in the apparel bank.
 */
public class ClothingType {

    public static final String MESSAGE_CONSTRAINTS =
            "Types that are accepted are TOP, BOTTOM, BELT, SHOES.";

    private ClothingTypeValue primary;

    public ClothingType() {};

    /**
     * Constructs a {@code ClothingType}.
     *
     * @param primary A valid primary type.
     */
    public ClothingType(ClothingTypeValue primary) {
        requireNonNull(primary);
        checkArgument(ClothingTypeValue.isValidClothingType(primary), MESSAGE_CONSTRAINTS);

        this.primary = primary;
    }

    /**
     * Constructs a {@code Type}.
     *
     * @param primary A valid primary type.
     */
    public ClothingType(String primary) {
        requireNonNull(primary);
        checkArgument(ClothingTypeValue.isValidClothingType(primary), MESSAGE_CONSTRAINTS);

        this.primary = ClothingTypeValue.valueOf(primary.toUpperCase());
    }

    public ClothingTypeValue getClothingTypeValue() {
        return this.primary;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClothingType // instanceof handles nulls
                && primary == (((ClothingType) other).primary)); // state check
    }

    @Override
    public String toString() {
        return primary.name();
    }
}
