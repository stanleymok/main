package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.apparel.Apparel;
import seedu.address.model.apparel.ColorValue;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private ImageView apparelImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label clothingTypeLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label usageCountLabel;
    @FXML
    private Label statusLabel;

    public BrowserPanel(ObservableValue<Apparel> selectedApparel) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        selectedApparel.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                showApparelDetails(null);
            } else {
                showApparelDetails(newValue);
            }
        });
    }

    /**
     * Fill all text fields to show details about the apparel.
     * If the specified apparel is null, all text fields are cleared.
     */
    public void showApparelDetails(Apparel apparel) {

        if (apparel == null) {
            System.out.println("NULL LEIIII !!");
            nameLabel.setText("Click");
            clothingTypeLabel.setText("to");
            colorLabel.setText("display");
            statusLabel.setText("an");
            usageCountLabel.setText("apparel");
            Image image = new Image("images/default_welcome_smiley_icon.png");
        } else {
            // Fill the image
            Image image;
            ColorValue cv = apparel.getColor().getPrimary();
            switch(apparel.getClothingType().getClothingTypeValue()) {
            case TOP:
                image = new Image(FileUtil.getTopIconFilePath(cv));
                break;
            case BOTTOM:
                image = new Image(FileUtil.getBottomIconFilePath(cv));
                break;
            case BELT:
                image = new Image(FileUtil.getBeltIconFilePath(cv));
                break;
            case SHOES:
                image = new Image(FileUtil.getShoeIconFilePath(cv));
                break;
            default:
                throw new IllegalArgumentException("No such clothing type.");
            }
            apparelImageView.setImage(image);

            // Fill the labels with info from the apparel Object
            nameLabel.setText(apparel.getName().fullName);
            clothingTypeLabel.setText(apparel.getClothingType().toString());
            colorLabel.setText(apparel.getColor().toString());
            usageCountLabel.setText(String.valueOf(apparel.getUsageCount()));
            statusLabel.setText(apparel.getWornStatus());
        }
    }
}
