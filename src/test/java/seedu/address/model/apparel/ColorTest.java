package seedu.address.model.apparel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ColorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Color(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Color(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        Assert.assertThrows(NullPointerException.class, () -> Color.isValidColor(null));

        // invalid phone numbers
        assertFalse(Color.isValidColor("")); // empty string
        assertFalse(Color.isValidColor(" ")); // spaces only
        assertFalse(Color.isValidColor("91")); // less than 3 numbers
        assertFalse(Color.isValidColor("phone")); // non-numeric
        assertFalse(Color.isValidColor("9011p041")); // alphabets within digits
        assertFalse(Color.isValidColor("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Color.isValidColor("911")); // exactly 3 numbers
        assertTrue(Color.isValidColor("93121534"));
        assertTrue(Color.isValidColor("124293842033123")); // long phone numbers
    }
}
