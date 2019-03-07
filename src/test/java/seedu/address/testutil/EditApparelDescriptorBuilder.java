package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.apparel.Address;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;
import seedu.address.model.apparel.Name;
import seedu.address.model.apparel.Color;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditApparelDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditApparelDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditApparelDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code apparel}'s details
     */
    public EditApparelDescriptorBuilder(Apparel apparel) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(apparel.getName());
        descriptor.setColor(apparel.getColor());
        descriptor.setClothingType(apparel.getClothingType());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApparelDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Color} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApparelDescriptorBuilder withColor(String color) {
        descriptor.setColor(new Color(color));
        return this;
    }

    /**
     * Sets the {@code ClothingType} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApparelDescriptorBuilder withClothingType(String clothingType) {
        descriptor.setClothingType(new ClothingType(clothingType));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
