package seedu.address.logic;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;

/**
 * Stores the recommendated outfit of CommandRecommendation.
 */
public class CommandRecommendation {
    private ArrayList<Apparel> tops = new ArrayList<>();
    private ArrayList<Apparel> belts = new ArrayList<>();
    private ArrayList<Apparel> shoes = new ArrayList<>();
    private ArrayList<Apparel> bottoms = new ArrayList<>();
    private Apparel recommendedTop = null;
    private Apparel recommendedBelt = null;
    private Apparel recommendedShoe = null;
    private Apparel recommendedBottom = null;

    /**
     * Constructs for CommandRecommendation
     */
    public CommandRecommendation(Model model) {
        setClothingInSections(model.getFilteredApparelList());
        recommendedBottom = recommendBottom();
        recommendedTop = recommendTop();
        recommendedBelt = recommendBelt();
        recommendedShoe = recommendShoe();

    }
    /**
     * returns string of recommended outfit
     */
    public String returnRecommendationString() {
        if (recommendedBottom == null || recommendedTop == null || recommendedBelt == null
               || recommendedShoe == null) {
            return "You don't have enough clothing";
        }

        return recommendedTop.toString() + "\n" + recommendedBottom.toString()
                + "\n" + recommendedBelt.toString() + "\n" + recommendedShoe.toString();


    }



    /**
     * find a reccomend Shoe
     */
    private Apparel recommendShoe() {
        if (shoes.size() <= 0) {
            return null;
        }

        return shoes.get(0);
    }

    /**
     * find a recommended belt
     */
    private Apparel recommendBelt() {
        if (belts.size() <= 0) {
            return null;
        }
        return belts.get(0);
    }

    /**
     * find a recommended top
     */
    private Apparel recommendTop() {
        if (tops.size() <= 0) {
            return null;
        }
        return tops.get(0);
    }
    /**
     * find a recommended bottom
     */
    private Apparel recommendBottom() {
        if (bottoms.size() <= 0) {
            return null;
        }
        int index = (int) (Math.random() * bottoms.size());
        return bottoms.get(0);
    }
    /**
     * initiat all the clothing type lists
     */
    private void setClothingInSections(ObservableList<Apparel> filteredApparelList) {
        for (int i = 0; i < filteredApparelList.size(); i++) {
            Apparel apparel = filteredApparelList.get(i);
            ClothingType type = apparel.getClothingType();
            switch (type.getClothingTypeValue()) {
            case TOP:
                tops.add(apparel);
                break;
            case BELT:
                belts.add(apparel);
                break;
            case SHOES:
                shoes.add(apparel);
                break;
            case BOTTOM:
                bottoms.add(apparel);
                break;
            default:
                break;
            }
        }

    }

}
