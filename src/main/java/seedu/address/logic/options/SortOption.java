package seedu.address.logic.options;

/**
 * All valid options for Sort command.
 */
public enum SortOption {
    NAME, COLOR, TYPE;

    /**
     * Return true if option supplied is valid, false otherwise.
     */
    public static boolean isValid(String optionString) {
        for (SortOption so : SortOption.values()) {
            String validOption = so.toString();
            if (validOption.equalsIgnoreCase(optionString.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a SortOption.
     */
    public static SortOption create(String optionString) throws IllegalArgumentException {
        if (!isValid(optionString)) {
            throw new IllegalArgumentException("Invalid option.");
        }

        return SortOption.valueOf(optionString.toUpperCase());
    }
}
