package seedu.address.model.apparel;

/**
 * Represents the primary type of an apparel.
 */
public enum ClothingTypeValue {
    TOP,
    BOTTOM,
    BELT,
    SHOES;

    /**
     * Return true if a given string is a valid type.
     */
    public static boolean isValidType(String other) {
        ClothingTypeValue[] types = ClothingTypeValue.values();
        for (ClothingTypeValue t : types) {
            if (ClothingTypeValue.valueOf(other.toUpperCase()) == t) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return true if a given string is a valid type.
     */
    public static boolean isValidType(ClothingTypeValue other) {
        ClothingTypeValue[] types = ClothingTypeValue.values();
        for (ClothingTypeValue t : types) {
            if (other == t) {
                return true;
            }
        }

        return false;
    }
}
