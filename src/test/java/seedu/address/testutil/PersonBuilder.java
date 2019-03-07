package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.apparel.Address;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.Color;
import seedu.address.model.apparel.Email;
import seedu.address.model.apparel.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Apparel objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Color color;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        color = new Color(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code apparelToCopy}.
     */
    public PersonBuilder(Apparel apparelToCopy) {
        name = apparelToCopy.getName();
        color = apparelToCopy.getColor();
        email = apparelToCopy.getEmail();
        address = apparelToCopy.getAddress();
        tags = new HashSet<>(apparelToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Apparel} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Apparel} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Apparel} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Color} of the {@code Apparel} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.color = new Color(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Apparel} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Apparel build() {
        return new Apparel(name, color, email, address, tags);
    }

}
