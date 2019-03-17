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
    private Apparel reccomendedTop = null;
    private Apparel reccomendedBelt = null;
    private Apparel reccomendedShoe = null;
    private Apparel reccomendedBottom = null;

    /**
     * Constructs for CommandRecommendation
     */
    public CommandRecommendation(Model model) {
        setClothingInSections(model.getFilteredApparelList());
        reccomendedBottom = reccomendBottom();
        reccomendedTop = reccomendTop();
        reccomendedBelt = reccomendBelt();
        reccomendedShoe = reccomendShoe();

    }
    /**
     * returns string of recommended outfit
     */
    public String returnRecommendationString(){
       if (reccomendedBottom == null || reccomendedTop == null || reccomendedBelt == null
               || reccomendedShoe == null) {
            return "You don't have enough clothing";
        }

        return reccomendedTop.toString() + "\n" + reccomendedBottom.toString()
                + "\n" + reccomendedBelt.toString() + "\n" + reccomendedShoe.toString();


    }



    /**
     * find a reccomend Shoe
     */
    private Apparel reccomendShoe() {
        if (shoes.size() <= 0) {
            return null;
        }

        return shoes.get(0);
    }

    /**
     * find a recommended belt
     */
    private Apparel reccomendBelt() {
        if (belts.size() <= 0) {
            return null;
        }
        return belts.get(0);
    }

    /**
     * find a recommended top
     */
    private Apparel reccomendTop() {
        if (tops.size() <= 0) {
            return null;
        }
        return tops.get(0);
    }
    /**
     * find a recommended bottom
     */
    private Apparel reccomendBottom() {
        if (bottoms.size() <= 0) {
            return null;
        }
        int index = (int)(Math.random() * bottoms.size());
        return bottoms.get(0);
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
                break;
                case BELT: belts.add(apperal);
                break;
                case SHOES: shoes.add(apperal);
                break;
                case BOTTOM: bottoms.add(apperal);
                break;

            }
        }

    }

}
