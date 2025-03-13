package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's payment info in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFee(int)} and {@link #isValidDate(String)}
 */
public class PaymentInfo {
    public static final String MESSAGE_CONSTRAINTS_FEE =
            "Fees should only contain numerical characters, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_DATE =
            "Dates should be in dd-MM-yyyy format, and it should not be blank";

    private final int paymentFee;
    private final String paymentDate;

    /**
     * Constructs a {@code PaymentInfo} with {@code paymentFee} and {@code paymentDate}.
     *
     * @param paymentFee A valid fee.
     * @param paymentDate A valid date.
     */
    public PaymentInfo(int paymentFee, String paymentDate) {
        requireNonNull(paymentDate);
        checkArgument(PaymentInfo.isValidFee(paymentFee), MESSAGE_CONSTRAINTS_FEE);
        checkArgument(isValidDate(paymentDate), MESSAGE_CONSTRAINTS_DATE);
        this.paymentFee = paymentFee;
        this.paymentDate = paymentDate;
    }

    /**
     * Constructs a {@code PaymentInfo} with {@code paymentFee} only.
     */
    public PaymentInfo(int paymentFee) {
        checkArgument(PaymentInfo.isValidFee(paymentFee), MESSAGE_CONSTRAINTS_FEE);
        this.paymentFee = paymentFee;
        this.paymentDate = "";
    }

    /**
     * Constructs a {@code PaymentInfo} with {@code paymentDate} only.
     */
    public PaymentInfo(String paymentDate) {
        requireNonNull(paymentDate);
        checkArgument(isValidDate(paymentDate), MESSAGE_CONSTRAINTS_DATE);
        this.paymentFee = 0;
        this.paymentDate = paymentDate;
    }

    /**
     * Constructs a {@code PaymentInfo} without {@code paymentFee} and {@code paymentDate}.
     */
    public PaymentInfo() {
        this.paymentFee = 0;
        this.paymentDate = "";
    }

    /**
     * Returns true if a given int is a valid fee (Positive).
     */
    public static boolean isValidFee(int fee) {
        return fee >= 0;
    }

    /**
     * Returns true if a given string is a valid date (dd-MM-yyyy).
     */
    public static boolean isValidDate(String s) {
        return StringUtil.isValidDate(s) || s.isEmpty();
    }

    public int getPaymentFee() {
        return paymentFee;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentFeeString() {
        return "Fee: $" + paymentFee;
    }

    public String getPaymentDateString() {
        return "Date: " + paymentDate;
    }

    @Override
    public String toString() {
        return "{" + getPaymentFeeString() + ", " + getPaymentDateString() + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PaymentInfo)) {
            return false;
        }

        PaymentInfo otherFee = (PaymentInfo) other;
        return paymentFee == otherFee.paymentFee && paymentDate.equals(otherFee.paymentDate);
    }
}
