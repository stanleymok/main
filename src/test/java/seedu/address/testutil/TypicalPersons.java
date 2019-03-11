package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.apparel.Apparel;

/**
 * A utility class containing a list of {@code Apparel} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Apparel SHIRT1 = new ApparelBuilder().withName("Formal Shirt")
            .withColor("White").build();
    public static final Apparel PANTS1 = new ApparelBuilder().withName("Formal Pants")
            .withClothingType("Bottom").withColor("Pink").build();
    public static final Apparel SHIRT2 = new ApparelBuilder().withName("Informal Shirt")
            .withColor("Blue")
            .withClothingType("Top").build();
    public static final Apparel BELT1 = new ApparelBuilder().withName("Formal Belt").withColor("Black")
            .withClothingType("Belt").build();
    public static final Apparel BELT2 = new ApparelBuilder().withName("Casual Belt").withColor("Yellow")
            .withClothingType("Belt").build();
    public static final Apparel BELT3 = new ApparelBuilder().withName("Casual Belt 2").withColor("Blue")
            .withClothingType("Belt").build();
    public static final Apparel SHOES1 = new ApparelBuilder().withName("Formal Shoes").withColor("Brown")
            .withClothingType("Shoes").build();

    // Manually added
    public static final Apparel SHOES2 = new ApparelBuilder().withName("Casual Shoes").withColor("Green")
            .withClothingType("Shoes").build();
    public static final Apparel SHOES3 = new ApparelBuilder().withName("Sneakers").withColor("Grey")
            .withClothingType("Shoes").build();

    // Manually added - Apparel's details found in {@code CommandTestUtil}
    public static final Apparel AMY = new ApparelBuilder().withName(VALID_NAME_AMY).withColor(VALID_PHONE_AMY)
            .withClothingType(VALID_EMAIL_AMY).build();
    public static final Apparel BOB = new ApparelBuilder().withName(VALID_NAME_BOB).withColor(VALID_PHONE_BOB)
            .withClothingType(VALID_EMAIL_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Apparel apparel : getTypicalPersons()) {
            ab.addApparel(apparel);
        }
        return ab;
    }

    public static List<Apparel> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(SHIRT1, PANTS1, SHIRT2, BELT1, BELT2, BELT3, SHOES1));
    }
}
