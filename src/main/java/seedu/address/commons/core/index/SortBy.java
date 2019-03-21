package seedu.address.commons.core.index;

/**
 * Represent the order to which the apparels should be sorted.
 */
public class SortBy {
    private String value;
    public SortBy(String value) {
        this.value = value.toLowerCase();
    }

    /**
     * Check if sorting value is valid.
     * @return
     */
    public boolean isValid() {
        if (this.value == "n" || this.value == "name" || value == "c" || value == "color") {
            return true;
        }

        return false;
    }
}
