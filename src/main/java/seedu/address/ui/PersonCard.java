package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import seedu.address.commons.util.ColorUtil;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox details;
    @FXML
    private Label eduLevel;
    @FXML
    private Label currentYear;
    @FXML
    private Label currentGrade;
    @FXML
    private Label expectedGrade;
    @FXML
    private Label paymentFee;
    @FXML
    private Label paymentDate;
    @FXML
    private Label paymentStatus;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        if (person.getEduLevel().value.equals("")) {
            hideDetailsLabel(eduLevel);
        } else {
            eduLevel.setText("Education Level: " + person.getEduLevel().value);
        }

        if (person.getCurrentYear().value.equals("")) {
            hideDetailsLabel(currentYear);
        } else {
            currentYear.setText("Current Year: " + person.getCurrentYear().value);
        }

        if (person.getCurrentGrade().value.equals("")) {
            hideDetailsLabel(currentGrade);
        } else {
            currentGrade.setText("Current Grade: " + person.getCurrentGrade().value);
            currentGrade.setStyle("-fx-text-fill: " + person.getCurrentGrade().color);
        }

        if (person.getExpectedGrade().value.equals("")) {
            hideDetailsLabel(expectedGrade);
        } else {
            expectedGrade.setText("Expected Grade: " + person.getExpectedGrade().value);
            expectedGrade.setStyle("-fx-text-fill: " + person.getExpectedGrade().color);
        }

        PaymentInfo paymentInfo = person.getPaymentInfo();

        if (paymentInfo.getPaymentFee() == 0) {
            hideDetailsLabel(paymentFee);
        } else {
            paymentFee.setText("Tutoring Fee: $" + paymentInfo.getPaymentFee());
        }

        if (paymentInfo.getPaymentDate().isEmpty()) {
            hideDetailsLabel(paymentDate);
        } else {
            paymentDate.setText("Payment Date: " + paymentInfo.getPaymentDate());
        }

        if (paymentInfo.getPaymentStatus().isEmpty()) {
            hideDetailsLabel(paymentStatus);
        } else {
            paymentStatus.setText("Payment Status: " + paymentInfo.getPaymentStatus());
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.fullTag))
                .forEach(tag -> tags.getChildren().add(createTagLabel(tag)));
    }

    private void hideDetailsLabel(Label label) {
        label.setManaged(false);
        label.setVisible(false);
    }

    /**
     * A helper function to create a {@code Label} given a {@code Tag}.
     * @param tag A valid Tag object.
     * @return The corresponding JavaFX Label object.
     */
    private Label createTagLabel(Tag tag) {
        String[] parts = tag.fullTag.split("#");
        Label label = new Label(parts[0]);
        label.maxWidthProperty().bind(tags.widthProperty());
        label.setOnMouseClicked(event -> label.setWrapText(!label.isWrapText()));
        if (parts.length == 2 && !parts[1].isEmpty()) {
            // Determine text and background color if custom color code is found...
            String hexColor = parts[1];
            String textColor = ColorUtil.isLightColor(hexColor) ? "black" : "white";
            label.setStyle("-fx-background-color: #" + hexColor + "; -fx-text-fill: " + textColor + ";");
            // Create a tooltip showing the hex color
            Tooltip tooltip = new Tooltip("Color: #" + hexColor);
            tooltip.setShowDelay(Duration.millis(0)); // No delay before showing
            Tooltip.install(label, tooltip); // Attach tooltip to the label
        } else {
            // Create a tooltip showing the hex color
            Tooltip tooltip = new Tooltip("Color: #3e7b91");
            tooltip.setShowDelay(Duration.millis(0)); // No delay before showing
            Tooltip.install(label, tooltip); // Attach tooltip to the label
        }

        return label;
    }
}
