package seedu.address.model.apparel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ClothingTypeTest {

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ClothingType(invalidEmail));
    }

    @Test
    public void isValidClothingType() {

        // blank clothingType
        assertFalse(ClothingTypeValue.isValidClothingType("")); // empty string
        assertFalse(ClothingTypeValue.isValidClothingType(" ")); // spaces only

        // invalid clothingType
        assertFalse(ClothingTypeValue.isValidClothingType("19838")); // numbers
        assertFalse(ClothingTypeValue.isValidClothingType("189a81")); // alphabets within digits

        // valid clothingType
        assertTrue(ClothingTypeValue.isValidClothingType("Shirt"));
    }
}
