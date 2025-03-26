package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.CurrentYear;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.person.Name;
import seedu.address.model.person.PaymentInfo;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_SEQUENCE = "Start index must be strictly less than End index.";
    public static final String MESSAGE_INVALID_PAYMENT_FEE = "Payment Fee is not an unsigned integer.";
    public static final String MESSAGE_INVALID_PAYMENT_DATE = "Payment Date is not a valid Date.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a "Start...End" {@code indexSequence} into a {@code List<Index>} containing two indices: Start and End.
     * Leading and trailing whitespaces will be trimmed, and Start and End must be valid positive integers.
     *
     * @throws ParseException if the input is invalid (not two positive integers, or Start >= End).
     */
    public static List<Index> parseIndexSequence(String indexSequence) throws ParseException {
        requireNonNull(indexSequence);
        String[] parts = indexSequence.trim().split("\\.\\.\\.");
        if (parts.length != 2) { // Must have exactly two parts
            throw new ParseException("Invalid format. Provide exactly two positive integers separated by '...'.");
        }
        if (!StringUtil.isNonZeroUnsignedInteger(parts[0])) {
            throw new ParseException("Start Index: " + MESSAGE_INVALID_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(parts[1])) {
            throw new ParseException("End Index: " + MESSAGE_INVALID_INDEX);
        }
        if (Integer.parseInt(parts[0]) >= Integer.parseInt(parts[1])) {
            throw new ParseException(MESSAGE_INVALID_INDEX_SEQUENCE);
        }
        return List.of(Index.fromOneBased(Integer.parseInt(parts[0])), Index.fromOneBased(Integer.parseInt(parts[1])));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTag(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String currentYear} into an {@code CurrentYear}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code currentYear} is invalid.
     */
    public static CurrentYear parseCurrentYear(String currentYear) throws ParseException {
        requireNonNull(currentYear);
        String trimmedCurrentYear = currentYear.trim();
        if (!CurrentYear.isValidCurrentYear(trimmedCurrentYear)) {
            throw new ParseException(CurrentYear.MESSAGE_CONSTRAINTS);
        }
        return new CurrentYear(trimmedCurrentYear);
    }

    /**
     * Parses a {@code String current grade} into an {@code CurrentGrade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code current grade} is invalid.
     */
    public static CurrentGrade parseCurrentGrade(String currentGrade) throws ParseException {
        requireNonNull(currentGrade);
        String trimmedCurrentGrade = currentGrade.trim();
        String upperCaseCurrentGrade = trimmedCurrentGrade.toUpperCase();
        if (!CurrentGrade.isValidCurrentGrade(upperCaseCurrentGrade)) {
            throw new ParseException(CurrentGrade.MESSAGE_CONSTRAINTS);
        }
        return new CurrentGrade(upperCaseCurrentGrade);
    }

    /**
     * Parses a {@code String expectedGrade} into an {@code ExpectedGrade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code expectedGrade} is invalid.
     */
    public static ExpectedGrade parseExpectedGrade(String expectedGrade) throws ParseException {
        requireNonNull(expectedGrade);
        String trimmedExpectedGrade = expectedGrade.trim();
        String upperCaseCurrentGrade = trimmedExpectedGrade.toUpperCase();
        if (!ExpectedGrade.isValidExpectedGrade(upperCaseCurrentGrade)) {
            throw new ParseException(ExpectedGrade.MESSAGE_CONSTRAINTS);
        }
        return new ExpectedGrade(upperCaseCurrentGrade);
    }

    /**
     * Parses a given {@code String} representing an education level and returns an {@code EduLevel} object.
     *
     * @param eduLevel The string representation of the education level to be parsed.
     * @return An {@code EduLevel} object representing the valid education level.
     * @throws ParseException If the provided education level is not one of the allowed values.
     */
    public static EduLevel parseEduLevel(String eduLevel) throws ParseException {
        requireNonNull(eduLevel);
        String trimmedEduLevel = eduLevel.trim();
        if (!EduLevel.isValidEduLevel(trimmedEduLevel)) {
            throw new ParseException(EduLevel.MESSAGE_CONSTRAINTS);
        }
        return new EduLevel(trimmedEduLevel);
    }

    /**
     * Parses {@code feeString} into an {@code int} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified fee is invalid (not unsigned integer).
     */
    public static int parseFee(String feeString) throws ParseException {
        String trimmedFee = feeString.trim();
        if (!StringUtil.isUnsignedInteger(trimmedFee)) {
            throw new ParseException(PaymentInfo.MESSAGE_CONSTRAINTS_FEE);
        }
        return Integer.parseInt(trimmedFee);
    }

    /**
     * Parses {@code dateString} into a valid date and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified {@code dateString} is invalid (not "dd-MM-yyyy").
     */
    public static String parseDate(String dateString) throws ParseException {
        String trimmedDate = dateString.trim();
        if (!StringUtil.isValidDate(trimmedDate)) {
            throw new ParseException(PaymentInfo.MESSAGE_CONSTRAINTS_DATE);
        }
        return trimmedDate;
    }

    /**
     * Parses {@code paymentStatusString} into a valid date and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified {@code paymentStatusString} is invalid (not "paid" or "waiting").
     */
    public static String parsePaymentStatus(String paymentStatusString) throws ParseException {
        String trimmedStatus = paymentStatusString.trim();
        if (!StringUtil.isValidPaymentStatus(trimmedStatus)) {
            throw new ParseException(PaymentInfo.MESSAGE_CONSTRAINTS_STATUS);
        }
        return trimmedStatus.substring(0, 1).toUpperCase() + trimmedStatus.substring(1).toLowerCase();
    }
}
