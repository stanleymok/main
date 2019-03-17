package seedu.address.logic;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;
import seedu.address.model.apparel.Color;

/**
 * Stores the recommendated outfit of CommandRecommendation.
 */
public class CommandRecommendation {
    private ArrayList<Apparel> tops = new ArrayList<>();
    private ArrayList<Apparel> belts = new ArrayList<>();
    private ArrayList<Apparel> shoes = new ArrayList<>();
    private ArrayList<Apparel> bottoms = new ArrayList<>();
    private Apparel reccomendedTop;
    private Apparel reccomendedBelt;
    private Apparel reccomendedShoe;
    private Apparel reccomendedBottom;

    /**
     * Constructs for CommandRecommendation
     */
    public CommandRecommendation(Model model) {
        setClothingInSections(model.getFilteredApparelList());
        reccomendedBottom = reccomendBottom();
        reccomendedTop = reccomendTop(reccomendedBottom.getColor());
        reccomendedBelt = reccomendBelt();
        reccomendedShoe = reccomendShoe();

    }

    /**
     * find a reccomend Shoe
     */
    private Apparel reccomendShoe() {
        if (shoes.size() > 0) {
            return null;
        }

        return shoes.get(0);
    }

    /**
     * find a recommended belt
     */
    private Apparel reccomendBelt() {
        if (belts.size() > 0) {
            return null;
        }
        return belts.get(0);
    }

    /**
     * find a recommended top
     */
    private Apparel reccomendTop(Color bottomColor) {
        if (tops.size() > 0) {
            return null;
        }
        return bottoms.get(0);
    }
    /**
     * find a recommended bottom
     */
    private Apparel reccomendBottom() {
        if (bottoms.size() > 0) {
            return null;
        }
        int index = (int)(Math.random() * bottoms.size());
        return bottoms.get(index);
    }
    /**
     * initiat all the clothing type lists
     */
    private void setClothingInSections(ObservableList<Apparel> filteredApparelList) {
        for (int i = 0; i < filteredApparelList.size(); i++) {
            Apparel apperal = filteredApparelList.get(i);
            ClothingType type = apperal.getClothingType();
            switch (type.getClothingTypeValue()) {
                case TOP: tops.add(apperal);
                case BELT: belts.add(apperal);
                case SHOES: shoes.add(apperal);
                case BOTTOM: bottoms.add(apperal);
            }
        }

    }

}
