package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label eduLevel;
    @FXML
    private Label currentYear;
    @FXML
    private Label currentGrade;
    @FXML
    private Label placeholder4;
    @FXML
    private Label paymentFee;
    @FXML
    private Label paymentDate;

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

        eduLevel.setText("Education Level: " + person.getEduLevel().getEduLevel());
        currentYear.setText("Current Year: " + person.getCurrentYear());
        currentGrade.setText("Current Grade: " + person.getCurrentGrade());
        placeholder4.setText("placeholder text 4");
        PaymentInfo paymentInfo = person.getPaymentInfo();
        if (paymentInfo.getPaymentFee() == 0) {
            paymentFee.setManaged(false);
            paymentFee.setVisible(false);
        } else {
            paymentFee.setText("Tutoring Fee: $" + paymentInfo.getPaymentFee());
        }
        if (paymentInfo.getPaymentDate().isEmpty()) {
            paymentDate.setManaged(false);
            paymentDate.setVisible(false);
        } else {
            paymentDate.setText("Payment Date: " + paymentInfo.getPaymentDate());
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.fullTag))
                .forEach(tag -> tags.getChildren().add(createTagLabel(tag)));
    }

    /**
     * A helper function to create a {@code Label} given a {@code Tag}.
     * @param tag A valid Tag object.
     * @return The corresponding JavaFX Label object.
     */
    private Label createTagLabel(Tag tag) {
        String[] parts = tag.fullTag.split("#");
        Label label = new Label(parts[0]);
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
