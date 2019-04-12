package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.apparel.Apparel;


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
    private void showApparelDetails(Apparel apparel) {
        // TODO
        File file = new File("/images/address_book_32.png");
        Image image = new Image(file.toURI().toString());
        apparelImageView.setImage(image);
        if (apparel == null) {
            nameLabel.setText("");
            clothingTypeLabel.setText("");
            colorLabel.setText("");
            usageCountLabel.setText("");
            statusLabel.setText("");
        } else {
            // Fill the labels with info from the apparel Object
            System.out.println("apparel = " + apparel.toString());
            System.out.println("apparel usage = " + apparel.getUsageCount());
            nameLabel.setText(apparel.getName().fullName);
            clothingTypeLabel.setText(apparel.getClothingType().toString());
            colorLabel.setText(apparel.getColor().toString());
            usageCountLabel.setText(String.valueOf(apparel.getUsageCount()));
            statusLabel.setText(apparel.getWornStatus());
        }
    }

}
