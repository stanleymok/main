package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_TOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOTTOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOR_GREEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOR_BLUE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.apparel.Apparel;

/**
 * A utility class containing a list of {@code Apparel} objects to be used in tests.
 */
public class TypicalApparels {

    public static final String NAME_FORMAL_SHIRT = "Format Shirt";
    public static final String NAME_FORMAL_PANTS = "Format Pants";
    public static final String NAME_INFORMAL_SHIRT = "Informal Shirt";
    public static final String NAME_FORMAL_BELT = "Belt";
    public static final String NAME_CASUAL_BELT = "Casual Belt";
    public static final String NAME_FORMAL_SHOES = "Formal Shoes";
    public static final String NAME_CASUAL_SHOES = "Casual Shoes";
    public static final String NAME_SNEAKERS = "Sneakers";

    public static final String TYPE_TOP = "Top";
    public static final String TYPE_BOTTOM = "Bottom";
    public static final String TYPE_BELT = "Belt";
    public static final String TYPE_SHOES = "Shoes";

    public static final String COLOR_WHITE = "White";
    public static final String COLOR_BLACK = "Black";
    public static final String COLOR_NAVY = "Navy";
    public static final String COLOR_BROWN = "Brown";
    public static final String COLOR_GREEN = "Green";
    public static final String COLOR_GREY = "Grey";



    public static final Apparel SHIRT1 = new ApparelBuilder().withName(NAME_FORMAL_SHIRT)
            .withColor(COLOR_WHITE).withClothingType(TYPE_TOP).build();
    public static final Apparel PANTS1 = new ApparelBuilder().withName(NAME_FORMAL_PANTS)
            .withClothingType(TYPE_BOTTOM).withColor(COLOR_BROWN).build();
    public static final Apparel SHIRT2 = new ApparelBuilder().withName(NAME_INFORMAL_SHIRT)
            .withColor(COLOR_NAVY)
            .withClothingType(TYPE_TOP).build();
    public static final Apparel BELT1 = new ApparelBuilder().withName(NAME_FORMAL_BELT).withColor(COLOR_BLACK)
            .withClothingType(TYPE_BELT).build();
    public static final Apparel BELT2 = new ApparelBuilder().withName(NAME_CASUAL_BELT).withColor(COLOR_WHITE)
            .withClothingType(TYPE_BELT).build();
    public static final Apparel BELT3 = new ApparelBuilder().withName(NAME_CASUAL_BELT).withColor(COLOR_NAVY)
            .withClothingType(TYPE_BELT).build();
    public static final Apparel SHOES1 = new ApparelBuilder().withName(NAME_FORMAL_SHOES).withColor(COLOR_BROWN)
            .withClothingType(TYPE_SHOES).build();

    // Manually added
    public static final Apparel SHOES2 = new ApparelBuilder().withName(NAME_CASUAL_SHOES).withColor(COLOR_GREEN)
            .withClothingType(TYPE_SHOES).build();
    public static final Apparel SHOES3 = new ApparelBuilder().withName(NAME_SNEAKERS).withColor(COLOR_GREY)
            .withClothingType(TYPE_SHOES).build();

    // Manually added - Apparel's details found in {@code CommandTestUtil}
    public static final Apparel ARMANY = new ApparelBuilder().withName(VALID_NAME_A).withColor(VALID_COLOR_GREEN)
            .withClothingType(VALID_TYPE_TOP).build();
    public static final Apparel BOBYIN = new ApparelBuilder().withName(VALID_NAME_B).withColor(VALID_COLOR_BLUE)
            .withClothingType(VALID_TYPE_BOTTOM).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApparels() {} // prevents instantiation

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
