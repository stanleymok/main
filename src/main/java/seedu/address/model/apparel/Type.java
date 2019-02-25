package seedu.address.model.apparel;

public class Type {

    private final TypePrimaryValue primary;

    // private final TypePrimaryValue secondary;

    public Type(TypePrimaryValue primary /* , TypeSecondaryValue secondary*/) {
        this.primary = primary;
        //this.secondary = secondary;
    }

    @Override
    public String toString() {
        return primary.name();
    }
}
