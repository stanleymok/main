package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;
import seedu.address.model.apparel.Color;
import seedu.address.model.apparel.Name;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Apparel[] getSamplePersons() {
        return new Apparel[] {
            new Apparel(new Name("Formal Shirt"), new Color("WHITE"), new ClothingType("TOP")),

/*          new Apparel(new Name("Formal Pants"), new Color("Black"), new ClothingType("Bottom")),

            new Apparel(new Name("Formal Belt"), new Color("Black"), new ClothingType("Belt")),

            new Apparel(new Name("Formal Shoes"), new Color("Black"), new ClothingType("Shoes")),

            new Apparel(new Name("Uniqlo Tshirt"), new Color("Yellow"), new ClothingType("Top")),

            new Apparel(new Name("H&M Tshirt"), new Color("Brown"), new ClothingType("Top")),*/
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Apparel sampleApparel : getSamplePersons()) {
            sampleAb.addApparel(sampleApparel);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
