package seedu.address.logic.options;

/**
 * All valid options for Sort command.
 */
public enum SortOption {
    /**
     * WARNING: OPTIONS must be the first. DO NOT put OPTIONS in any other order.
     */
    OPTIONS, NAME, COLOR, TYPE;

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

    /**
     * Return all the available sorting options in String format.
     */
    public static String allOptions() {
        StringBuilder sb = new StringBuilder();
        SortOption[] sortOptions = SortOption.values();
        sb.append("All the valid sorting options:\n");
        for (int i = 1; i < sortOptions.length; i++) {
            sb.append(sortOptions[i].toString().toLowerCase() + "\n");
        }
        return sb.toString();
    }
}
