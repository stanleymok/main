package seedu.address.logic;

import static seedu.address.model.apparel.ColorValue.BLACK;
import static seedu.address.model.apparel.ColorValue.BLUE;
import static seedu.address.model.apparel.ColorValue.BROWN;
import static seedu.address.model.apparel.ColorValue.CREAM;
import static seedu.address.model.apparel.ColorValue.GREEN;
import static seedu.address.model.apparel.ColorValue.GREY;
import static seedu.address.model.apparel.ColorValue.KHAKI;
import static seedu.address.model.apparel.ColorValue.NAVY;
import static seedu.address.model.apparel.ColorValue.ORANGE;
import static seedu.address.model.apparel.ColorValue.PINK;
import static seedu.address.model.apparel.ColorValue.PURPLE;
import static seedu.address.model.apparel.ColorValue.RED;
import static seedu.address.model.apparel.ColorValue.WHITE;
import static seedu.address.model.apparel.ColorValue.YELLOW;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ClothingType;
import seedu.address.model.apparel.Color;
import seedu.address.model.apparel.ColorValue;

/**
 * Stores the recommendation outfit of CommandRecommendation.
 */
// @@author PhilipPhil
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

        Collections.shuffle(bottoms);
        if (bottoms.size() <= 0) {
            return;
        }

        for (int i = 0; i < bottoms.size(); i++) {
            Apparel currBottom = bottoms.get(i);
            Apparel currTop = recommendTop(currBottom.getColor());
            Apparel currShoe = recommendShoe(currBottom.getColor());
            Apparel currBelt = null;

            if (currShoe != null) {
                currBelt = recommendBelt(currShoe.getColor());
            }

            if (currTop != null && currShoe != null) {
                recommendedBottom = currBottom;
                recommendedTop = currTop;
                recommendedShoe = currShoe;
                recommendedBelt = currBelt;
                if (recommendedBelt != null || belts.size() <= 0) {
                    return;
                }

            }

        }

    }
    /**
     * returns string of recommended outfit. If not enough items found returns message.
     * If enough items found but no belt, returns list of items with no belt.
     */
    public String returnRecommendationString() {
        if (recommendedBottom == null || recommendedTop == null || recommendedShoe == null) {
            return "You don't have enough clothing";
        }

        if (recommendedBelt == null) {
            return recommendedTop.toString() + "\n" + recommendedBottom.toString()
                    + "\n" + recommendedShoe.toString();
        }

        return recommendedTop.toString() + "\n" + recommendedBottom.toString()
                + "\n" + recommendedBelt.toString() + "\n" + recommendedShoe.toString();


    }



    /**
     * find a recommend Shoe
     */
    private Apparel recommendShoe(Color pantColor) {
        if (shoes.size() <= 0) {
            return null;
        }

        Collections.shuffle(shoes);
        for (int i = 0; i < shoes.size(); i++) {
            Apparel top = shoes.get(i);
            if (ColorRules.isValidShoeColor(pantColor, top.getColor())) {
                return shoes.get(i);
            }
        }

        return null;
    }

    /**
     * find a recommended belt
     */
    private Apparel recommendBelt(Color shoeColor) {
        if (belts.size() <= 0) {
            return null;
        }
        Collections.shuffle(belts);
        for (int i = 0; i < belts.size(); i++) {
            Apparel top = belts.get(i);
            if (ColorRules.isValidBeltColor(shoeColor, top.getColor())) {
                return belts.get(i);
            }
        }

        return null;
    }

    /**
     * find a recommended top
     */
    private Apparel recommendTop(Color bottomColor) {
        if (tops.size() <= 0) {
            return null;
        }
        Collections.shuffle(tops);
        for (int i = 0; i < tops.size(); i++) {
            Apparel top = tops.get(i);
            if (ColorRules.isValidTop(bottomColor, top.getColor())) {
                return tops.get(i);
            }
        }

        return null;
    }
    /**
     * find a recommended bottom
     */
    private Apparel recommendBottom() {
        if (bottoms.size() <= 0) {
            return null;
        }
        int index = (int) (Math.random() * bottoms.size());
        return bottoms.get(index);
    }
    /**
     * initiate all the clothing type lists
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
    /**
     * class responsible only for color relationships
     */
    // @@author PhilipPhil
    private static class ColorRules {

        private static ColorValue[] whitePantsToShoe = {WHITE, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, GREY};
        private static ColorValue[] greyPantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, BLACK};
        private static ColorValue[] blackPantsToShoe = {PURPLE, NAVY, BLUE, GREEN, RED, BLACK};
        private static ColorValue[] creamPantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED};
        private static ColorValue[] brownPantsToShoe = {WHITE, BROWN, NAVY, BLUE, GREEN};
        private static ColorValue[] bluePantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, BLACK};
        private static ColorValue[] navyPantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, BLACK};
        private static ColorValue[] greenPantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, BLACK};
        private static ColorValue[] redPantsToShoe = {WHITE, GREY, BROWN, PURPLE, NAVY, BLUE, GREEN, RED, BLACK};

        private static ColorValue[] whitePantsToShirt = {NAVY, RED, GREEN, BLACK, WHITE, PURPLE, PINK, GREY};
        private static ColorValue[] greyPantsToShirt = {WHITE, BLUE, BROWN, RED};
        private static ColorValue[] blackPantsToShirt = {NAVY, RED, GREEN, ORANGE, WHITE, PURPLE, YELLOW, PINK, GREY};
        private static ColorValue[] creamPantsToShirt = {NAVY, RED, PINK, GREEN, BLACK};
        private static ColorValue[] brownPantsToShirt = {WHITE};
        private static ColorValue[] greenPantsToShirt = {WHITE};
        private static ColorValue[] redPantsToShirt = {WHITE};
        private static ColorValue[] bluePantsToShirt = {WHITE, YELLOW, PINK, GREEN, PURPLE, BLUE, BROWN, RED, CREAM,
                                                        KHAKI, GREY, BLACK, ORANGE};
        private static ColorValue[] navyPantsToShirt = {WHITE, YELLOW, PINK, GREEN, PURPLE, BLUE, BROWN, RED, CREAM,
                                                        KHAKI, GREY, BLACK, ORANGE};



        /**
         * returns true if belt and shoes match
         */
        static boolean isValidBeltColor(Color shoe, Color belt) {
            if (shoe.getPrimary().equals(BLACK) && belt.getPrimary().equals(BLACK)) {
                return true;
            }
            if (shoe.getPrimary().equals(BROWN) && belt.getPrimary().equals(BROWN)) {
                return true;
            }
            if (shoe.getPrimary().equals(WHITE)) {
                return true;
            }
            return false;
        }

        /**
         * returns true if shoes and pants match
         */
        static boolean isValidShoeColor(Color bottom, Color shoe) {
            switch (bottom.getPrimary()) {
            case WHITE:
                return findMatchPants(whitePantsToShoe, shoe);
            case GREY:
                return findMatchPants(greyPantsToShoe, shoe);
            case BLACK:
                return findMatchPants(blackPantsToShoe, shoe);
            case CREAM:
                return findMatchPants(creamPantsToShoe, shoe);
            case BROWN:
                return findMatchPants(brownPantsToShoe, shoe);
            case BLUE:
                return findMatchPants(bluePantsToShoe, shoe);
            case NAVY:
                return findMatchPants(navyPantsToShoe, shoe);
            case GREEN:
                return findMatchPants(greenPantsToShoe, shoe);
            case RED:
                return findMatchPants(redPantsToShoe, shoe);
            default:
                return false;
            }
        }

        /**
         *  finds matching the set of shoes that corresponds to color of pants
         */
        private static boolean findMatchPants(ColorValue[] whitePantsToShoe, Color shoe) {
            for (int i = 0; i < whitePantsToShoe.length; i++) {
                if (shoe.getPrimary().equals(whitePantsToShoe[i])) {
                    return true;
                }
            }
            return false;
        }

        /**
         * returns true if bottom and top match
         */
        static boolean isValidTop(Color bottom, Color top) {
            ColorValue[] colorSet = findColors(bottom);

            for (int i = 0; i < colorSet.length; i++) {
                if (top.getPrimary().equals(colorSet[i])) {
                    return true;
                }
            }

            return false;
        }
        /**
         * finds matching the set of top colors that corresponds to color of bottom
         */
        private static ColorValue[] findColors(Color bottom) {
            switch (bottom.getPrimary()) {
            case WHITE:
                return whitePantsToShirt;
            case GREY:
                return greyPantsToShirt;
            case BLACK:
                return blackPantsToShirt;
            case CREAM:
                return creamPantsToShirt;
            case BROWN:
                return brownPantsToShirt;
            case BLUE:
                return bluePantsToShirt;
            case NAVY:
                return navyPantsToShirt;
            case GREEN:
                return greenPantsToShirt;
            default:
                return redPantsToShirt;
            }

        }
    }
}
