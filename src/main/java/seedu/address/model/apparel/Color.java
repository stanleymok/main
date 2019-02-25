package seedu.address.model.apparel;

public class Color {

    private ColorValue primary;

    public Color(ColorValue primary) {
        this.primary = primary;
    }

    @Override
    public String toString() {
        return primary.name();
    }
}
