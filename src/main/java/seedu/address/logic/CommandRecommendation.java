package seedu.address.logic;

import seedu.address.model.apparel.Apparel;

/**
 * Stores the recommendated outfit of CommandRecommendation.
 */
public class CommandRecommendation {
    private Apparel top;
    private Apparel bottom;
    private Apparel belt;
    private Apparel shoes;

    public CommandRecommendation() {};

    public Apparel getTop() {
        return top;
    }

    public void setTop(Apparel top) {
        this.top = top;
    }

    public Apparel getBottom() {
        return bottom;
    }

    public void setBottom(Apparel bottom) {
        this.bottom = bottom;
    }

    public Apparel getBelt() {
        return belt;
    }

    public void setBelt(Apparel belt) {
        this.belt = belt;
    }

    public Apparel getShoes() {
        return shoes;
    }

    public void setShoes(Apparel shoes) {
        this.shoes = shoes;
    }
}
