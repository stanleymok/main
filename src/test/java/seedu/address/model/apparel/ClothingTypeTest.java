package seedu.address.model.apparel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ClothingTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ClothingType(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ClothingType(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> ClothingType.isValidClothingType(null));

        // blank email
        assertFalse(ClothingType.isValidClothingType("")); // empty string
        assertFalse(ClothingType.isValidClothingType(" ")); // spaces only

        // missing parts
        assertFalse(ClothingType.isValidClothingType("@example.com")); // missing local part
        assertFalse(ClothingType.isValidClothingType("peterjackexample.com")); // missing '@' symbol
        assertFalse(ClothingType.isValidClothingType("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(ClothingType.isValidClothingType("peterjack@-")); // invalid domain name
        assertFalse(ClothingType.isValidClothingType("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ClothingType.isValidClothingType("peter jack@example.com")); // spaces in local part
        assertFalse(ClothingType.isValidClothingType("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ClothingType.isValidClothingType(" peterjack@example.com")); // leading space
        assertFalse(ClothingType.isValidClothingType("peterjack@example.com ")); // trailing space
        assertFalse(ClothingType.isValidClothingType("peterjack@@example.com")); // double '@' symbol
        assertFalse(ClothingType.isValidClothingType("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ClothingType.isValidClothingType("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ClothingType.isValidClothingType("peterjack@.example.com")); // domain name starts with a period
        assertFalse(ClothingType.isValidClothingType("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ClothingType.isValidClothingType("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(ClothingType.isValidClothingType("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(ClothingType.isValidClothingType("PeterJack_1190@example.com"));
        assertTrue(ClothingType.isValidClothingType("a@bc")); // minimal
        assertTrue(ClothingType.isValidClothingType("test@localhost")); // alphabets only
        assertTrue(ClothingType.isValidClothingType("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(ClothingType.isValidClothingType("123@145")); // numeric local part and domain name
        assertTrue(ClothingType.isValidClothingType("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(ClothingType.isValidClothingType("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(ClothingType.isValidClothingType("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
