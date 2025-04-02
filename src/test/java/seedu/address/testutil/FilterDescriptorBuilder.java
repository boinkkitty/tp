package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.person.CurrentGrade;
import seedu.address.model.person.EduLevel;
import seedu.address.model.person.ExpectedGrade;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building FilterDescriptor objects.
 */
public class FilterDescriptorBuilder {

    private FilterDescriptor descriptor;

    public FilterDescriptorBuilder() {
        descriptor = new FilterDescriptor();
    }

    public FilterDescriptorBuilder(FilterDescriptor descriptor) {
        this.descriptor = new FilterDescriptor(descriptor);
    }

    /**
     * Sets the {@code ExpectedGrade} of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withExpectedGrade(String expectedGrade) {
        descriptor.setExpectedGrade(new ExpectedGrade(expectedGrade));
        return this;
    }

    /**
     * Sets the {@code EduLevel} of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withEduLevel(String eduLevel) {
        descriptor.setEduLevel(new EduLevel(eduLevel));
        return this;
    }

    /**
     * Sets the {@code Current Grade} of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withCurrentGrade(String currentGrade) {
        descriptor.setCurrentGrade(new CurrentGrade(currentGrade));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code FilterDescriptor}
     * that we are building.
     */
    public FilterDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public FilterDescriptor build() {
        return descriptor;
    }
}
