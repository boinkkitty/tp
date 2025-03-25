package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's payment info in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFee(int)} and {@link #isValidDate(String)}
 */
public class PaymentInfo {
    public static final String MESSAGE_CONSTRAINTS_FEE =
            "Fees should only contain unsigned integers, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_DATE =
            "Dates should be in dd-MM-yyyy format, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_STATUS =
            "Payment Status should only be either 'paid' or 'waiting', and it should not be blank";

    private final int paymentFee;
    private final String paymentDate;
    private final String paymentStatus;

    /**
     * Private constructor used by {@link Builder}.
     * Prevents direct instantiation to enforce controlled object creation.
     *
     * @param builder the builder instance containing configured field values
     */
    private PaymentInfo(Builder builder) {
        checkArgument(isValidFee(builder.paymentFee), MESSAGE_CONSTRAINTS_FEE);
        checkArgument(isValidDate(builder.paymentDate), MESSAGE_CONSTRAINTS_DATE);
        checkArgument(isValidStatus(builder.paymentStatus), MESSAGE_CONSTRAINTS_STATUS);
        this.paymentFee = builder.paymentFee;
        this.paymentDate = builder.paymentDate;
        if (builder.paymentStatus.isEmpty()) {
            this.paymentStatus = "";
        } else {
            this.paymentStatus = builder.paymentStatus.substring(0, 1).toUpperCase()
                    + builder.paymentStatus.substring(1).toLowerCase();
        }
    }

    /**
     * Builder class for {@link PaymentInfo}, providing a flexible way to create instances
     * with any combination of optional fields.
     */
    public static class Builder {
        private int paymentFee;
        private String paymentDate;
        private String paymentStatus;

        /** Default constructor for {@link Builder}. */
        public Builder() {
            this.paymentFee = 0;
            this.paymentDate = "";
            this.paymentStatus = "";
        }

        /** Sets the value of {@code paymentFee}. */
        public Builder setPaymentFee(int paymentFee) {
            this.paymentFee = paymentFee;
            return this;
        }

        /** Sets the value of {@code paymentDate}. */
        public Builder setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        /** Sets the value indicating whether the payment {@code isPaid}. */
        public Builder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        /** Builds and returns the instance of {@link PaymentInfo}. */
        public PaymentInfo build() {
            return new PaymentInfo(this);
        }
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

    /**
     * Returns true if a given string is a valid status ("paid" or "waiting").
     */
    public static boolean isValidStatus(String s) {
        return StringUtil.isValidPaymentStatus(s) || s.isEmpty();
    }

    public int getPaymentFee() {
        return paymentFee;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentFeeString() {
        return "Fee: $" + paymentFee;
    }

    public String getPaymentDateString() {
        return "Date: " + paymentDate;
    }

    public String getPaymentStatusString() {
        return "Payment Status: " + paymentStatus;
    }

    @Override
    public String toString() {
        return "{" + getPaymentFeeString() + ", " + getPaymentDateString() + ", " + getPaymentStatusString() + "}";
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

        PaymentInfo otherPaymentInfo = (PaymentInfo) other;
        return paymentFee == otherPaymentInfo.paymentFee
                && paymentDate.equals(otherPaymentInfo.paymentDate)
                && paymentStatus.equals(otherPaymentInfo.paymentStatus);
    }
}
