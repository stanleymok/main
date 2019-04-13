package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seedu.address.commons.core.index.Index;

/**
 * A handler for the {@code BrowserPanel} of the UI.
 */
public class BrowserPanelHandle extends NodeHandle<Node> {

    private static final String APPAREL_IMAGE_VIEW = "#apparelImageView";
    private static final String NAME_LABEL = "#nameLabel";
    private static final String CLOTHING_TYPE_LABEL = "#clothingTypeLabel";
    private static final String COLOR_LABEL = "#colorLabel";
    private static final String STATUS_LABEL = "#statusLabel";
    private static final String USAGE_COUNT_LABEL = "#usageCountLabel";

    private Index lastRememberIndex;
    private ImageView imageView;
    private Label nameLabel;
    private Label clothingTypeLabel;
    private Label colorLabel;
    private Label statusLabel;
    private Label usageCountLabel;

    private Image image;
    private String name;
    private String clothingType;
    private String color;
    private String status;
    private String usageCount;

    public BrowserPanelHandle(Node browserPanelNode) {
        super(browserPanelNode);

        imageView = getChildNode(APPAREL_IMAGE_VIEW);
        nameLabel = getChildNode(NAME_LABEL);
        clothingTypeLabel = getChildNode(CLOTHING_TYPE_LABEL);
        colorLabel = getChildNode(COLOR_LABEL);
        statusLabel = getChildNode(STATUS_LABEL);
        usageCountLabel = getChildNode(USAGE_COUNT_LABEL);
    }

    public Image getImage() { return imageView.getImage(); }
    public String getName() { return nameLabel.getText(); }
    public String getClothingType() { return clothingTypeLabel.getText(); }
    public String getColor() { return colorLabel.getText(); }
    public String getStatus() { return statusLabel.getText(); }
    public String getUsageCount() { return usageCountLabel.getText(); }

    /**
     * Remember the state of the selected apparel
     */
    public void rememberState() {
        image = imageView.getImage();
        name = nameLabel.getText();
        clothingType = clothingTypeLabel.getText();
        color = colorLabel.getText();
        status = statusLabel.getText();
        usageCount = usageCountLabel.getText();
    }
}
